package com.frogobox.sdk.delegate.piracy

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.frogobox.sdk.ext.showLogD
import com.github.javiersantos.piracychecker.*
import com.github.javiersantos.piracychecker.enums.Display
import com.github.javiersantos.piracychecker.enums.InstallerID
import com.github.javiersantos.piracychecker.utils.apkSignatures


/*
 * Created by faisalamir on 01/07/22
 * FrogoSDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2022 Frogobox Media Inc.      
 * All rights reserved
 *
 */

class PiracyDelegatesImpl : PiracyDelegates {

    companion object {
        val TAG: String = PiracyDelegatesImpl::class.java.simpleName
    }

    private var piracyCheckerDisplay = Display.DIALOG
    private lateinit var piracyDelegateContext: Context

    override fun setupPiracyDelegate(context: Context) {
        showLogD<PiracyDelegatesImpl>("Context : $context")
        piracyDelegateContext = context
    }

    override fun verifySignature() {
        piracyDelegateContext.piracyChecker {
            display(piracyCheckerDisplay)
            enableSigningCertificates("478yYkKAQF+KST8y4ATKvHkYibo=") // Wrong signature
            // enableSigningCertificates("VHZs2aiTBiap/F+AYhYeppy0aF0=") // Right signature
        }.start()
    }

    override fun readSignature() {
        val dialogMessage = StringBuilder()
        piracyDelegateContext.apkSignatures.forEach {
            showLogD<PiracyDelegatesImpl>("Signature : $it")
            dialogMessage.append("* ").append(it).append("\n")
        }
        AlertDialog.Builder(piracyDelegateContext)
            .setTitle("APK")
            .setMessage(dialogMessage.toString())
            .show()
    }

    override fun verifyInstallerId() {
        piracyDelegateContext.piracyChecker {
            display(piracyCheckerDisplay)
            enableInstallerId(InstallerID.GOOGLE_PLAY)
        }.start()
    }

    override fun verifyUnauthorizedApps() {
        piracyDelegateContext.piracyChecker {
            display(piracyCheckerDisplay)
            enableUnauthorizedAppsCheck()
            // blockIfUnauthorizedAppUninstalled("license_checker", "block")
        }.start()
    }

    override fun verifyStores() {
        piracyDelegateContext.piracyChecker {
            display(piracyCheckerDisplay)
            enableStoresCheck()
        }.start()
    }

    override fun verifyDebug() {
        piracyDelegateContext.piracyChecker {
            display(piracyCheckerDisplay)
            enableDebugCheck()
            callback {
                allow {
                    // Do something when the user is allowed to use the app
                }
                doNotAllow { piracyCheckerError, pirateApp ->
                    // You can either do something specific when the user is not allowed to use the app
                    // Or manage the error, using the 'error' parameter, yourself (Check errors at {@link PiracyCheckerError}).

                    // Additionally, if you enabled the check of pirate apps and/or third-party stores, the 'app' param
                    // is the app that has been detected on device. App can be null, and when null, it means no pirate app or store was found,
                    // or you disabled the check for those apps.
                    // This allows you to let users know the possible reasons why license is been invalid.
                }
                onError { error ->
                    // This method is not required to be implemented/overriden but...
                    // You can either do something specific when an error occurs while checking the license,
                    // Or manage the error, using the 'error' parameter, yourself (Check errors at {@link PiracyCheckerError}).
                }
            }
        }.start()
    }

    override fun verifyEmulator() {
        piracyDelegateContext.piracyChecker {
            display(piracyCheckerDisplay)
            enableEmulatorCheck(false)
        }.start()
    }

    override fun showApkSignatures() {
        piracyDelegateContext.apkSignatures.forEach {
            showLogD<PiracyDelegatesImpl>("Signature This Apps : $it")
        }
    }

}