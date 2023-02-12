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
    containBackButton: Boolean = false,
    containFavoriteButton: Boolean = true,
    containSettingButton: Boolean = true,
    containLanguageButton: Boolean = true,
    onFavoriteClick: () -> Unit = {},
    onSettingClick: () -> Unit = {}
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
        elevation = 0.dp,
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
            if(containFavoriteButton) {
                IconButton(onClick = {
                    onFavoriteClick()
                }) {
                    Icon(
                        Icons.Default.Favorite, "favorite",
                        tint = Pink
                    )
                }
            }
            if(containSettingButton) {
                IconButton(onClick = {
                    onSettingClick()
                }) {
                    Icon(
                        Icons.Default.Settings, "setting",
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            }
            if(containLanguageButton) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        Icons.Rounded.Language, "language",
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            }
        }
    )
}