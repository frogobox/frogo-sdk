package com.frogobox.composeui.widget

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun FrogoDivider(
    modifier: Modifier = Modifier.fillMaxWidth(),
    thickness: Dp = DividerDefaults.Thickness,
    color: Color = DividerDefaults.color
) {
    Divider(
        modifier = modifier,
        thickness = thickness,
        color = color
    )
}

@Composable
fun FrogoVerticalDivider(
    modifier: Modifier = Modifier.fillMaxHeight().width(DividerDefaults.Thickness),
    thickness: Dp = DividerDefaults.Thickness,
    color: Color = DividerDefaults.color
) {
    Divider(
        modifier = modifier,
        thickness = thickness,
        color = color
    )
}
