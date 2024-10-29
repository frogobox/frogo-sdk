package com.frogobox.appuikit.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.frogobox.appuikit.model.People
import com.frogobox.appuikit.ui.theme.FrogoAndroidUIKitTheme

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            FrogoAndroidUIKitTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                }
            }
        }
    }

    private fun setupData(): MutableList<People> {
        val data = mutableListOf<People>()
        data.add(People("Amir", "Programmer"))
        data.add(People("Amir", "Programmer"))
        data.add(People("Amir", "Programmer"))
        data.add(People("Amir", "Programmer"))
        data.add(People("Amir", "Programmer"))
        return data
    }

}

@Composable
fun Greeting(text: String) {
    Text(text = text)
}

@Composable
fun DefaultPreview() {
    FrogoAndroidUIKitTheme {
        Greeting("Android")
    }
}