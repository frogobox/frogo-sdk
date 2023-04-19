package com.frogobox.appsdk.main

import androidx.viewbinding.ViewBinding
import com.frogobox.appsdk.core.BaseActivity
import com.frogobox.sdk.delegate.piracy.FrogoPiracyCallback
import com.frogobox.sdk.delegate.piracy.FrogoPiracyDialogCallback
import com.frogobox.sdk.delegate.piracy.PiracyDelegates
import com.frogobox.sdk.delegate.piracy.PiracyDelegatesImpl
import com.frogobox.sdk.delegate.piracy.util.PiracyMessage

/**
 * Created by Faisal Amir on 19/03/23
 * https://github.com/amirisback
 */


abstract class CoreMainActivity<VB : ViewBinding> : BaseActivity<VB>(),
    PiracyDelegates by PiracyDelegatesImpl() {

    override fun setupDelegates() {
        super.setupDelegates()
        setupPiracyDelegate(this, this)
        setupPiracyDelegatesDebug(setupDebugMode())
    }

    override fun setupDebugMode(): Boolean {
        return com.frogobox.appsdk.BuildConfig.DEBUG
    }

    override fun setupPiracyMode() {
        connectPiracyChecker(object : FrogoPiracyCallback {
            override fun doOnPirated(message: PiracyMessage) {

                showPiracedDialog(message, object : FrogoPiracyDialogCallback {
                    override fun doOnPirated(message: PiracyMessage) {
                        openPlaystore(packageName)
                    }

                })
            }
        })
    }

}