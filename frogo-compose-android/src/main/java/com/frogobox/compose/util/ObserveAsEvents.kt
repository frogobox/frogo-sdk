package com.frogobox.compose.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

/**
 * Ergonomic Composable helper for collecting one-off UI side-effects (e.g. Navigation, Toast, Snackbar)
 * from a [Flow] in a safe, lifecycle-aware manner.
 *
 * Only collects emissions when the lifecycle is at least in [Lifecycle.State.STARTED],
 * preventing dropped events or memory leaks during backgrounding.
 *
 * Example usage:
 * ```kotlin
 * @Composable
 * fun UserScreen(viewModel: UserViewModel = viewModel()) {
 *     ObserveAsEvents(viewModel.uiEffect) { effect ->
 *         when (effect) {
 *             is UserEffect.ShowToast -> Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
 *             is UserEffect.NavigateToDetail -> navController.navigate("detail/${effect.id}")
 *         }
 *     }
 * }
 * ```
 *
 * Created by Muhammad Faisal Amir
 * github.com/amirisback
 */
@Composable
fun <T> ObserveAsEvents(
    flow: Flow<T>,
    key1: Any? = true,
    key2: Any? = null,
    onEvent: (T) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner.lifecycle, key1, key2) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate) {
                flow.collect(onEvent)
            }
        }
    }
}
