package com.frogobox.sdk.delegate.piracy

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.frogobox.sdk.R
import com.frogobox.sdk.ext.getInstallerId
import com.frogobox.sdk.ext.showLogD
import com.frogobox.sdk.piracychecker.callback
import com.frogobox.sdk.piracychecker.doNotAllow
import com.frogobox.sdk.piracychecker.enums.Display
import com.frogobox.sdk.piracychecker.enums.InstallerID
import com.frogobox.sdk.piracychecker.piracyChecker
import com.frogobox.sdk.piracychecker.utils.apkSignatures
import com.frogobox.sdk.util.SimpleDialogUtil
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

/**
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
        if (isDebug) {
            showLogD<PiracyDelegatesImpl>("BRAND        : ${Build.BRAND}")
            showLogD<PiracyDelegatesImpl>("DEVICE       : ${Build.DEVICE}")
            showLogD<PiracyDelegatesImpl>("FINGERPRINT  : ${Build.FINGERPRINT}")
            showLogD<PiracyDelegatesImpl>("HARDWARE     : ${Build.HARDWARE}")
            showLogD<PiracyDelegatesImpl>("MODEL        : ${Build.MODEL}")
            showLogD<PiracyDelegatesImpl>("MANUFACTURER : ${Build.MANUFACTURER}")
            showLogD<PiracyDelegatesImpl>("PRODUCT      : ${Build.PRODUCT}")
            showLogD<PiracyDelegatesImpl>("IS EMULATOR  : ${isEmulator()}")
        }
    }

    override fun setupPiracyDelegate(context: Context, activity: AppCompatActivity?) {
        piracyDelegateContext = context
        if (activity != null) {
            piracyDelegateActivity = activity
        }
    }

    override fun connectPiracyChecker(callback: PiracyCallback?) {

        if (callback != null) {
            if (piracyCheckerIsDebug) {
                showLogD<PiracyDelegatesImpl>("Connecting Piracy Checker")
                showLogD<PiracyDelegatesImpl>("InstallerId  : ${piracyDelegateContext.getInstallerId()}")
                showLogD<PiracyDelegatesImpl>("Debut State : Is Debug")
                showLogD<PiracyDelegatesImpl>("Please call setupPiracyDelegateDebug(<Debug Status>) first")
                showLogD<PiracyDelegatesImpl>("Please setupDebugMode() to false if using FrogoActivity")
                showLogD<PiracyDelegatesImpl>("Please set build variant to release")
            } else {
                if (isEmulator()) {
                    callback.doOnPirated(piracyMessage(true))
                } else {
                    verifyInstallerId(callback)
                }
            }
        } else {
            if (piracyCheckerIsDebug) {
                showLogD<PiracyDelegatesImpl>("Connecting Piracy Checker")
                showLogD<PiracyDelegatesImpl>("InstallerId  : ${piracyDelegateContext.getInstallerId()}")
                showLogD<PiracyDelegatesImpl>("Debut State : Is Debug")
                showLogD<PiracyDelegatesImpl>("Please call setupPiracyDelegateDebug(<Debug Status>) first")
                showLogD<PiracyDelegatesImpl>("Please call setupDebugMode() set to false if using FrogoActivity")
                showLogD<PiracyDelegatesImpl>("Please set build variant to release")
            } else {
                if (isEmulator()) {
                    showPiracedDialog(piracyMessage(true))
                } else {
                    verifyInstallerId()
                }
            }
        }


    }

    override fun showPiracedDialog(message: PiracyMessage, callback: PiracyCallback?) {
        SimpleDialogUtil.create(
            piracyDelegateContext,
            message.title,
            message.description,
            object : SimpleDialogUtil.OnDialogClickListener {
                override fun positiveButton() {
                    callback?.doOnPirated(message)
                    piracyDelegateActivity.finishAffinity()
                }

                override fun negativeButton() {
                   // TODO
                }
            }
        )
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
            dialogMessage.append("* ").append(it).append("\n")
        }
        AlertDialog.Builder(piracyDelegateContext)
            .setTitle("APK")
            .setMessage(dialogMessage.toString())
            .show()
    }

    override fun verifySignature(callback: PiracyCallback?) {
        piracyDelegateContext.piracyChecker {
            if (callback != null) {
                enableSigningCertificates("478yYkKAQF+KST8y4ATKvHkYibo=") // Wrong signature
                // enableSigningCertificates("VHZs2aiTBiap/F+AYhYeppy0aF0=") // Right signature

                callback {
                    doNotAllow { _, _ ->
                        callback.doOnPirated(piracyMessage())
                    }
                }
            } else {
                display(piracyCheckerDisplay)
                enableSigningCertificates("478yYkKAQF+KST8y4ATKvHkYibo=") // Wrong signature
                // enableSigningCertificates("VHZs2aiTBiap/F+AYhYeppy0aF0=") // Right signature
            }
        }.start()
    }

    override fun showApkSignatures() {
        piracyDelegateContext.apkSignatures.forEach {
            showLogD<PiracyDelegatesImpl>("Signature This Apps : $it")
        }
    }

    override fun verifyInstallerId(callback: PiracyCallback?) {
        piracyDelegateContext.piracyChecker {
            if (callback != null) {
                enableInstallerId(
                    InstallerID.GOOGLE_PLAY,
                    InstallerID.AMAZON_APP_STORE,
                    InstallerID.GALAXY_APPS,
                    InstallerID.HUAWEI_APP_GALLERY,
                    InstallerID.XIAOMI_GET_APPS
                )
                callback {
                    doNotAllow { _, _ ->
                        callback.doOnPirated(piracyMessage())
                    }
                }
            } else {
                display(piracyCheckerDisplay)
                enableInstallerId(
                    InstallerID.GOOGLE_PLAY,
                    InstallerID.AMAZON_APP_STORE,
                    InstallerID.GALAXY_APPS,
                    InstallerID.HUAWEI_APP_GALLERY,
                    InstallerID.XIAOMI_GET_APPS
                )
            }
        }.start()
    }

    override fun verifyUnauthorizedApps(callback: PiracyCallback?) {
        piracyDelegateContext.piracyChecker {
            if (callback != null) {
                enableUnauthorizedAppsCheck()
                blockIfUnauthorizedAppUninstalled("license_checker", "block")
                callback {
                    doNotAllow { _, _ ->
                        callback.doOnPirated(piracyMessage())
                    }
                }
            } else {
                display(piracyCheckerDisplay)
                enableUnauthorizedAppsCheck()
                blockIfUnauthorizedAppUninstalled("license_checker", "block")

            }

        }.start()
    }

    override fun verifyStores(callback: PiracyCallback?) {
        piracyDelegateContext.piracyChecker {
            if (callback != null) {
                enableStoresCheck()
                callback {
                    doNotAllow { _, _ ->
                        callback.doOnPirated(piracyMessage())
                    }
                }
            } else {
                display(piracyCheckerDisplay)
                enableStoresCheck()
            }
        }.start()
    }

    override fun verifyDebug(callback: PiracyCallback?) {
        piracyDelegateContext.piracyChecker {
            if (callback != null) {
                enableDebugCheck()
                callback {
                    doNotAllow { _, _ ->
                        callback.doOnPirated(piracyMessage())
                    }
                }
            } else {
                display(piracyCheckerDisplay)
                enableDebugCheck()
            }
        }.start()
    }

    override fun verifyEmulator(callback: PiracyCallback?) {
        piracyDelegateContext.piracyChecker {
            if (callback != null) {
                enableEmulatorCheck(true)
                callback {
                    doNotAllow { _, _ ->
                        callback.doOnPirated(piracyMessage())
                    }
                }
            } else {
                display(piracyCheckerDisplay)
                enableEmulatorCheck(true)
            }
        }.start()
    }

    override fun piracyMessage(isEmulator: Boolean): PiracyMessage {
        return if (isEmulator) {
            PiracyMessage(
                title = piracyDelegateContext.getString(R.string.piracy_message_emu_title),
                description = piracyDelegateContext.getString(R.string.piracy_message_emu_desc)
            )
        } else {
            PiracyMessage(
                title = piracyDelegateContext.getString(R.string.piracy_message_title),
                description = piracyDelegateContext.getString(R.string.piracy_message_desc)
            )
        }
    }

}