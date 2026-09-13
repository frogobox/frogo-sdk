package com.frogobox.composeui.animation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer

enum class FrogoAnimationComposeType {
    Bounce, Flash, Pulse, Rubberband, Shake, Standup, Swing, Tada, Wave, Wobble,
    FadeIn, FadeOut, ZoomIn, ZoomOut
}

/**
 * A native Jetpack Compose Modifier that applies custom entrance or attention-seeking animations.
 *
 * @param type The type of animation to run.
 * @param trigger Any key that, when changed, triggers a restart of a one-shot animation.
 * @param durationMillis Duration of a single animation cycle.
 * @param repeat If true, the animation runs continuously in a loop.
 */
fun Modifier.frogoAnimationCompose(
    type: FrogoAnimationComposeType,
    trigger: Any? = null,
    durationMillis: Int = 1000,
    repeat: Boolean = false
): Modifier = composed {
    val animProgress = remember { Animatable(0f) }

    LaunchedEffect(trigger, repeat) {
        if (repeat) {
            animProgress.animateTo(
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            )
        } else {
            animProgress.snapTo(0f)
            animProgress.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis, easing = LinearOutSlowInEasing)
            )
        }
    }

    this.then(
        Modifier.graphicsLayer {
            val progress = animProgress.value
            when (type) {
                FrogoAnimationComposeType.Bounce -> {
                    // Simulates Bounce: translationY bounces up and down
                    val bounceVal = when {
                        progress < 0.2f -> 0f
                        progress < 0.4f -> -30f * (1f - (progress - 0.2f) / 0.2f)
                        progress < 0.6f -> 0f
                        progress < 0.8f -> -15f * (1f - (progress - 0.6f) / 0.2f)
                        else -> 0f
                    }
                    translationY = bounceVal
                }
                FrogoAnimationComposeType.Flash -> {
                    // Simulates Flash: alpha changes between 1f and 0f
                    val flashAlpha = when {
                        progress < 0.2f -> 1f - (progress / 0.2f)
                        progress < 0.4f -> (progress - 0.2f) / 0.2f
                        progress < 0.6f -> 1f - ((progress - 0.4f) / 0.2f)
                        progress < 0.8f -> (progress - 0.6f) / 0.2f
                        else -> 1f
                    }
                    alpha = flashAlpha
                }
                FrogoAnimationComposeType.Pulse -> {
                    // Simulates Pulse: scale up and down
                    val pulseScale = if (progress < 0.5f) {
                        1f + 0.1f * (progress / 0.5f)
                    } else {
                        1.1f - 0.1f * ((progress - 0.5f) / 0.5f)
                    }
                    scaleX = pulseScale
                    scaleY = pulseScale
                }
                FrogoAnimationComposeType.Rubberband -> {
                    // Rubberband scale animation
                    val sx = when {
                        progress < 0.3f -> 1f + 0.25f * (progress / 0.3f)
                        progress < 0.5f -> 1.25f - 0.5f * ((progress - 0.3f) / 0.2f)
                        progress < 0.7f -> 0.75f + 0.4f * ((progress - 0.5f) / 0.2f)
                        progress < 0.85f -> 1.15f - 0.15f * ((progress - 0.7f) / 0.15f)
                        else -> 1f
                    }
                    val sy = when {
                        progress < 0.3f -> 1f - 0.25f * (progress / 0.3f)
                        progress < 0.5f -> 0.75f + 0.5f * ((progress - 0.3f) / 0.2f)
                        progress < 0.7f -> 1.25f - 0.4f * ((progress - 0.5f) / 0.2f)
                        progress < 0.85f -> 0.85f + 0.15f * ((progress - 0.7f) / 0.15f)
                        else -> 1f
                    }
                    scaleX = sx
                    scaleY = sy
                }
                FrogoAnimationComposeType.Shake -> {
                    // Shake horizontally
                    val dx = when {
                        progress < 0.15f -> 10f * (progress / 0.15f)
                        progress < 0.3f -> 10f - 20f * ((progress - 0.15f) / 0.15f)
                        progress < 0.45f -> -10f + 20f * ((progress - 0.3f) / 0.15f)
                        progress < 0.6f -> 10f - 20f * ((progress - 0.45f) / 0.15f)
                        progress < 0.75f -> -10f + 15f * ((progress - 0.6f) / 0.15f)
                        progress < 0.9f -> 5f - 5f * ((progress - 0.75f) / 0.15f)
                        else -> 0f
                    }
                    translationX = dx
                }
                FrogoAnimationComposeType.Standup -> {
                    transformOrigin = TransformOrigin(0.5f, 1f)
                    val rx = when {
                        progress < 0.2f -> 55f * (1f - progress / 0.2f)
                        progress < 0.4f -> -30f * ((progress - 0.2f) / 0.2f)
                        progress < 0.6f -> -30f + 45f * ((progress - 0.4f) / 0.2f)
                        progress < 0.8f -> 15f - 30f * ((progress - 0.6f) / 0.2f)
                        else -> -15f * (1f - (progress - 0.8f) / 0.2f)
                    }
                    rotationX = rx
                }
                FrogoAnimationComposeType.Swing -> {
                    transformOrigin = TransformOrigin(0.5f, 0f)
                    val rot = when {
                        progress < 0.2f -> 10f * (progress / 0.2f)
                        progress < 0.4f -> 10f - 20f * ((progress - 0.2f) / 0.2f)
                        progress < 0.6f -> -10f + 16f * ((progress - 0.4f) / 0.2f)
                        progress < 0.8f -> 6f - 12f * ((progress - 0.6f) / 0.2f)
                        else -> -6f * (1f - (progress - 0.8f) / 0.2f)
                    }
                    rotationZ = rot
                }
                FrogoAnimationComposeType.Tada -> {
                    val rot = when {
                        progress < 0.1f -> 0f
                        progress < 0.9f -> if (((progress * 10).toInt() % 2) == 0) -3f else 3f
                        else -> 0f
                    }
                    val scale = when {
                        progress < 0.1f -> 1f - 0.1f * (progress / 0.1f)
                        progress < 0.2f -> 0.9f
                        progress < 0.9f -> 1.1f
                        else -> 1.1f - 0.1f * ((progress - 0.9f) / 0.1f)
                    }
                    scaleX = scale
                    scaleY = scale
                    rotationZ = rot
                }
                FrogoAnimationComposeType.Wave -> {
                    transformOrigin = TransformOrigin(0.5f, 1f)
                    val rot = when {
                        progress < 0.2f -> 12f * (progress / 0.2f)
                        progress < 0.4f -> 12f - 24f * ((progress - 0.2f) / 0.2f)
                        progress < 0.6f -> -12f + 15f * ((progress - 0.4f) / 0.2f)
                        progress < 0.8f -> 3f - 6f * ((progress - 0.6f) / 0.2f)
                        else -> -3f * (1f - (progress - 0.8f) / 0.2f)
                    }
                    rotationZ = rot
                }
                FrogoAnimationComposeType.Wobble -> {
                    val dx = when {
                        progress < 0.15f -> -25f * (progress / 0.15f)
                        progress < 0.3f -> -25f + 45f * ((progress - 0.15f) / 0.15f)
                        progress < 0.45f -> 20f - 35f * ((progress - 0.3f) / 0.15f)
                        progress < 0.6f -> -15f + 25f * ((progress - 0.45f) / 0.15f)
                        progress < 0.75f -> 10f - 15f * ((progress - 0.6f) / 0.15f)
                        progress < 0.9f -> -5f + 5f * ((progress - 0.75f) / 0.15f)
                        else -> 0f
                    }
                    val rot = when {
                        progress < 0.15f -> -5f * (progress / 0.15f)
                        progress < 0.3f -> -5f + 8f * ((progress - 0.15f) / 0.15f)
                        progress < 0.45f -> 3f - 6f * ((progress - 0.3f) / 0.15f)
                        progress < 0.6f -> -3f + 5f * ((progress - 0.45f) / 0.15f)
                        progress < 0.75f -> 2f - 3f * ((progress - 0.6f) / 0.15f)
                        progress < 0.9f -> -1f + 1f * ((progress - 0.75f) / 0.15f)
                        else -> 0f
                    }
                    translationX = dx
                    rotationZ = rot
                }
                FrogoAnimationComposeType.FadeIn -> {
                    alpha = progress
                }
                FrogoAnimationComposeType.FadeOut -> {
                    alpha = 1f - progress
                }
                FrogoAnimationComposeType.ZoomIn -> {
                    scaleX = 0.5f + 0.5f * progress
                    scaleY = 0.5f + 0.5f * progress
                    alpha = progress
                }
                FrogoAnimationComposeType.ZoomOut -> {
                    scaleX = 1f - 0.5f * progress
                    scaleY = 1f - 0.5f * progress
                    alpha = 1f - progress
                }
            }
        }
    )
}
