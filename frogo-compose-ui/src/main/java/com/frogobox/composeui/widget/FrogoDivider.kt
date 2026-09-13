package com.frogobox.composeui.widget

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun FrogoDivider(
    modifier: Modifier = Modifier.fillMaxWidth(),
    thickness: Dp = DividerDefaults.Thickness,
    color: Color = DividerDefaults.color
) {
    HorizontalDivider(
        modifier = modifier,
        thickness = thickness,
        color = color
    )
}

@Composable
fun FrogoVerticalDivider(
    modifier: Modifier = Modifier.fillMaxHeight(),
    thickness: Dp = DividerDefaults.Thickness,
    color: Color = DividerDefaults.color
) {
    VerticalDivider(
        modifier = modifier,
        thickness = thickness,
        color = color
    )
}
