package com.frogobox.ads.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.frogobox.ads.delegate.UnityAdDelegates
import com.frogobox.ads.delegate.UnityAdDelegatesImpl

/**
 * Created by Faisal Amir on 07/02/23
 * https://github.com/amirisback
 */


abstract class UnityAdActivity : AppCompatActivity(),
    UnityAdDelegates by UnityAdDelegatesImpl() {

    companion object {
        val TAG: String = UnityAdActivity::class.java.simpleName
    }

    open fun setupMonetized() {
        setupUnityAdDelegates(this)
    }

    open fun setupContentView() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupContentView()
        setupMonetized()
    }

}