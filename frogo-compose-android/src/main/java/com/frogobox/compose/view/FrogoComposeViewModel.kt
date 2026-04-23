package com.frogobox.compose.view

import androidx.lifecycle.ViewModel

abstract class FrogoComposeViewModel : ViewModel() {

    open fun onStart() {}
    open fun onClearDisposable() {}

    override fun onCleared() {
        super.onCleared()
        onClearDisposable()
    }

}
