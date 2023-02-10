package com.miladisaei.githubusers.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.miladisaei.githubusers.data.model.User

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserItem(user: User, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .padding(10.dp, 7.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 1.dp,
        onClick = {
            onItemClick()
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            MaterialTheme.colors.secondary,
                            MaterialTheme.colors.primary
                        )
                    )
                )
        ) {
            AvatarUser(user = user)
            Text(
                modifier = Modifier
                    .padding(10.dp, 20.dp),
                text = user.username,
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}