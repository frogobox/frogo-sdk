package com.frogobox.composeui.template.scaffold

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun FrogoScaffoldWithFab(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    fabIcon: ImageVector,
    onFabClick: () -> Unit,
    fabContentDescription: String? = null,
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        floatingActionButtonPosition = floatingActionButtonPosition,
        floatingActionButton = {
            FloatingActionButton(onClick = onFabClick) {
                Icon(imageVector = fabIcon, contentDescription = fabContentDescription)
            }
        },
        content = content
    )
}
