package com.frogobox.composeui.template.refresh

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Modern ergonomic wrapper for Material 3 [PullToRefreshBox].
 *
 * Provides smooth pull-to-refresh gestures and indicator without requiring
 * legacy Accompanist or XML SwipeRefreshLayout.
 *
 * Example usage:
 * ```kotlin
 * FrogoPullToRefresh(
 *     isRefreshing = isRefreshing,
 *     onRefresh = { viewModel.reload() }
 * ) {
 *     LazyColumn { ... }
 * }
 * ```
 *
 * Created by Muhammad Faisal Amir
 * github.com/amirisback
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FrogoPullToRefresh(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val state = rememberPullToRefreshState()

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        modifier = modifier.fillMaxSize(),
        state = state,
        indicator = {
            Indicator(
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshing = isRefreshing,
                state = state
            )
        }
    ) {
        content()
    }
}
