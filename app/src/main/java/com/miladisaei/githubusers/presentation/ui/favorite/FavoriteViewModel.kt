package com.miladisaei.githubusers.presentation.ui.favorite

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesus.soldiership.datastore.SettingsDataStore
import com.miladisaei.githubusers.data.model.User
import com.miladisaei.githubusers.domain.usecase.GetFavoriteUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel
@Inject
constructor(
    private val settingsDataStore: SettingsDataStore,
    private val getFavoriteUsersUseCase: GetFavoriteUsersUseCase
) : ViewModel() {

    data class DataStateUsers(
        val data: List<User>? = null,
        val errorMessage: String? = null,
        val isLoading: Boolean = false
    )

    private val _usersState: MutableState<DataStateUsers> = mutableStateOf(DataStateUsers())
    val usersState: State<DataStateUsers> = _usersState

    fun toggleTheme() = settingsDataStore.toggleTheme()

    fun getFavoriteUsers() {
        _usersState.value = usersState.value.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            getFavoriteUsersUseCase.execute().collect {
                _usersState.value = DataStateUsers(
                    data = it,
                    isLoading = false
                )
            }
        }
    }
}