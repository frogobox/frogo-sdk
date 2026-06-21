package com.frogobox.appcompose

import android.os.Bundle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.frogobox.compose.view.FrogoComposeNavActivity

class SampleNavComposeActivity : FrogoComposeNavActivity() {

    override fun onCreateExt(savedInstanceState: Bundle?) {
        super.onCreateExt(savedInstanceState)
        // Additional configuration during onCreate if needed
    }

    @Composable
    override fun SetupNavigation(navController: NavHostController) {
        NavHost(navController = navController, startDestination = "first") {
            composable("first") {
                FirstScreen(
                    onNavigateToSecond = { navController.navigate("second") },
                    onShowBottomSheet = {
                        SampleBottomSheetFragment().show(supportFragmentManager, "bottom_sheet")
                    }
                )
            }
            composable("second") {
                SecondScreen(
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }

    @Composable
    private fun FirstScreen(onNavigateToSecond: () -> Unit, onShowBottomSheet: () -> Unit) {
        Scaffold { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "First Screen (Navigation Compose)", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onNavigateToSecond) {
                    Text("Go to Second Screen")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onShowBottomSheet) {
                    Text("Open Bottom Sheet Dialog")
                }
            }
        }
    }

    @Composable
    private fun SecondScreen(onBack: () -> Unit) {
        Scaffold { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Second Screen (Navigation Compose)", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onBack) {
                    Text("Go Back")
                }
            }
        }
    }
}
