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
    height: Dp = 16.dp,
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier.height(height))
}

@Composable
fun FrogoSpacerWidth(
    width: Dp = 16.dp,
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier.width(width))
}

@Composable
fun FrogoSpacerSmallHeight(modifier: Modifier = Modifier) {
    FrogoSpacerHeight(8.dp, modifier)
}

@Composable
fun FrogoSpacerMediumHeight(modifier: Modifier = Modifier) {
    FrogoSpacerHeight(16.dp, modifier)
}

@Composable
fun FrogoSpacerLargeHeight(modifier: Modifier = Modifier) {
    FrogoSpacerHeight(24.dp, modifier)
}

@Composable
fun FrogoSpacerSmallWidth(modifier: Modifier = Modifier) {
    FrogoSpacerWidth(8.dp, modifier)
}

@Composable
fun FrogoSpacerMediumWidth(modifier: Modifier = Modifier) {
    FrogoSpacerWidth(16.dp, modifier)
}

@Composable
fun FrogoSpacerLargeWidth(modifier: Modifier = Modifier) {
    FrogoSpacerWidth(24.dp, modifier)
}
