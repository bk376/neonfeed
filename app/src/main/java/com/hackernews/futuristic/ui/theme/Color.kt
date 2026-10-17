package com.hackernews.futuristic.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/** Semantic colors used by every screen. Each [AppTheme] supplies one of these. */
data class AppPalette(
    val isDark: Boolean,
    val background: Color,
    val surface: Color,
    val card: Color,
    val border: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textDim: Color,
    /** Titles, primary buttons, progress indicators. */
    val accent: Color,
    /** Content drawn on top of [accent]. */
    val onAccent: Color,
    /** Scores. */
    val positive: Color,
    /** Comment counts, thread depth markers. */
    val secondaryAccent: Color,
    /** Authors, domains, external links. */
    val link: Color,
    /** Timestamps. */
    val highlight: Color,
    val bookmark: Color,
    val error: Color
)

private val NeonPalette = AppPalette(
    isDark = true,
    background = Color(0xFF0A0A0A),
    surface = Color(0xFF1A1A1A),
    card = Color(0xFF2A2A2A),
    border = Color(0xFF3A3A3A),
    textPrimary = Color(0xFFFFFFFF),
    textSecondary = Color(0xFFB3B3B3),
    textDim = Color(0xFF666666),
    accent = Color(0xFF00D4FF),
    onAccent = Color.Black,
    positive = Color(0xFF00FF88),
    secondaryAccent = Color(0xFF8B5CF6),
    link = Color(0xFF00FFFF),
    highlight = Color(0xFFFFD700),
    bookmark = Color(0xFFFFD700),
    error = Color(0xFFFF0040)
)

private val AmoledPalette = NeonPalette.copy(
    background = Color.Black,
    surface = Color(0xFF0D0D0D),
    card = Color(0xFF161616),
    border = Color(0xFF262626)
)

private val DarkPalette = AppPalette(
    isDark = true,
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    card = Color(0xFF262626),
    border = Color(0xFF333333),
    textPrimary = Color(0xFFE6E6E6),
    textSecondary = Color(0xFFA0A0A0),
    textDim = Color(0xFF6B6B6B),
    accent = Color(0xFFFF8A3D),
    onAccent = Color.Black,
    positive = Color(0xFF81C784),
    secondaryAccent = Color(0xFFB39DDB),
    link = Color(0xFF64B5F6),
    highlight = Color(0xFFE0B252),
    bookmark = Color(0xFFFFCA28),
    error = Color(0xFFEF5350)
)

private val LightPalette = AppPalette(
    isDark = false,
    background = Color(0xFFF6F6EF),
    surface = Color(0xFFFFFFFF),
    card = Color(0xFFF0F0E8),
    border = Color(0xFFDADAD0),
    textPrimary = Color(0xFF1A1A1A),
    textSecondary = Color(0xFF5F5F5F),
    textDim = Color(0xFF8A8A8A),
    accent = Color(0xFFD35400),
    onAccent = Color.White,
    positive = Color(0xFF2E7D32),
    secondaryAccent = Color(0xFF6A1B9A),
    link = Color(0xFF0B63B6),
    highlight = Color(0xFF8D6E00),
    bookmark = Color(0xFFE09F00),
    error = Color(0xFFC62828)
)

enum class AppTheme(val displayName: String) {
    SYSTEM("System"),
    NEON("Neon"),
    AMOLED("AMOLED"),
    DARK("Dark"),
    LIGHT("Light");

    fun palette(systemDark: Boolean): AppPalette = when (this) {
        SYSTEM -> if (systemDark) NeonPalette else LightPalette
        NEON -> NeonPalette
        AMOLED -> AmoledPalette
        DARK -> DarkPalette
        LIGHT -> LightPalette
    }
}

internal val LocalAppPalette = staticCompositionLocalOf { NeonPalette }

/** The active theme's palette, e.g. `AppColors.accent`. */
val AppColors: AppPalette
    @Composable
    @ReadOnlyComposable
    get() = LocalAppPalette.current
