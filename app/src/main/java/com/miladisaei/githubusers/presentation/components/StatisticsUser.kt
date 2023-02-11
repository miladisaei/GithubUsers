package com.miladisaei.githubusers.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.miladisaei.githubusers.data.model.User

@Composable
fun StatisticsUser(user: User) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(15.dp, 5.dp, 15.dp, 15.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .background(MaterialTheme.colors.background),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        ColumnStatistics(
            modifier = Modifier.weight(0.3f),
            title = "Follower",
            value = user.followersCount.toString()
        )
        ColumnStatistics(
            modifier = Modifier.weight(0.3f),
            title = "Following",
            value = user.followingCount.toString()
        )
        ColumnStatistics(
            modifier = Modifier.weight(0.3f),
            title = "Repository",
            value = user.repositoryCount.toString()
        )
    }
}

@Composable
fun ColumnStatistics(
    modifier: Modifier = Modifier,
    title: String,
    value: String
) {
    Column(
        modifier = modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = value,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
    }
}