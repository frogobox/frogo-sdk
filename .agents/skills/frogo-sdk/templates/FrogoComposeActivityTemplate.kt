package com.example.app.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.frogobox.compose.view.FrogoComposeActivity
import com.frogobox.compose.viewmodel.FrogoComposeStateViewModel
import com.frogobox.composeui.template.appbar.FrogoTopAppBar
import com.frogobox.composeui.template.scaffold.FrogoScaffold

// 1. UI State & Side-Effects
data class ExampleUiState(
    val title: String = "Frogo SDK Screen",
    val isLoading: Boolean = false
)

sealed interface ExampleUiEffect {
    data class ShowToast(val message: String) : ExampleUiEffect
}

// 2. State ViewModel using FrogoComposeStateViewModel
class ExampleViewModel : FrogoComposeStateViewModel<ExampleUiState, ExampleUiEffect>(ExampleUiState()) {
    fun updateTitle(newTitle: String) {
        updateState { copy(title = newTitle) }
        emitEffect(ExampleUiEffect.ShowToast("Title updated!"))
    }
}

// 3. FrogoComposeActivity (Edge-to-Edge and insets handled automatically)
class ExampleActivity : FrogoComposeActivity() {

    private val viewModel: ExampleViewModel by viewModels()

    override fun onCreateExt(savedInstanceState: Bundle?) {
        super.onCreateExt(savedInstanceState)
        // Additional setup if needed
    }

    @Composable
    override fun SetupCompose() {
        val state by viewModel.uiState.collectAsStateWithLifecycle()

        FrogoScaffold(
            topBar = {
                FrogoTopAppBar(title = state.title)
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Hello from Frogo SDK Compose!")
            }
        }
    }
}
