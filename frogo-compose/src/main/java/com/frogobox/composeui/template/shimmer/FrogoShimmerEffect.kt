package com.frogobox.composeui.template.shimmer

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme

fun Modifier.frogoShimmerEffect(
    showShimmer: Boolean = true,
    targetValue: Float = 1000f,
    colors: List<Color>? = null
): Modifier = composed {
    if (!showShimmer) return@composed this

    val isDark = isSystemInDarkTheme()
    val defaultBaseColor = if (isDark) {
        MaterialTheme.colorScheme.surfaceVariant
    } else {
        Color.LightGray
    }

    val shimmerColors = colors ?: listOf(
        defaultBaseColor.copy(alpha = 0.6f),
        defaultBaseColor.copy(alpha = 0.2f),
        defaultBaseColor.copy(alpha = 0.6f),
    )

    val transition = rememberInfiniteTransition(label = "ShimmerTransition")
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = targetValue,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "ShimmerTranslate"
    )

    this.drawBehind {
        val progress = translateAnimation.value
        val brush = Brush.linearGradient(
            colors = shimmerColors,
            start = Offset.Zero,
            end = Offset(x = progress, y = progress)
        )
        drawRect(brush = brush)
    }
}
