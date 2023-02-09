package com.miladisaei.githubusers.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.miladisaei.githubusers.presentation.navigation.NavigationGraph
import com.miladisaei.githubusers.presentation.navigation.Screen
import com.miladisaei.githubusers.presentation.ui.detail.DetailScreen
import com.miladisaei.githubusers.presentation.ui.detail.DetailViewModel
import com.miladisaei.githubusers.presentation.ui.favorite.FavoriteScreen
import com.miladisaei.githubusers.presentation.ui.favorite.FavoriteViewModel
import com.miladisaei.githubusers.presentation.ui.main.MainScreen
import com.miladisaei.githubusers.presentation.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            NavigationGraph(navController = navController)
        }
    }
}