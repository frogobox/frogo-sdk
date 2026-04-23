package com.frogobox.appcompose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.frogobox.compose.view.FrogoComposeActivity

class MainComposeActivity : FrogoComposeActivity() {

    @Composable
    override fun setupCompose() {
        Box(
            modifier = Modifier.Companion.fillMaxSize(),
            contentAlignment = Alignment.Companion.Center
        ) {
            Text(
                color = Color.Companion.White,
                text = "Hello from Frogo Compose!"
            )
        }
    }
}