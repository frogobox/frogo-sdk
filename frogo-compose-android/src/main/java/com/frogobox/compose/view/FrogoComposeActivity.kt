package com.frogobox.compose.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable

abstract class FrogoComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        setupDelegates()
        setContent {
            setupCompose()
        }
    }

    open fun setupViewModel() {}
    
    open fun setupDelegates() {}
    
    @Composable
    abstract fun setupCompose()

}
