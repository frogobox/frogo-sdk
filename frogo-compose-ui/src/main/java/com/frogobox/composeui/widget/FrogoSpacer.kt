package com.frogobox.composeui.widget

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun FrogoSpacer(
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier)
}

@Composable
fun FrogoSpacerHeight(
    height: Dp = 16.dp
) {
    Spacer(modifier = Modifier.height(height))
}

@Composable
fun FrogoSpacerWidth(
    width: Dp = 16.dp
) {
    Spacer(modifier = Modifier.width(width))
}

@Composable
fun FrogoSpacerSmallHeight() {
    FrogoSpacerHeight(8.dp)
}

@Composable
fun FrogoSpacerMediumHeight() {
    FrogoSpacerHeight(16.dp)
}

@Composable
fun FrogoSpacerLargeHeight() {
    FrogoSpacerHeight(24.dp)
}

@Composable
fun FrogoSpacerSmallWidth() {
    FrogoSpacerWidth(8.dp)
}

@Composable
fun FrogoSpacerMediumWidth() {
    FrogoSpacerWidth(16.dp)
}

@Composable
fun FrogoSpacerLargeWidth() {
    FrogoSpacerWidth(24.dp)
}
