package com.frogobox.ads.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.frogobox.ads.delegate.AdmobDelegates
import com.frogobox.ads.delegate.AdmobDelegatesImpl
import kotlinx.coroutines.launch


/**
 * Created by Faisal Amir on 07/02/23
 * https://github.com/amirisback
 */


abstract class AdmobActivity : AppCompatActivity(),
    AdmobDelegates by AdmobDelegatesImpl() {

    companion object {
        val TAG: String = AdmobActivity::class.java.simpleName
    }

    open fun setupMonetized() {
        setupAdmobDelegates(this)
        lifecycleScope.launch {
            // Initialize the Google Mobile Ads SDK on a background thread.
            setupAdmobApp()
        }
    }

    open fun setupContentView() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupContentView()
        setupMonetized()
    }

}