package com.miladisaei.githubusers.presentation.ui.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miladisaei.githubusers.data.model.User
import com.miladisaei.githubusers.data.util.Resource
import com.miladisaei.githubusers.domain.usecase.GetSearchedUsersUseCase
import com.miladisaei.githubusers.util.USER_PAGINATION_PAGE_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val getSearchedUsersUseCase: GetSearchedUsersUseCase
) : ViewModel() {

    data class DataStateSearch(
        val data: MutableList<User> = mutableListOf(),
        val errorMessage: String? = null,
        val isLoading: Boolean = false,
        val isLoadingMore: Boolean = false,
        val isMoreFinished: Boolean = false
    )

    private val _searchState: MutableState<DataStateSearch> = mutableStateOf(DataStateSearch())
    val searchState: State<DataStateSearch> = _searchState

    val searchQuery = mutableStateOf("")

    var page = 1

    fun searchUser() {
        page = 1
        _searchState.value = searchState.value.copy(data = mutableListOf(), isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            getSearchedUsersUseCase.execute(
                searchQuery = searchQuery.value,
                page = page,
                count = USER_PAGINATION_PAGE_SIZE
            ).collect {
                when (it) {
                    is Resource.Success -> {
                        _searchState.value = DataStateSearch(
                            data = it.data?.users as MutableList<User>,
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        _searchState.value = searchState.value.copy(
                            isLoading = true
                        )
                    }
                    is Resource.Error -> {
                        _searchState.value = searchState.value.copy(
                            errorMessage = it.message,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    fun fetchMoreUsers(){
        page++
        _searchState.value = searchState.value.copy(isLoadingMore = true)
        viewModelScope.launch(Dispatchers.IO) {
            getSearchedUsersUseCase.execute(
                searchQuery = searchQuery.value,
                page = page,
                count = USER_PAGINATION_PAGE_SIZE
            ).collect {
                when (it) {
                    is Resource.Success -> {
                        it.data?.users?.let { moreUsersList ->
                            searchState.value.data.addAll(moreUsersList)
                            if(moreUsersList.size < USER_PAGINATION_PAGE_SIZE){
                                // Finish List Users
                                _searchState.value = DataStateSearch(
                                    data = searchState.value.data,
                                    isLoading = false,
                                    isLoadingMore = false,
                                    isMoreFinished = true
                                )
                            }else{
                                _searchState.value = DataStateSearch(
                                    data = searchState.value.data,
                                    isLoadingMore = false
                                )
                            }
                        }
                    }
                    is Resource.Loading -> {
                        _searchState.value = searchState.value.copy(
                            isLoadingMore = true
                        )
                    }
                    is Resource.Error -> {
                        _searchState.value = searchState.value.copy(
                            errorMessage = it.message,
                            isLoadingMore = false
                        )
                    }
                }
            }
        }
    }

}