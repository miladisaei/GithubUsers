package com.miladisaei.githubusers.presentation.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = DarkMagentaPink,
    primaryVariant = Gray165,
    onPrimary = Color.White,
    secondary = LightMagentaPink,
    background = WhiteBG,
    surface = WhiteBG,
    secondaryVariant = Gray240,
    onSecondary = Moonstone,
    onBackground = BlackBG
)

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
    primary = DarkMagentaPink,
    primaryVariant = Gray100,
    onPrimary = Color.White,
    secondary = LightMagentaPink,
    background = BlackBG,
    surface = BlackBG,
    secondaryVariant = Gray40,
    onSecondary = Moonstone,
    onBackground = WhiteBG
)


@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}