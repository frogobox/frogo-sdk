package com.frogobox.appcompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Host Activity for SampleFragment (Compose-based Fragment).
 * Demonstrates hosting a FrogoComposeFragment inside an Activity.
 */
class SampleFragmentComposeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, SampleFragment())
                .commit()
        }
    }
}
