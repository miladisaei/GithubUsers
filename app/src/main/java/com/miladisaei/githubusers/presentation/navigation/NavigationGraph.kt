package com.miladisaei.githubusers.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.miladisaei.githubusers.presentation.ui.detail.DetailScreen
import com.miladisaei.githubusers.presentation.ui.favorite.FavoriteScreen
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
                    navigateToDetailScreen(navController, username)
                }
            )
        }

        composable(
            route = Screen.Detail.route + "/{username}",
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) { navBackStackEntry ->
            DetailScreen(
                navController = navController,
                onNavigateToDetailScreen = { username ->
                    navigateToDetailScreen(navController, username)
                },
                username = navBackStackEntry.arguments?.getString("username")
            )
        }

        composable(route = Screen.Favorite.route) {
            FavoriteScreen()
        }
    }
}

fun navigateToDetailScreen(
    navController: NavHostController,
    username: String
) {
    navController.navigate(route = Screen.Detail.route + "/${username}")
}