package com.miladisaei.githubusers.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.miladisaei.githubusers.data.model.User

@Composable
fun StatisticsUser(
    modifier: Modifier = Modifier,
    textColors: Color = MaterialTheme.colors.onBackground,
    user: User
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        ColumnStatistics(
            modifier = Modifier.weight(0.3f),
            title = "Follower",
            value = user.followersCount.toString(),
            textColors = textColors
        )
        ColumnStatistics(
            modifier = Modifier.weight(0.3f),
            title = "Following",
            value = user.followingCount.toString(),
            textColors = textColors
        )
        ColumnStatistics(
            modifier = Modifier.weight(0.3f),
            title = "Repository",
            value = user.repositoryCount.toString(),
            textColors = textColors
        )
    }
}

@Composable
fun ColumnStatistics(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    textColors: Color
) {
    Column(
        modifier = modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            color = textColors,
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = value,
            color = textColors,
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
    }
}