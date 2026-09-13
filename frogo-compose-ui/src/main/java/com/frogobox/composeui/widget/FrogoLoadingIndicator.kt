package com.frogobox.composeui.widget

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.frogobox.composeui.loadingindicator.FrogoLoadingIndicatorView

/**
 * Created by Faisal Amir
 * Compose wrapper for the legacy [FrogoLoadingIndicatorView].
 *
 * Uses [AndroidView] to host the 28 custom canvas-drawn indicators
 * within Jetpack Compose layouts without rewriting their drawing logic.
 *
 * @param modifier         The modifier to apply to the indicator.
 * @param indicatorName    Name of the indicator class (e.g. "BallPulseIndicator").
 * @param indicatorColor   The color of the indicator drawable.
 * @param size             The size of the indicator view.
 */
@Composable
fun FrogoLoadingIndicator(
    modifier: Modifier = Modifier,
    indicatorName: String = "BallPulseIndicator",
    indicatorColor: Color = Color.White,
    size: Dp = 48.dp
) {
    AndroidView(
        factory = { context ->
            FrogoLoadingIndicatorView(context).apply {
                tag = indicatorName
                setIndicator(indicatorName)
                setIndicatorColor(indicatorColor.toArgb())
            }
        },
        modifier = modifier.size(size),
        update = { view ->
            if (view.tag != indicatorName) {
                view.tag = indicatorName
                view.setIndicator(indicatorName)
            }
            view.setIndicatorColor(indicatorColor.toArgb())
        }
    )
}
