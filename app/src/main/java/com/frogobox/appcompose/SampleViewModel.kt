package com.frogobox.appcompose

import com.frogobox.compose.view.FrogoComposeStateViewModel

data class SampleUiState(
    val title: String = "Frogo SDK Compose Sample",
    val description: String = "This is a demonstration of UDF UI state.",
    val counter: Int = 0
)

sealed interface SampleUiEffect {
    data class ShowToast(val message: String) : SampleUiEffect
}

class SampleViewModel : FrogoComposeStateViewModel<SampleUiState, SampleUiEffect>(SampleUiState()) {

    fun incrementCounter() {
        updateState {
            copy(counter = counter + 1)
        }
        emitEffect(SampleUiEffect.ShowToast("Counter incremented to ${currentState.counter}"))
    }
}
