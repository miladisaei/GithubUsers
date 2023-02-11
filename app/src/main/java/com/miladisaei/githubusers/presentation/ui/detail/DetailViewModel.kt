package com.miladisaei.githubusers.presentation.ui.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miladisaei.githubusers.data.model.User
import com.miladisaei.githubusers.data.util.Resource
import com.miladisaei.githubusers.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
@Inject
constructor(
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val getFollowersUseCase: GetFollowersUseCase,
    private val getFollowingUseCase: GetFollowingUseCase,
    private val addFavoriteUserUseCase: AddFavoriteUserUseCase,
    private val deleteFavoriteUserUseCase: DeleteFavoriteUserUseCase
) : ViewModel() {

    data class DataStateDetails(
        val data: User? = null,
        val errorMessage: String? = null,
        val isLoading: Boolean = false
    )

    data class DataStateFollowers(
        val data: List<User>? = null,
        val errorMessage: String? = null,
        val isLoading: Boolean = false
    )

    data class DataStateFollowing(
        val data: List<User>? = null,
        val errorMessage: String? = null,
        val isLoading: Boolean = false
    )


    private val _userState: MutableState<DataStateDetails> = mutableStateOf(DataStateDetails())
    val userState: State<DataStateDetails> = _userState

    private val _followersState: MutableState<DataStateFollowers> =
        mutableStateOf(DataStateFollowers())
    val followersState: State<DataStateFollowers> = _followersState

    private val _followingState: MutableState<DataStateFollowing> =
        mutableStateOf(DataStateFollowing())
    val followingState: State<DataStateFollowing> = _followingState


    fun getUserDetails(username: String) {
        _userState.value = userState.value.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            getUserDetailsUseCase.execute(username).collect {
                when (it) {
                    is Resource.Success -> {
                        _userState.value = DataStateDetails(
                            data = it.data,
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        _userState.value = userState.value.copy(
                            isLoading = true
                        )
                    }
                    is Resource.Error -> {
                        _userState.value = userState.value.copy(
                            errorMessage = it.message,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    fun getFollowers(username: String) {
        _followersState.value = followersState.value.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            getFollowersUseCase.execute(username).collect {
                when (it) {
                    is Resource.Success -> {
                        _followersState.value = DataStateFollowers(
                            data = it.data,
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        _followersState.value = followersState.value.copy(
                            isLoading = true
                        )
                    }
                    is Resource.Error -> {
                        _followersState.value = followersState.value.copy(
                            errorMessage = it.message,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    fun getFollowing(username: String) {
        _followingState.value = followingState.value.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            getFollowingUseCase.execute(username).collect {
                when (it) {
                    is Resource.Success -> {
                        _followingState.value = DataStateFollowing(
                            data = it.data,
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        _followingState.value = followingState.value.copy(
                            isLoading = true
                        )
                    }
                    is Resource.Error -> {
                        _followingState.value = followingState.value.copy(
                            errorMessage = it.message,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}