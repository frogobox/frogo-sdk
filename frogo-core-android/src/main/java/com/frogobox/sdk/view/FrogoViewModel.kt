package com.frogobox.sdk.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Created by faisalamir on 26/07/21
 * FrogoSDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * GitHub   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.      
 * All rights reserved
 *
 */
abstract class FrogoViewModel : ViewModel() {

    open fun onStart() {}
    open fun onClearDisposable() {}

    override fun onCleared() {
        super.onCleared()
        onClearDisposable()
    }

}

/**
 * Template ViewModel implementing Unidirectional Data Flow (UDF) / MVI pattern
 * using StateFlow (for UI State) and SharedFlow (for UI Effects/one-off events).
 *
 * Symmetrical implementation to FrogoComposeStateViewModel.
 *
 * @param STATE Represents the screen state.
 * @param EFFECT Represents one-off events (e.g. navigation, Toast).
 */
abstract class FrogoStateViewModel<STATE, EFFECT>(
    initialState: STATE
) : FrogoViewModel() {

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<STATE> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<EFFECT>()
    val uiEffect: SharedFlow<EFFECT> = _uiEffect.asSharedFlow()

    /**
     * Retrieves the current UI state.
     */
    protected val currentState: STATE
        get() = _uiState.value

    /**
     * Updates the UI state atomically.
     */
    protected fun updateState(reducer: STATE.() -> STATE) {
        _uiState.value = _uiState.value.reducer()
    }

    /**
     * Emits a one-off UI effect to be consumed by the View.
     */
    protected fun emitEffect(effect: EFFECT) {
        viewModelScope.launch {
            _uiEffect.emit(effect)
        }
    }
}
