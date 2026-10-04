package com.example.afsharij.abvcalculator.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Teal700,
    secondary = Pink700,
    tertiary = Teal900
)

private val LightColorScheme = lightColorScheme(
    primary = Teal700,
    secondary = Pink700,
    tertiary = Teal900
)

@Composable
fun AbvCalculatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
