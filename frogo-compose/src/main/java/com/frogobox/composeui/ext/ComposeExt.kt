package com.frogobox.composeui.ext

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import com.frogobox.composeui.animation.FrogoAnimationComposeType
import com.frogobox.composeui.animation.frogoAnimationCompose
import com.frogobox.composeui.fireworks.FrogoFireworksStateCompose

/**
 * Extension modifier that applies a default attention animation (Rubberband)
 * to a Composable, mimicking the legacy [View.frogoStartAnimation].
 */
fun Modifier.frogoStartAnimationCompose(
    isRepeat: Boolean = false,
    trigger: Any? = null
): Modifier = this.frogoAnimationCompose(
    type = FrogoAnimationComposeType.Rubberband,
    trigger = trigger,
    durationMillis = 1500,
    repeat = isRepeat
)

/**
 * Extension modifier that registers a click listener and spawns a fireworks explosion
 * directly at the exact tap coordinates inside the [FrogoFireworksStateCompose].
 */
fun Modifier.frogoClickWithFireworksCompose(
    state: FrogoFireworksStateCompose,
    colors: List<Color> = listOf(Color.Red, Color.Yellow, Color.Green, Color.Blue, Color.Magenta, Color.Cyan),
    count: Int = 40,
    onClick: () -> Unit
): Modifier = this.pointerInput(state, colors, count) {
    detectTapGestures(
        onTap = { offset ->
            state.explode(offset.x, offset.y, colors, count)
            onClick()
        }
    )
}
