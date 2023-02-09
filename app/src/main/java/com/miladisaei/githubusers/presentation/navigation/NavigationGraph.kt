package com.miladisaei.githubusers.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.miladisaei.githubusers.presentation.ui.detail.DetailScreen
import com.miladisaei.githubusers.presentation.ui.detail.DetailViewModel
import com.miladisaei.githubusers.presentation.ui.favorite.FavoriteScreen
import com.miladisaei.githubusers.presentation.ui.favorite.FavoriteViewModel
import com.miladisaei.githubusers.presentation.ui.main.MainScreen
import com.miladisaei.githubusers.presentation.ui.main.MainViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {

        composable(route = Screen.Main.route) { navBackStackEntry ->
            val viewModel = hiltViewModel<MainViewModel>()
            MainScreen(
                viewModel = viewModel
            )
        }

        composable(
            route = Screen.Detail.route + "/{username}",
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) { navBackStackEntry ->
            val viewModel = hiltViewModel<DetailViewModel>()
            DetailScreen(
                username = navBackStackEntry.arguments?.getString("username"),
                viewModel = viewModel
            )
        }

        composable(route = Screen.Favorite.route) { navBackStackEntry ->
            val viewModel = hiltViewModel<FavoriteViewModel>()
            FavoriteScreen(
                viewModel = viewModel
            )
        }
    }
}