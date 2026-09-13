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
    tertiary = Pink80,
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onBackground = Color.White,
    onSurface = Color.White
)

val FrogoLightColorScheme: ColorScheme = lightColorScheme(
    primary = FrogoColorPrimary,
    onPrimary = Color.White,
    primaryContainer = FrogoColorPrimaryDark,
    onPrimaryContainer = Color.White,
    secondary = FrogoColorAccent,
    onSecondary = Color.Black,
    tertiary = Pink40,
    background = Color(0xFFFAFAFA),
    surface = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F)
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