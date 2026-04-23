package com.frogobox.composeui.template.appbar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FrogoBottomAppBar(
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {},
    floatingActionButton: @Composable (() -> Unit)? = null,
) {
    BottomAppBar(
        actions = actions,
        modifier = modifier,
        floatingActionButton = floatingActionButton
    )
}
