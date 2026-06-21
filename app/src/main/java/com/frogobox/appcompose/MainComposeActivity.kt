package com.frogobox.appcompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.frogobox.compose.view.FrogoComposeActivity
import com.frogobox.sdk.ext.startActivityExt

class MainComposeActivity : FrogoComposeActivity() {

    @Composable
    override fun SetupCompose() {
        Scaffold { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Frogo Compose Samples",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Sample 1: Single Activity Compose (this class itself demonstrates FrogoComposeActivity)
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        startActivityExt<SampleNavComposeActivity> {}
                    }
                ) {
                    Text("Navigation Compose (NavHost)")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Sample 2: Fragment Compose hosted in an Activity
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        startActivityExt<SampleFragmentComposeActivity> {}
                    }
                ) {
                    Text("Fragment Compose (SampleFragment)")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Sample 3: Bottom Sheet Fragment Compose (shown as dialog)
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        SampleBottomSheetFragment().show(supportFragmentManager, "bottom_sheet")
                    }
                ) {
                    Text("Bottom Sheet Fragment Compose")
                }
            }
        }
    }
}
