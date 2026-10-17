package com.hackernews.futuristic.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun NeonFeedTheme(
    theme: AppTheme,
    content: @Composable () -> Unit
) {
    val palette = theme.palette(systemDark = isSystemInDarkTheme())

    val base = if (palette.isDark) darkColorScheme() else lightColorScheme()
    val colorScheme = base.copy(
        primary = palette.accent,
        onPrimary = palette.onAccent,
        secondary = palette.secondaryAccent,
        tertiary = palette.positive,
        background = palette.background,
        onBackground = palette.textPrimary,
        surface = palette.surface,
        onSurface = palette.textPrimary,
        surfaceVariant = palette.card,
        onSurfaceVariant = palette.textSecondary,
        surfaceContainer = palette.surface,
        secondaryContainer = palette.accent.copy(alpha = 0.2f),
        onSecondaryContainer = palette.accent,
        outline = palette.border,
        error = palette.error
    )

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = palette.surface.toArgb()
            window.navigationBarColor = palette.surface.toArgb()
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = !palette.isDark
                isAppearanceLightNavigationBars = !palette.isDark
            }
        }
    }

    CompositionLocalProvider(LocalAppPalette provides palette) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}
