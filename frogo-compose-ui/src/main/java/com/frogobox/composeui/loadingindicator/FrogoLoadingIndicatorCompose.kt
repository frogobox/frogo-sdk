package com.frogobox.composeui.loadingindicator

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * A native Jetpack Compose Selector that runs the specified indicator style.
 *
 * @param modifier The modifier to apply.
 * @param indicatorName The name of the indicator style (e.g. "BallPulseIndicator", "BallClipRotateIndicator", "BallScaleIndicator", "LineScaleIndicator", "PacmanIndicator").
 * @param color The drawing color of the indicator shapes.
 * @param size The layout bounding box size.
 */
@Composable
fun FrogoLoadingIndicatorCompose(
    modifier: Modifier = Modifier,
    indicatorName: String = "BallPulseIndicator",
    color: Color = Color.White,
    size: Dp = 48.dp
) {
    Box(modifier = modifier.size(size)) {
        when (indicatorName) {
            "BallPulseIndicator", "BallPulse" -> BallPulseIndicatorCompose(color)
            "BallClipRotateIndicator", "BallClipRotate" -> BallClipRotateIndicatorCompose(color)
            "BallScaleIndicator", "BallScale" -> BallScaleIndicatorCompose(color)
            "LineScaleIndicator", "LineScale" -> LineScaleIndicatorCompose(color)
            "PacmanIndicator", "Pacman" -> PacmanIndicatorCompose(color)
            else -> BallPulseIndicatorCompose(color) // Default fallback
        }
    }
}

@Composable
fun BallPulseIndicatorCompose(color: Color) {
    val transition = rememberInfiniteTransition(label = "BallPulse")
    
    val scale1 by transition.animateFloat(
        initialValue = 1f, targetValue = 0.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(750, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
            initialStartOffset = StartOffset(120)
        ), label = "s1"
    )
    val scale2 by transition.animateFloat(
        initialValue = 1f, targetValue = 0.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(750, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
            initialStartOffset = StartOffset(240)
        ), label = "s2"
    )
    val scale3 by transition.animateFloat(
        initialValue = 1f, targetValue = 0.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(750, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
            initialStartOffset = StartOffset(360)
        ), label = "s3"
    )

    Canvas(modifier = Modifier.size(48.dp)) {
        val spacing = 4.dp.toPx()
        val radius = (size.minDimension - spacing * 2) / 6
        val centerY = size.height / 2

        // Left circle
        drawCircle(
            color = color,
            radius = radius * scale1,
            center = Offset(radius + spacing, centerY)
        )
        // Middle circle
        drawCircle(
            color = color,
            radius = radius * scale2,
            center = Offset(radius * 3 + spacing * 2, centerY)
        )
        // Right circle
        drawCircle(
            color = color,
            radius = radius * scale3,
            center = Offset(radius * 5 + spacing * 3, centerY)
        )
    }
}

@Composable
fun BallClipRotateIndicatorCompose(color: Color) {
    val transition = rememberInfiniteTransition(label = "BallClipRotate")
    
    val rotation by transition.animateFloat(
        initialValue = 0f, targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "rot"
    )
    
    val scaleVal by transition.animateFloat(
        initialValue = 1f, targetValue = 0.6f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "scale"
    )

    Canvas(modifier = Modifier.size(48.dp)) {
        val radius = size.minDimension / 2
        val strokeWidth = 3.dp.toPx()

        rotate(degrees = rotation) {
            scale(scale = scaleVal) {
                drawArc(
                    color = color,
                    startAngle = -45f,
                    sweepAngle = 270f,
                    useCenter = false,
                    topLeft = Offset(strokeWidth, strokeWidth),
                    size = Size((radius * 2) - strokeWidth * 2, (radius * 2) - strokeWidth * 2),
                    style = Stroke(width = strokeWidth)
                )
            }
        }
    }
}

@Composable
fun BallScaleIndicatorCompose(color: Color) {
    val transition = rememberInfiniteTransition(label = "BallScale")
    
    val scale by transition.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ), label = "scale"
    )
    
    val alpha by transition.animateFloat(
        initialValue = 1f, targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ), label = "alpha"
    )

    Canvas(modifier = Modifier.size(48.dp)) {
        drawCircle(
            color = color.copy(alpha = alpha),
            radius = (size.minDimension / 2) * scale,
            center = center
        )
    }
}

@Composable
fun LineScaleIndicatorCompose(color: Color) {
    val transition = rememberInfiniteTransition(label = "LineScale")
    
    val scaleYList = List(5) { i ->
        transition.animateFloat(
            initialValue = 1f, targetValue = 0.4f,
            animationSpec = infiniteRepeatable(
                animation = tween(600, easing = LinearOutSlowInEasing),
                repeatMode = RepeatMode.Reverse,
                initialStartOffset = StartOffset(100 * (i + 1))
            ), label = "s$i"
        )
    }

    Canvas(modifier = Modifier.size(48.dp)) {
        val width = size.width
        val height = size.height
        val lineSpacing = 3.dp.toPx()
        val lineWidth = (width - (lineSpacing * 4)) / 5

        for (i in 0 until 5) {
            val scaleY = scaleYList[i].value
            val currentLineHeight = height * scaleY
            val x = i * (lineWidth + lineSpacing)
            val y = (height - currentLineHeight) / 2

            drawRoundRect(
                color = color,
                topLeft = Offset(x, y),
                size = Size(lineWidth, currentLineHeight),
                cornerRadius = CornerRadius(lineWidth / 2, lineWidth / 2)
            )
        }
    }
}

@Composable
fun PacmanIndicatorCompose(color: Color) {
    val transition = rememberInfiniteTransition(label = "Pacman")
    
    val mouthAngle by transition.animateFloat(
        initialValue = 0f, targetValue = 45f,
        animationSpec = infiniteRepeatable(
            animation = tween(400, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "mouth"
    )

    val dotOffset by transition.animateFloat(
        initialValue = 1f, targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "dot"
    )

    Canvas(modifier = Modifier.size(48.dp)) {
        val radius = size.minDimension / 2
        val centerY = size.height / 2
        
        // Draw Pacman
        drawArc(
            color = color,
            startAngle = mouthAngle,
            sweepAngle = 360f - 2 * mouthAngle,
            useCenter = true,
            topLeft = Offset(0f, 0f),
            size = Size(radius * 1.5f, radius * 1.5f)
        )

        // Draw dot moving towards Pacman's mouth
        val dotRadius = 4.dp.toPx()
        val startX = size.width
        val endX = radius * 0.75f
        val currentX = endX + (startX - endX) * dotOffset

        drawCircle(
            color = color.copy(alpha = dotOffset),
            radius = dotRadius,
            center = Offset(currentX, centerY)
        )
    }
}
