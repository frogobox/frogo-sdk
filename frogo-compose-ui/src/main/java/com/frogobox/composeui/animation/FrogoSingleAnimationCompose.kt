package com.frogobox.composeui.animation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.ui.unit.IntOffset

/**
 * Created by Faisal Amir on 21/05/26
 * Native Jetpack Compose equivalents for legacy activity transition animations.
 * Use these with standard Compose Navigation or [AnimatedContent]/[AnimatedVisibility].
 */
object FrogoSingleAnimationCompose {

    val slideLeftEnter: EnterTransition = slideInHorizontally(
        animationSpec = tween(500),
        initialOffsetX = { it }
    ) + fadeIn(animationSpec = tween(500))

    val slideLeftExit: ExitTransition = slideOutHorizontally(
        animationSpec = tween(500),
        targetOffsetX = { -it }
    ) + fadeOut(animationSpec = tween(500))

    val slideRightEnter: EnterTransition = slideInHorizontally(
        animationSpec = tween(500),
        initialOffsetX = { -it }
    ) + fadeIn(animationSpec = tween(500))

    val slideRightExit: ExitTransition = slideOutHorizontally(
        animationSpec = tween(500),
        targetOffsetX = { it }
    ) + fadeOut(animationSpec = tween(500))

    val slideDownEnter: EnterTransition = slideInVertically(
        animationSpec = tween(500),
        initialOffsetY = { -it }
    ) + fadeIn(animationSpec = tween(500))

    val slideDownExit: ExitTransition = slideOutVertically(
        animationSpec = tween(500),
        targetOffsetY = { it }
    ) + fadeOut(animationSpec = tween(500))

    val slideUpEnter: EnterTransition = slideInVertically(
        animationSpec = tween(500),
        initialOffsetY = { it }
    ) + fadeIn(animationSpec = tween(500))

    val slideUpExit: ExitTransition = slideOutVertically(
        animationSpec = tween(500),
        targetOffsetY = { -it }
    ) + fadeOut(animationSpec = tween(500))

    val zoomEnter: EnterTransition = scaleIn(
        animationSpec = tween(500),
        initialScale = 0.5f
    ) + fadeIn(animationSpec = tween(500))

    val zoomExit: ExitTransition = scaleOut(
        animationSpec = tween(500),
        targetScale = 1.5f
    ) + fadeOut(animationSpec = tween(500))

    val fadeEnter: EnterTransition = fadeIn(animationSpec = tween(500))

    val fadeExit: ExitTransition = fadeOut(animationSpec = tween(500))

    val windmillEnter: EnterTransition = scaleIn(
        animationSpec = tween(500),
        initialScale = 0.2f
    ) + fadeIn(animationSpec = tween(500))

    val windmillExit: ExitTransition = scaleOut(
        animationSpec = tween(500),
        targetScale = 0.2f
    ) + fadeOut(animationSpec = tween(500))

    val spinEnter: EnterTransition = scaleIn(
        animationSpec = tween(500),
        initialScale = 0.1f
    ) + fadeIn(animationSpec = tween(500))

    val spinExit: ExitTransition = scaleOut(
        animationSpec = tween(500),
        targetScale = 0.1f
    ) + fadeOut(animationSpec = tween(500))

    val diagonalEnter: EnterTransition = slideIn(
        animationSpec = tween(500),
        initialOffset = { IntOffset(it.width, -it.height) }
    ) + fadeIn(animationSpec = tween(500))

    val diagonalExit: ExitTransition = slideOut(
        animationSpec = tween(500),
        targetOffset = { IntOffset(-it.width, it.height) }
    ) + fadeOut(animationSpec = tween(500))

    val splitEnter: EnterTransition = expandIn(
        animationSpec = tween(500)
    ) + fadeIn(animationSpec = tween(500))

    val splitExit: ExitTransition = shrinkOut(
        animationSpec = tween(500)
    ) + fadeOut(animationSpec = tween(500))

    val shrinkEnter: EnterTransition = scaleIn(
        animationSpec = tween(500),
        initialScale = 1.2f
    ) + fadeIn(animationSpec = tween(500))

    val shrinkExit: ExitTransition = scaleOut(
        animationSpec = tween(500),
        targetScale = 0.8f
    ) + fadeOut(animationSpec = tween(500))

    val cardEnter: EnterTransition = scaleIn(
        animationSpec = tween(500),
        initialScale = 0.9f
    ) + slideInHorizontally(animationSpec = tween(500)) { it / 2 } + fadeIn(animationSpec = tween(500))

    val cardExit: ExitTransition = scaleOut(
        animationSpec = tween(500),
        targetScale = 1.1f
    ) + slideOutHorizontally(animationSpec = tween(500)) { -it / 2 } + fadeOut(animationSpec = tween(500))

    val inAndOutEnter: EnterTransition = scaleIn(
        animationSpec = tween(500),
        initialScale = 0.8f
    ) + fadeIn(animationSpec = tween(500))

    val inAndOutExit: ExitTransition = scaleOut(
        animationSpec = tween(500),
        targetScale = 0.8f
    ) + fadeOut(animationSpec = tween(500))

    val swipeLeftEnter: EnterTransition = slideInHorizontally(
        animationSpec = tween(500),
        initialOffsetX = { it }
    ) + fadeIn(animationSpec = tween(500))

    val swipeLeftExit: ExitTransition = slideOutHorizontally(
        animationSpec = tween(500),
        targetOffsetX = { -it }
    ) + fadeOut(animationSpec = tween(500))

    val swipeRightEnter: EnterTransition = slideInHorizontally(
        animationSpec = tween(500),
        initialOffsetX = { -it }
    ) + fadeIn(animationSpec = tween(500))

    val swipeRightExit: ExitTransition = slideOutHorizontally(
        animationSpec = tween(500),
        targetOffsetX = { it }
    ) + fadeOut(animationSpec = tween(500))
}
