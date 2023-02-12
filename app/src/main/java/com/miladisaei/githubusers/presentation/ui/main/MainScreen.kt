package com.miladisaei.githubusers.presentation.ui.main

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.miladisaei.githubusers.R
import com.miladisaei.githubusers.presentation.components.*

@Composable
fun MainScreen(
    navController: NavHostController,
    onNavigateToFavoriteScreen: () -> Unit,
    onNavigateToDetailScreen: (String) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            AppBar(
                title = stringResource(R.string.main_screen_title),
                navController = navController,
                onFavoriteClick = {
                    onNavigateToFavoriteScreen()
                },
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
                SearchView(
                    searchQuery = viewModel.searchQuery,
                    onSearchClick = { viewModel.searchUser() }
                )
                if (!viewModel.searchState.value.isLoading) {
                    if (viewModel.searchState.value.data.isEmpty()) {
                        EmptyList()
                    } else {
                        Box(modifier = Modifier.fillMaxSize()) {
                            LazyColumn(
                                modifier = Modifier.fillMaxSize(),
                                state = listState
                            ) {
                                items(viewModel.searchState.value.data) { user ->
                                    UserItem(user = user) {
                                        // OnItemClick
                                        onNavigateToDetailScreen(user.username)
                                    }
                                }
                            }
                            if (viewModel.searchState.value.isLoadingMore) {
                                ItemLoading()
                            }
                        }

                        listState.OnBottomListReached(buffer = 3) {
                            if (!viewModel.searchState.value.isMoreFinished)
                                viewModel.fetchMoreUsers()
                        }
                    }
                } else {
                    CircularLoading()
                }
            }
        }
    }
}