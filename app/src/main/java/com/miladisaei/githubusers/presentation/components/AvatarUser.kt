package com.miladisaei.githubusers.presentation.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.miladisaei.githubusers.data.model.User

@Composable
fun AvatarUser(
    modifier: Modifier = Modifier,
    user: User
) {
    AsyncImage(
        modifier = modifier
            .padding(10.dp)
            .clip(CircleShape)
            .aspectRatio(1f),
        contentScale = ContentScale.Crop,
        model = user.avatarUrl,
        contentDescription = user.username
    )
}