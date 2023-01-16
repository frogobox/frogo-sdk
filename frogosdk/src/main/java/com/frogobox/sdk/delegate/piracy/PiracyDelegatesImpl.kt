package com.frogobox.sdk.delegate.piracy

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.frogobox.sdk.delegate.piracy.util.PiracyMessage
import com.frogobox.sdk.ext.getInstallerId
import com.frogobox.sdk.ext.showLogD
import com.frogobox.sdk.util.FrogoFunc
import com.github.javiersantos.piracychecker.callback
import com.github.javiersantos.piracychecker.doNotAllow
import com.github.javiersantos.piracychecker.enums.Display
import com.github.javiersantos.piracychecker.enums.InstallerID
import com.github.javiersantos.piracychecker.piracyChecker
import com.github.javiersantos.piracychecker.utils.apkSignatures
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

        const val PIRACY_MESSAGE_TITLE = "This app is not licensed"
        const val PIRACY_MESSAGE_DESC =
            "This application is not licensed nor valid. Warning most likely the application has been hacked, Please download the app from trusted sources"
        const val PIRACY_MESSAGE_EMU_TITLE = "Warning Prohibited Activity"
        const val PIRACY_MESSAGE_EMU_DESC = "This app is not intended for emulators"

        val PIRACY_MESSAGE_CALLBACK = PiracyMessage(PIRACY_MESSAGE_TITLE, PIRACY_MESSAGE_DESC)
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
            showLogD<PiracyDelegatesImpl>("Please call setupDebugMode() set to false if using FrogoActivity")
            showLogD<PiracyDelegatesImpl>("Please set build variant to release")
        } else {
            if (isEmulator()) {
                showLogD<PiracyDelegatesImpl>("isEmulator")
                showPiracedDialog(PiracyMessage(PIRACY_MESSAGE_EMU_TITLE, PIRACY_MESSAGE_EMU_DESC))
            } else {
                showLogD<PiracyDelegatesImpl>("isNotEmulator")
                verifyInstallerId()
            }
        }
    }

    override fun connectPiracyChecker(callback: FrogoPiracyCallback) {
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
                callback.doOnPirated(
                    PiracyMessage(
                        PIRACY_MESSAGE_EMU_TITLE,
                        PIRACY_MESSAGE_EMU_DESC
                    )
                )
            } else {
                showLogD<PiracyDelegatesImpl>("isNotEmulator")
                verifyInstallerId(callback)
            }
        }
    }

    override fun showPiracedDialog(message: PiracyMessage) {
        FrogoFunc.createDialogDefault(
            piracyDelegateContext,
            message.title,
            message.description,
        ) {
            piracyDelegateActivity.finishAffinity()
        }
    }

    override fun showPiracedDialog(message: PiracyMessage, callback: FrogoPiracyDialogCallback) {
        FrogoFunc.createDialogDefault(
            piracyDelegateContext,
            message.title,
            message.description,
        ) {
            callback.doOnPirated(message)
            piracyDelegateActivity.finishAffinity()
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
        showLogD<PiracyDelegatesImpl>("readSignature")
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
        showLogD<PiracyDelegatesImpl>("verifySignature")
        piracyDelegateContext.piracyChecker {
            display(piracyCheckerDisplay)
            enableSigningCertificates("478yYkKAQF+KST8y4ATKvHkYibo=") // Wrong signature
            // enableSigningCertificates("VHZs2aiTBiap/F+AYhYeppy0aF0=") // Right signature
        }.start()
    }

    override fun verifySignature(callback: FrogoPiracyCallback) {
        showLogD<PiracyDelegatesImpl>("verifySignature")
        piracyDelegateContext.piracyChecker {
            enableSigningCertificates("478yYkKAQF+KST8y4ATKvHkYibo=") // Wrong signature
            // enableSigningCertificates("VHZs2aiTBiap/F+AYhYeppy0aF0=") // Right signature
            callback {
                doNotAllow { piracyCheckerError, pirateApp ->
                    callback.doOnPirated(PIRACY_MESSAGE_CALLBACK)
                }
            }
        }.start()
    }

    override fun showApkSignatures() {
        showLogD<PiracyDelegatesImpl>("showApkSignatures")
        piracyDelegateContext.apkSignatures.forEach {
            showLogD<PiracyDelegatesImpl>("Signature This Apps : $it")
        }
    }

    override fun verifyInstallerId() {
        showLogD<PiracyDelegatesImpl>("verifyInstallerId")
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

    override fun verifyInstallerId(callback: FrogoPiracyCallback) {
        showLogD<PiracyDelegatesImpl>("verifyInstallerId")
        piracyDelegateContext.piracyChecker {
            enableInstallerId(
                InstallerID.GOOGLE_PLAY,
                InstallerID.AMAZON_APP_STORE,
                InstallerID.GALAXY_APPS,
                InstallerID.HUAWEI_APP_GALLERY
            )
            callback {
                doNotAllow { piracyCheckerError, pirateApp ->
                    callback.doOnPirated(PIRACY_MESSAGE_CALLBACK)
                }
            }
        }.start()
    }

    override fun verifyUnauthorizedApps() {
        showLogD<PiracyDelegatesImpl>("verifyUnauthorizedApps")
        piracyDelegateContext.piracyChecker {
            display(piracyCheckerDisplay)
            enableUnauthorizedAppsCheck()
            blockIfUnauthorizedAppUninstalled("license_checker", "block")
        }.start()
    }

    override fun verifyUnauthorizedApps(callback: FrogoPiracyCallback) {
        showLogD<PiracyDelegatesImpl>("verifyUnauthorizedApps")
        piracyDelegateContext.piracyChecker {
            display(piracyCheckerDisplay)
            enableUnauthorizedAppsCheck()
            blockIfUnauthorizedAppUninstalled("license_checker", "block")
            callback {
                doNotAllow { piracyCheckerError, pirateApp ->
                    callback.doOnPirated(PIRACY_MESSAGE_CALLBACK)
                }
            }
        }.start()
    }

    override fun verifyStores() {
        showLogD<PiracyDelegatesImpl>("verifyStores")
        piracyDelegateContext.piracyChecker {
            display(piracyCheckerDisplay)
            enableStoresCheck()
        }.start()
    }

    override fun verifyStores(callback: FrogoPiracyCallback) {
        showLogD<PiracyDelegatesImpl>("verifyStores")
        piracyDelegateContext.piracyChecker {
            enableStoresCheck()
            callback {
                doNotAllow { piracyCheckerError, pirateApp ->
                    callback.doOnPirated(PIRACY_MESSAGE_CALLBACK)
                }
            }
        }.start()
    }

    override fun verifyDebug() {
        showLogD<PiracyDelegatesImpl>("verifyDebug")
        piracyDelegateContext.piracyChecker {
            display(piracyCheckerDisplay)
            enableDebugCheck()
        }.start()
    }

    override fun verifyDebug(callback: FrogoPiracyCallback) {
        showLogD<PiracyDelegatesImpl>("verifyDebug")
        piracyDelegateContext.piracyChecker {
            enableDebugCheck()
            callback {
                doNotAllow { piracyCheckerError, pirateApp ->
                    callback.doOnPirated(PIRACY_MESSAGE_CALLBACK)
                }
            }
        }.start()
    }

    override fun verifyEmulator() {
        showLogD<PiracyDelegatesImpl>("verifyEmulator")
        piracyDelegateContext.piracyChecker {
            display(piracyCheckerDisplay)
            enableEmulatorCheck(true)
        }.start()
    }

    override fun verifyEmulator(callback: FrogoPiracyCallback) {
        showLogD<PiracyDelegatesImpl>("verifyEmulator")
        piracyDelegateContext.piracyChecker {
            enableEmulatorCheck(true)
            callback {
                doNotAllow { piracyCheckerError, pirateApp ->
                    callback.doOnPirated(PIRACY_MESSAGE_CALLBACK)
                }
            }
        }.start()
    }

}