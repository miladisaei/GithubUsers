package com.miladisaei.githubusers.presentation

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miladisaei.githubusers.data.model.SearchResponse
import com.miladisaei.githubusers.data.util.Resource
import com.miladisaei.githubusers.domain.usecase.GetSearchedUsersUseCase
import com.miladisaei.githubusers.util.CommonMethods
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val app: Application,
    private val getSearchedUsersUseCase: GetSearchedUsersUseCase
) : ViewModel() {

    val searchedUsers: MutableLiveData<Resource<SearchResponse>> = MutableLiveData()

    fun searchUser(
        searchQuery: String,
        page: Int,
        count: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        searchedUsers.postValue(Resource.Loading())
        try {
            if (CommonMethods.isNetworkAvailable(app.applicationContext)) {
                val apiResult = getSearchedUsersUseCase.execute(
                    searchQuery = searchQuery,
                    page = page,
                    count = count
                )
                searchedUsers.postValue(apiResult)
            } else {
                searchedUsers.postValue(Resource.Error(message = "Internet is not Available"))
            }
        } catch (e: Exception) {
            searchedUsers.postValue(Resource.Error(message = e.message.toString()))
        }
    }
}