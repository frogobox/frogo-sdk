package com.frogobox.composeui.theme


import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

val FrogoDarkColorScheme: ColorScheme = darkColorScheme(
    primary = FrogoColorAccent,
    onPrimary = Color.Black,
    primaryContainer = FrogoColorPrimaryDark,
    onPrimaryContainer = Color.White,
    secondary = PurpleGrey80,
    onSecondary = Color.Black,
    tertiary = Pink80,
    onTertiary = Color.Black,
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    surfaceVariant = Color(0xFF2C2C2C),
    onSurfaceVariant = Color(0xFFC4C7C5),
    surfaceContainerLowest = Color(0xFF0F0F0F),
    surfaceContainerLow = Color(0xFF1B1B1B),
    surfaceContainer = Color(0xFF222222),
    surfaceContainerHigh = Color(0xFF2A2A2A),
    surfaceContainerHighest = Color(0xFF333333),
    surfaceDim = Color(0xFF141414),
    surfaceBright = Color(0xFF383838),
    outline = Color(0xFF8E918F),
    outlineVariant = Color(0xFF444746),
    onBackground = Color.White,
    onSurface = Color.White,
    error = Color(0xFFFFB4AB),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6)
)

val FrogoLightColorScheme: ColorScheme = lightColorScheme(
    primary = FrogoColorPrimary,
    onPrimary = Color.White,
    primaryContainer = FrogoColorPrimaryDark,
    onPrimaryContainer = Color.White,
    secondary = FrogoColorAccent,
    onSecondary = Color.Black,
    tertiary = Pink40,
    onTertiary = Color.White,
    background = Color(0xFFFAFAFA),
    surface = Color.White,
    surfaceVariant = Color(0xFFE7E0EC),
    onSurfaceVariant = Color(0xFF49454F),
    surfaceContainerLowest = Color.White,
    surfaceContainerLow = Color(0xFFF7F2FA),
    surfaceContainer = Color(0xFFF3EDF7),
    surfaceContainerHigh = Color(0xFFECE6F0),
    surfaceContainerHighest = Color(0xFFE6E0E9),
    surfaceDim = Color(0xFFDED8E1),
    surfaceBright = Color(0xFFFEF7FF),
    outline = Color(0xFF79747E),
    outlineVariant = Color(0xFFCAC4D0),
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    error = Color(0xFFBA1A1A),
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002)
)

/**
 * The official Material 3 theme for Frogo SDK Compose components.
 */
@Composable
fun FrogoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> FrogoDarkColorScheme
        else -> FrogoLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

/** Alias for [FrogoTheme] */
@Composable
fun FrogoComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    FrogoTheme(darkTheme = darkTheme, dynamicColor = dynamicColor, content = content)
}

/**
 * @deprecated Use [FrogoTheme] instead. Retained for backwards compatibility.
 */
@Deprecated(
    message = "Use FrogoTheme instead",
    replaceWith = ReplaceWith("FrogoTheme(darkTheme, dynamicColor, content)")
)
@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    FrogoTheme(darkTheme = darkTheme, dynamicColor = dynamicColor, content = content)
}