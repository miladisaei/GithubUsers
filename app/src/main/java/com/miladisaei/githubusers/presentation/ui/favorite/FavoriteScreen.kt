package com.miladisaei.githubusers.presentation.ui.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.miladisaei.githubusers.R
import com.miladisaei.githubusers.presentation.components.*

@Composable
fun FavoriteScreen(
    navController: NavHostController,
    onNavigateToDetailScreen: (String) -> Unit,
    viewModel: FavoriteViewModel = hiltViewModel()
) {

    if (viewModel.usersState.value.data == null)
        viewModel.getFavoriteUsers()

    Scaffold(
        topBar = {
            AppBar(
                title = stringResource(R.string.favorite_screen_title),
                navController = navController,
                containFavoriteButton = false,
                containLanguageButton = false,
                containBackButton = true,
                onSettingClick = {
                    viewModel.toggleTheme()
                }
            )
        }
    ) { paddingValues ->
        val listState = rememberLazyListState()
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = MaterialTheme.colors.surface
        ) {
            Column {
                if (!viewModel.usersState.value.isLoading) {
                    if (viewModel.usersState.value.data.isNullOrEmpty()) {
                        EmptyList()
                    } else {
                        Box(modifier = Modifier.fillMaxSize()) {
                            LazyColumn(
                                modifier = Modifier.fillMaxSize(),
                                state = listState
                            ) {
                                items(viewModel.usersState.value.data!!) { user ->
                                    FavoriteItem(user = user) {
                                        // OnItemClick
                                        onNavigateToDetailScreen(user.username)
                                    }
                                }
                            }
                        }

                    }
                } else {
                    CircularLoading()
                }
            }
        }
    }
}