package com.frogobox.sdk.delegate.piracy

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.frogobox.sdk.ext.getInstallerId
import com.frogobox.sdk.ext.showLogD
import com.frogobox.sdk.util.FrogoFunc
import com.github.javiersantos.piracychecker.*
import com.github.javiersantos.piracychecker.enums.Display
import com.github.javiersantos.piracychecker.enums.InstallerID
import com.github.javiersantos.piracychecker.utils.apkSignatures
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader


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

    private var piracyCheckerIsDebug = true
    private var piracyCheckerDisplay = Display.DIALOG
    private lateinit var piracyDelegateContext: Context
    private lateinit var piracyDelegateActivity: AppCompatActivity

    override fun setupPiracyDelegatesDebug(isDebug: Boolean) {
        piracyCheckerIsDebug = isDebug
    }

    override fun setupPiracyDelegate(context: Context, activity: AppCompatActivity) {
        showLogD<PiracyDelegatesImpl>("Context : $context")
        showLogD<PiracyDelegatesImpl>("Activity : $activity")
        piracyDelegateContext = context
        piracyDelegateActivity = activity
    }

    override fun setupPiracyDelegate(context: Context) {
        showLogD<PiracyDelegatesImpl>("Context : $context")
        piracyDelegateContext = context
    }

    override fun connectPiracyChecker() {
        if (piracyCheckerIsDebug) {
            showLogD<PiracyDelegatesImpl>("Connecting Piracy Checker")
            showLogD<PiracyDelegatesImpl>("IntallerId  : ${piracyDelegateContext.getInstallerId()}")
            showLogD<PiracyDelegatesImpl>("Debut State : Is Debug")
            showLogD<PiracyDelegatesImpl>("Please call setupPiracyDelegateDebug(<Debug Status>) first")
            showLogD<PiracyDelegatesImpl>("Please setupDebugMode() to false if using FrogoActivity")
            showLogD<PiracyDelegatesImpl>("Please set build variant to release")
        } else {
            if (isEmulator()) {
                showLogD<PiracyDelegatesImpl>("isEmulator")
                FrogoFunc.createDialogDefault(
                    piracyDelegateContext,
                    "Warning Prohibited Activity",
                    "This app is not intended for emulators",
                ) {
                    piracyDelegateActivity.finishAffinity()
                }
            } else {
                showLogD<PiracyDelegatesImpl>("isNotEmulator")
                verifyInstallerId()
            }
        }
    }

    override fun connectPiracyChecker(doIsEmulator: () -> Unit) {
        if (piracyCheckerIsDebug) {
            showLogD<PiracyDelegatesImpl>("Connecting Piracy Checker")
            showLogD<PiracyDelegatesImpl>("IntallerId  : ${piracyDelegateContext.getInstallerId()}")
            showLogD<PiracyDelegatesImpl>("Debut State : Is Debug")
            showLogD<PiracyDelegatesImpl>("Please call setupPiracyDelegateDebug(<Debug Status>) first")
            showLogD<PiracyDelegatesImpl>("Please setupDebugMode() to false if using FrogoActivity")
            showLogD<PiracyDelegatesImpl>("Please set build variant to release")
        } else {
            if (isEmulator()) {
                showLogD<PiracyDelegatesImpl>("isEmulator")
                FrogoFunc.createDialogDefault(
                    piracyDelegateContext,
                    "Warning Prohibited Activity",
                    "This app is not intended for emulators",
                ) {
                    doIsEmulator()
                }
            } else {
                showLogD<PiracyDelegatesImpl>("isNotEmulator")
                verifyInstallerId()
            }
        }
    }

    override fun checkRootMethod1(): Boolean {
        val buildTags = Build.TAGS
        return buildTags != null && buildTags.contains("test-keys")
    }

    override fun checkRootMethod2(): Boolean {
        val paths = arrayOf(
            "/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su",
            "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su",
            "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"
        )
        for (path in paths) {
            if (File(path).exists()) return true
        }
        return false
    }

    override fun checkRootMethod3(): Boolean {
        var process: Process? = null
        return try {
            process = Runtime.getRuntime().exec(arrayOf("/system/xbin/which", "su"))
            val `in` = BufferedReader(InputStreamReader(process.inputStream))
            `in`.readLine() != null
        } catch (t: Throwable) {
            false
        } finally {
            process?.destroy()
        }
    }

    override fun isEmulator(): Boolean {
        return (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")
                || Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.HARDWARE.contains("goldfish")
                || Build.HARDWARE.contains("ranchu")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MODEL.contains("VirtualBox")
                || Build.MANUFACTURER.contains("Genymotion")
                || Build.PRODUCT.contains("sdk_google")
                || Build.PRODUCT.contains("google_sdk")
                || Build.PRODUCT.contains("sdk")
                || Build.PRODUCT.contains("sdk_x86")
                || Build.PRODUCT.contains("vbox86p")
                || Build.PRODUCT.contains("emulator")
                || Build.PRODUCT.contains("simulator"))
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

    override fun verifySignature() {
        piracyDelegateContext.piracyChecker {
            display(piracyCheckerDisplay)
            enableSigningCertificates("478yYkKAQF+KST8y4ATKvHkYibo=") // Wrong signature
            // enableSigningCertificates("VHZs2aiTBiap/F+AYhYeppy0aF0=") // Right signature
        }.start()
    }

    override fun verifyInstallerId() {
        piracyDelegateContext.piracyChecker {
            display(piracyCheckerDisplay)
            enableInstallerId(
                InstallerID.GOOGLE_PLAY,
                InstallerID.AMAZON_APP_STORE,
                InstallerID.GALAXY_APPS,
                InstallerID.HUAWEI_APP_GALLERY
            )
        }.start()
    }

    override fun verifyUnauthorizedApps() {
        piracyDelegateContext.piracyChecker {
            display(piracyCheckerDisplay)
            enableUnauthorizedAppsCheck()
            blockIfUnauthorizedAppUninstalled("license_checker", "block")
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
            enableEmulatorCheck(true)
        }.start()
    }

    override fun showApkSignatures() {
        piracyDelegateContext.apkSignatures.forEach {
            showLogD<PiracyDelegatesImpl>("Signature This Apps : $it")
        }
    }

}