package com.frogobox.ads.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.frogobox.ads.delegate.AdmobDelegates
import com.frogobox.ads.delegate.AdmobDelegatesImpl
import com.frogobox.ads.delegate.FrogoAdDelegates
import com.frogobox.ads.delegate.FrogoAdDelegatesImpl
import com.frogobox.ads.delegate.UnityAdDelegates
import com.frogobox.ads.delegate.UnityAdDelegatesImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Faisal Amir on 07/02/23
 * https://github.com/amirisback
 */


abstract class AdActivity : AppCompatActivity(),
    AdmobDelegates by AdmobDelegatesImpl(),
    UnityAdDelegates by UnityAdDelegatesImpl(),
    FrogoAdDelegates by FrogoAdDelegatesImpl() {

    companion object {
        val TAG: String = AdActivity::class.java.simpleName
    }

    open fun setupMonetized() {
        setupAdmobDelegates(this)
        CoroutineScope(Dispatchers.IO).launch {
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