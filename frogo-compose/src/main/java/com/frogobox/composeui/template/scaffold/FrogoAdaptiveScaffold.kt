package com.frogobox.composeui.template.scaffold

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Window width size classifications adhering to Material Design 3 guidelines.
 */
enum class FrogoWindowWidthSizeClass {
    /** Phones in portrait, typically < 600dp width */
    Compact,
    /** Foldables unfolded, small tablets, phones in landscape (600dp - 839dp width) */
    Medium,
    /** Large tablets, desktops, large foldables (>= 840dp width) */
    Expanded
}

/**
 * Adaptive Scaffold template that dynamically adapts its layout chrome
 * (Navigation Bar, Navigation Rail, or Permanent Navigation Drawer)
 * based on available screen width.
 *
 * Automatically infers [FrogoWindowWidthSizeClass] via [BoxWithConstraints].
 *
 * Created by Muhammad Faisal Amir
 * github.com/amirisback
 */
@Composable
fun FrogoAdaptiveScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    navigationRail: @Composable () -> Unit = {},
    navigationDrawer: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable (paddingValues: PaddingValues, windowSizeClass: FrogoWindowWidthSizeClass) -> Unit
) {
    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val windowSizeClass = when {
            maxWidth < 600.dp -> FrogoWindowWidthSizeClass.Compact
            maxWidth < 840.dp -> FrogoWindowWidthSizeClass.Medium
            else -> FrogoWindowWidthSizeClass.Expanded
        }

        when (windowSizeClass) {
            FrogoWindowWidthSizeClass.Compact -> {
                // Phone layout: TopBar + BottomBar
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = topBar,
                    bottomBar = bottomBar,
                    floatingActionButton = floatingActionButton
                ) { innerPadding ->
                    content(innerPadding, windowSizeClass)
                }
            }

            FrogoWindowWidthSizeClass.Medium -> {
                // Foldable / Small Tablet layout: NavigationRail on side + TopBar
                Row(modifier = Modifier.fillMaxSize()) {
                    navigationRail()
                    Scaffold(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        topBar = topBar,
                        floatingActionButton = floatingActionButton
                    ) { innerPadding ->
                        content(innerPadding, windowSizeClass)
                    }
                }
            }

            FrogoWindowWidthSizeClass.Expanded -> {
                // Large Tablet / Desktop layout: Permanent NavigationDrawer on side
                Row(modifier = Modifier.fillMaxSize()) {
                    navigationDrawer()
                    Scaffold(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        topBar = topBar,
                        floatingActionButton = floatingActionButton
                    ) { innerPadding ->
                        content(innerPadding, windowSizeClass)
                    }
                }
            }
        }
    }
}
