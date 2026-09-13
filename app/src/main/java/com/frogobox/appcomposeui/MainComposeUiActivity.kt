package com.frogobox.appcomposeui

import androidx.compose.runtime.Composable
import com.frogobox.compose.view.FrogoComposeActivity

class MainComposeUiActivity : FrogoComposeActivity() {

    @Composable
    override fun SetupCompose() {
        ComposeUiMainScreen()
    }
}
