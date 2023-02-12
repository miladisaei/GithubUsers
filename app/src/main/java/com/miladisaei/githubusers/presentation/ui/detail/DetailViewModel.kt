package com.miladisaei.githubusers.presentation.ui.detail

import android.app.Application
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesus.soldiership.datastore.SettingsDataStore
import com.miladisaei.githubusers.data.model.User
import com.miladisaei.githubusers.data.util.Resource
import com.miladisaei.githubusers.domain.usecase.*
import com.miladisaei.githubusers.util.CommonMethods
import com.miladisaei.githubusers.util.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
@Inject
constructor(
    private val app: Application,
    private val settingsDataStore: SettingsDataStore,
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val getFollowersUseCase: GetFollowersUseCase,
    private val getFollowingUseCase: GetFollowingUseCase,
    private val addFavoriteUserUseCase: AddFavoriteUserUseCase,
    private val deleteFavoriteUserUseCase: DeleteFavoriteUserUseCase,
    private val isExistUserInFavoritesUseCase: IsExistUserInFavoritesUseCase
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

    data class DataStateFavorite(
        val addedToFavorite: Boolean = false,
        val deletedFromFavorite: Boolean = false,
        val existInFavoriteList: Boolean = false,
        val checkedExist: Boolean = false
    )


    private val _userState: MutableState<DataStateDetails> = mutableStateOf(DataStateDetails())
    val userState: State<DataStateDetails> = _userState

    private val _followersState: MutableState<DataStateFollowers> =
        mutableStateOf(DataStateFollowers())
    val followersState: State<DataStateFollowers> = _followersState

    private val _followingState: MutableState<DataStateFollowing> =
        mutableStateOf(DataStateFollowing())
    val followingState: State<DataStateFollowing> = _followingState

    private val _favoriteState: MutableState<DataStateFavorite> =
        mutableStateOf(DataStateFavorite())
    val favoriteState: State<DataStateFavorite> = _favoriteState

    fun toggleTheme() = settingsDataStore.toggleTheme()


    fun getUserDetails(username: String) {
        if (CommonMethods.isNetworkAvailable((app.applicationContext))) {
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
    }

    fun getFollowers(username: String) {
        if (CommonMethods.isNetworkAvailable((app.applicationContext))) {
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
    }

    fun getFollowing(username: String) {
        if (CommonMethods.isNetworkAvailable((app.applicationContext))) {
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

    fun toggleFavoriteUser() {
        if (favoriteState.value.existInFavoriteList)
            deleteUserFromFavorite()
        else
            addUserToFavorite()
    }

    private fun addUserToFavorite() {
        if (CommonMethods.isNetworkAvailable((app.applicationContext))) {
            userState.value.data?.let { user ->
                viewModelScope.launch(Dispatchers.IO) {
                    addFavoriteUserUseCase.execute(user)
                    _favoriteState.value = favoriteState.value.copy(
                        addedToFavorite = true,
                        deletedFromFavorite = false,
                        existInFavoriteList = true
                    )
                }
            }
        }
    }

    private fun deleteUserFromFavorite() {
        if (CommonMethods.isNetworkAvailable((app.applicationContext))) {
            userState.value.data?.let { user ->
                viewModelScope.launch(Dispatchers.IO) {
                    deleteFavoriteUserUseCase.execute(user)
                    _favoriteState.value = favoriteState.value.copy(
                        addedToFavorite = false,
                        deletedFromFavorite = true,
                        existInFavoriteList = false
                    )
                }
            }
        }
    }

    fun checkExistUserInFavoriteList(username: String) {
        if (CommonMethods.isNetworkAvailable((app.applicationContext))) {
            viewModelScope.launch(Dispatchers.IO) {
                isExistUserInFavoritesUseCase.execute(username).collect {
                    try {
                        _favoriteState.value = favoriteState.value.copy(
                            existInFavoriteList = it,
                            checkedExist = true
                        )
                    } catch (e: Exception) {
                        Log.d(TAG, "checkExistUserInFavoriteList: ${e.message}")
                    }
                }
            }
        }
    }
}