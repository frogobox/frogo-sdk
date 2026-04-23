package com.frogobox.appcomposeui

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.frogobox.compose.view.FrogoComposeActivity
import com.frogobox.composeui.widget.FrogoButton

class MainComposeUiActivity : FrogoComposeActivity() {

    @Composable
    override fun setupCompose() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            FrogoButton(
                text = "Click Me! (Frogo Compose UI)",
                onClick = {
                    Toast.makeText(this@MainComposeUiActivity, "Frogo Compose UI Button Clicked!", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}
