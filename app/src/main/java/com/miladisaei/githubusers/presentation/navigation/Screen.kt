package com.miladisaei.githubusers.presentation.navigation

sealed class Screen(
    val route: String
) {

    object Main : Screen("main")

    object Detail : Screen("detail")

    object Favorite : Screen("favorite")
}