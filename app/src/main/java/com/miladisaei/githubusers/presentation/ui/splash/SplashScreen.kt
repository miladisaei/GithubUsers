package com.miladisaei.githubusers.presentation.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.miladisaei.githubusers.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToMainScreen: () -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.size(80.dp))
            Image(
                modifier = Modifier
                    .size(200.dp),
                painter = painterResource(R.drawable.github),
                contentDescription = "empty"
            )
            Spacer(Modifier.size(15.dp))
            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.app_name),
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.onPrimary
            )

            LaunchedEffect(true) {
                delay(2000)
                onNavigateToMainScreen()
            }
        }
    }
}