package com.frogobox.appcompose

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.frogobox.compose.view.FrogoComposeFragment

class SampleFragment : FrogoComposeFragment() {

    private lateinit var sampleViewModel: SampleViewModel

    override fun setupViewModel() {
        super.setupViewModel()
        sampleViewModel = SampleViewModel()
    }

    @Composable
    override fun setupCompose() {
        val vm: SampleViewModel = viewModel()
        val state by vm.uiState.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Sample Fragment Compose", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = state.description, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Counter: ${state.counter}", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                vm.incrementCounter()
                Toast.makeText(context, "Clicked from Compose Fragment!", Toast.LENGTH_SHORT).show()
            }) {
                Text("Increment Counter")
            }
        }
    }
}
