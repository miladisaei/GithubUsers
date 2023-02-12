package com.miladisaei.githubusers.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Domain
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.miladisaei.githubusers.data.model.User

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavoriteItem(user: User, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
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
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AvatarUser(
                modifier = Modifier.size(100.dp),
                user = user
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .padding(0.dp, 10.dp, 0.dp, 0.dp),
                    text = user.username,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onPrimary
                )
                user.location?.let {
                    Row(
                        modifier = Modifier
                            .padding(0.dp, 10.dp, 0.dp, 0.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(15.dp),
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "location",
                            tint = MaterialTheme.colors.onPrimary
                        )
                        Spacer(modifier = Modifier.size(3.dp))
                        Text(
                            text = it,
                            maxLines = 1,
                            style = MaterialTheme.typography.h5,
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                }
                user.company?.let {
                    Row(
                        modifier = Modifier
                            .padding(0.dp, 10.dp, 0.dp, 0.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(17.dp),
                            imageVector = Icons.Default.Domain,
                            contentDescription = "company",
                            tint = MaterialTheme.colors.onPrimary,
                        )
                        Spacer(modifier = Modifier.size(5.dp))
                        Text(
                            text = it,
                            maxLines = 1,
                            style = MaterialTheme.typography.h5,
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                }
                StatisticsUser(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 7.dp, 0.dp, 0.dp),
                    textColors = MaterialTheme.colors.onPrimary,
                    user = user
                )
            }
        }
    }
}