package com.miladisaei.githubusers.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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

        composable(route = Screen.Main.route) {
            MainScreen(
                navController = navController,
                onNavigateToDetailScreen = { username ->
                    navController.navigate(route = Screen.Detail.route + "/${username}")
                }
            )
        }

        composable(
            route = Screen.Detail.route + "/{username}",
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) { navBackStackEntry ->
            DetailScreen(
                username = navBackStackEntry.arguments?.getString("username")
            )
        }

        composable(route = Screen.Favorite.route) {
            FavoriteScreen()
        }
    }
}