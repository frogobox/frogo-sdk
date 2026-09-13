package com.frogobox.compose.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class FrogoComposeViewModel : ViewModel() {

    open fun onStart() {}
    open fun onClearDisposable() {}

    override fun onCleared() {
        super.onCleared()
        onClearDisposable()
    }

}

/**
 * Base ViewModel for Jetpack Compose that implements Unidirectional Data Flow (UDF).
 *
 * It provides structured handling for UI state and one-off side effects.
 *
 * @param STATE Represents the state of the UI screen (e.g. Loading, Content, Error states).
 * @param EFFECT Represents one-off events (e.g. Navigation, Toast, showing a snackbar).
 */
abstract class FrogoComposeStateViewModel<STATE, EFFECT>(
    initialState: STATE
) : FrogoComposeViewModel() {

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<STATE> = _uiState.asStateFlow()

    private val _uiEffect = Channel<EFFECT>(Channel.BUFFERED)
    val uiEffect: Flow<EFFECT> = _uiEffect.receiveAsFlow()

    /**
     * Retrieves the current UI state.
     */
    protected val currentState: STATE
        get() = _uiState.value

    /**
     * Updates the UI state atomically.
     */
    protected fun updateState(reducer: STATE.() -> STATE) {
        _uiState.update { it.reducer() }
    }

    /**
     * Emits a one-off UI effect to be consumed by the UI.
     */
    protected fun emitEffect(effect: EFFECT) {
        viewModelScope.launch {
            _uiEffect.send(effect)
        }
    }
}

