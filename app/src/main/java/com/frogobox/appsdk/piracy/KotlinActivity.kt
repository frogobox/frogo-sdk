package com.frogobox.appsdk.piracy

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.frogobox.appsdk.R
import com.frogobox.appsdk.core.BaseActivity
import com.frogobox.appsdk.databinding.ActivityPiracyBinding
import com.frogobox.sdkutil.piracychecker.allow
import com.frogobox.sdkutil.piracychecker.callback
import com.frogobox.sdkutil.piracychecker.doNotAllow
import com.frogobox.sdkutil.piracychecker.enums.Display
import com.frogobox.sdkutil.piracychecker.enums.InstallerID
import com.frogobox.sdkutil.piracychecker.onError
import com.frogobox.sdkutil.piracychecker.piracyChecker
import com.frogobox.sdkutil.piracychecker.utils.apkSignatures

class KotlinActivity : BaseActivity<ActivityPiracyBinding>() {

    private var piracyCheckerDisplay = Display.DIALOG

    override fun setupViewBinding(): ActivityPiracyBinding {
        return ActivityPiracyBinding.inflate(layoutInflater)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        
        binding.layoutContentMain.radioDisplay.setOnCheckedChangeListener { _, i ->
            when (i) {
                R.id.radio_dialog -> piracyCheckerDisplay = Display.DIALOG
                R.id.radio_activity -> piracyCheckerDisplay = Display.ACTIVITY
            }
        }
        
        // Show APK signature
        apkSignatures.forEach { Log.e("Signature", it) }
    }
    
    fun toGithub() {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://github.com/javiersantos/piracyChecker")))
    }
    
    fun verifySignature() {
        piracyChecker {
            display(piracyCheckerDisplay)
            enableSigningCertificates("478yYkKAQF+KST8y4ATKvHkYibo=") // Wrong signature
            //enableSigningCertificates("VHZs2aiTBiap/F+AYhYeppy0aF0=") // Right signature
        }.start()
    }
    
    fun readSignature() {
        val dialogMessage = StringBuilder()
        apkSignatures.forEach {
            Log.e("Signature", it)
            dialogMessage.append("* ").append(it).append("\n")
        }
        AlertDialog.Builder(this)
            .setTitle("APK")
            .setMessage(dialogMessage.toString())
            .show()
    }
    
    fun verifyInstallerId() {
        piracyChecker {
            display(piracyCheckerDisplay)
            enableInstallerId(InstallerID.GOOGLE_PLAY)
        }.start()
    }
    
    fun verifyUnauthorizedApps() {
        piracyChecker {
            display(piracyCheckerDisplay)
            enableUnauthorizedAppsCheck()
            //blockIfUnauthorizedAppUninstalled("license_checker", "block")
        }.start()
    }
    
    fun verifyStores() {
        piracyChecker {
            display(piracyCheckerDisplay)
            enableStoresCheck()
        }.start()
    }
    
    fun verifyDebug() {
        piracyChecker {
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
    
    fun verifyEmulator() {
        piracyChecker {
            display(piracyCheckerDisplay)
            enableEmulatorCheck(false)
        }.start()
    }
}