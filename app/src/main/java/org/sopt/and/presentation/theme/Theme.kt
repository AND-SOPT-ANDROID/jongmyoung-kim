package org.sopt.and.presentation.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

object AndAndroidTheme {
    val colors: AndAndroidColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAndAndroidColors.current
}

@Composable
fun ProvideAppColors(
    colors: AndAndroidColors,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalAndAndroidColors provides colors,
        content = content
    )
}

@Composable
fun ANDANDROIDTheme(
    content: @Composable () -> Unit
) {
    ProvideAppColors(colors = defaultAndAndroidColors) {
        val view = LocalView.current
        if (!view.isInEditMode) {
            SideEffect {
                val window = (view.context as Activity).window
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
            }
        }
        MaterialTheme(
            typography = Typography,
            content = content
        )
    }
}