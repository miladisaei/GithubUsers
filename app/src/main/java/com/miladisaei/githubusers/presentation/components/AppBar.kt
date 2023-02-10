package com.miladisaei.githubusers.presentation.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Language
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.miladisaei.githubusers.presentation.theme.Pink

@Composable
fun AppBar(
    title: String,
    navController: NavHostController,
    containBackButton: Boolean = false
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h4
            )
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        elevation = 2.dp,
        navigationIcon = if (containBackButton) {
            {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(Icons.Rounded.ArrowBack, "")
                }
            }
        } else null,
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Default.Favorite, "favorite",
                    tint = Pink
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Default.Settings, "setting",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Rounded.Language, "web",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        }
    )
}