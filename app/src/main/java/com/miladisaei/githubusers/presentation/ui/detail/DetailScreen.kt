package com.miladisaei.githubusers.presentation.ui.detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DetailScreen(
    username: String?,
    viewModel: DetailViewModel = hiltViewModel()
) {
    Text(text = username!!)
}