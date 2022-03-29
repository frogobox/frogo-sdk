package com.frogobox.sdk

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.frogobox.coresdk.FrogoConstant.Url.BASE_PLAY_STORE_URL
import com.github.javiersantos.piracychecker.*
import com.github.javiersantos.piracychecker.enums.Display
import com.github.javiersantos.piracychecker.enums.InstallerID
import com.github.javiersantos.piracychecker.utils.apkSignatures
import com.google.gson.Gson
import java.util.*


/*
 * Created by faisalamir on 28/07/21
 * FrogoSDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.      
 * All rights reserved
 *
 */
abstract class FrogoActivity<VB : ViewBinding> : AppCompatActivity(), IFrogoActivity {

    companion object {
        val TAG: String = FrogoActivity::class.java.simpleName
    }

    private var piracyCheckerDisplay = Display.DIALOG

    protected val frogoActivity by lazy { this }

    protected val binding: VB by lazy { setupViewBinding() }

    protected val textCopyright : String by lazy {
        "${getString(R.string.about_all_right_reserved)} ${getString(R.string.about_copyright)} ${Calendar.getInstance().get(Calendar.YEAR)}"
    }

    // ---------------------------------------------------------------------------------------------

    abstract fun setupViewBinding(): VB

    abstract fun setupViewModel()

    abstract fun setupOnCreate(savedInstanceState: Bundle?)

    // ---------------------------------------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViewModel()
        setupOnCreate(savedInstanceState)
        Log.d(TAG, "View Binding : ${binding::class.java.simpleName}")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun setupDetailActivity(title: String) {
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        Log.d(TAG, "Setup Detail Activity : $title")
    }

    override fun setupChildFragment(frameId: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(frameId, fragment)
            commit()
        }
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        Log.d(TAG, "Toast : $message")
    }

    override fun setupEmptyView(view: View, isEmpty: Boolean) {
        if (isEmpty) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
        Log.d(TAG, "Setup Empty View : $isEmpty")
    }

    override fun setupProgressView(view: View, isProgress: Boolean) {
        if (isProgress) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
        Log.d(TAG, "Setup Progress View : $isProgress")
    }

    override fun checkExtra(extraKey: String): Boolean {
        return intent?.hasExtra(extraKey)!!
    }

    override fun <Model> FrogoFragmentNewInstance(
        fragment: FrogoFragment<*>,
        argumentKey: String,
        extraDataResult: Model
    ) {
        fragment.frogoNewInstance(argumentKey, extraDataResult)
    }

    protected inline fun <reified ClassActivity> frogoStartActivity() {
        Log.d(TAG, "Frogo Start Activity : ${ClassActivity::class.java.simpleName}")
        startActivity(Intent(this, ClassActivity::class.java))
    }

    protected inline fun <reified ClassActivity, reified Model> frogoStartActivity(
        extraKey: String,
        data: Model
    ) {
        val intent = Intent(this, ClassActivity::class.java)
        val extraData = Gson().toJson(data)
        intent.putExtra(extraKey, extraData)
        Log.d(TAG, "Frogo Start Activity : ${ClassActivity::class.java.simpleName}")
        Log.d(
            TAG,
            "Frogo Start Activity : Extra Data (${Model::class.java.simpleName}) : $extraData"
        )
        startActivity(intent)
    }

    protected inline fun <reified Model> frogoGetExtraData(extraKey: String): Model {
        val extraIntent = intent.getStringExtra(extraKey)
        return Gson().fromJson(extraIntent, Model::class.java)
    }

    override fun isNetworkConnected(): Boolean {
        return FrogoFunc.isNetworkConnected(this)
    }

    override fun setupFullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val controller = window.insetsController
            if (controller != null) {
                controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                controller.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
    }

    override fun setupHideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
        Log.d(TAG, "Hide System UI a.k.a Status Bar Android CutOut")
    }

    override fun shareApp(packageName: String, appName: String) {
        val messageShare =
            "Hi, let's play $appName. Download now on Google Play! $BASE_PLAY_STORE_URL$packageName"
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, appName)
        intent.putExtra(Intent.EXTRA_TEXT, messageShare)
        Log.d(TAG, "Message Share App : $messageShare")
        startActivity(Intent.createChooser(intent, "Share"))
    }

    override fun rateApp(packageName: String) {
        val url = "$BASE_PLAY_STORE_URL$packageName"
        Log.d(TAG, "Url Play Store : $url")
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    // ---------------------------------------------------------------------------------------------

    override fun verifySignature() {
        piracyChecker {
            display(piracyCheckerDisplay)
            enableSigningCertificates("478yYkKAQF+KST8y4ATKvHkYibo=") // Wrong signature
            //enableSigningCertificates("VHZs2aiTBiap/F+AYhYeppy0aF0=") // Right signature
        }.start()
    }

    override fun readSignature() {
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

    override fun verifyInstallerId() {
        piracyChecker {
            display(piracyCheckerDisplay)
            enableInstallerId(InstallerID.GOOGLE_PLAY)
        }.start()
    }

    override fun verifyUnauthorizedApps() {
        piracyChecker {
            display(piracyCheckerDisplay)
            enableUnauthorizedAppsCheck()
            //blockIfUnauthorizedAppUninstalled("license_checker", "block")
        }.start()
    }

    override fun verifyStores() {
        piracyChecker {
            display(piracyCheckerDisplay)
            enableStoresCheck()
        }.start()
    }

    override fun verifyDebug() {
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

    override fun verifyEmulator() {
        piracyChecker {
            display(piracyCheckerDisplay)
            enableEmulatorCheck(false)
        }.start()
    }

    override fun showApkSignatures() {
        apkSignatures.forEach { Log.d(TAG, "Signature This Apps : $it") }
    }

}