package com.frogobox.sdk.view

import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.frogobox.coresdk.util.FrogoConstant.Url.BASE_PLAY_STORE_URL
import com.frogobox.sdk.R
import com.frogobox.sdk.ext.*
import com.frogobox.sdk.util.FrogoFunc
import com.frogobox.sdk.view.piracycheck.FrogoPiracyActivity
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
abstract class FrogoActivity<VB : ViewBinding> : FrogoPiracyActivity(), IFrogoActivity {

    companion object {
        val TAG: String = FrogoActivity::class.java.simpleName
    }

    protected val frogoActivity by lazy { this }

    protected val binding: VB by lazy { setupViewBinding() }

    protected val textCopyright: String by lazy {
        "${getString(R.string.about_all_right_reserved)} ${getString(R.string.about_copyright)} ${
            Calendar.getInstance().get(Calendar.YEAR)
        }"
    }

    // ---------------------------------------------------------------------------------------------

    abstract fun setupViewBinding(): VB

    abstract fun setupOnCreate(savedInstanceState: Bundle?)

    // ---------------------------------------------------------------------------------------------

    open fun setupViewModel() {}

    // ---------------------------------------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViewModel()
        setupOnCreate(savedInstanceState)
        if (savedInstanceState == null) {
            showLogDebug("$TAG View Binding : ${binding::class.java.simpleName}")
            showLogDebug("$TAG Internet Status : ${isNetworkConnected()}")
        }
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
        setupDetailActivity(title, null, null)
    }

    override fun setupDetailActivity(title: String, actionBackIcon: Int?) {
        setupDetailActivity(title, actionBackIcon, null)
    }

    override fun setupDetailActivity(
        title: String,
        @DrawableRes actionBackIcon: Int?,
        @ColorRes backgroundColor: Int?
    ) {
        showLogDebug("$TAG Setup Detail Activity : Title : $title")
        showLogDebug("$TAG Setup Detail Activity : Action Back Icon : $actionBackIcon")
        showLogDebug("$TAG Setup Detail Activity : Background Color: $backgroundColor")
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (actionBackIcon != null) {
            supportActionBar?.setHomeAsUpIndicator(getResDrawable(actionBackIcon))
        }
        if (backgroundColor != null) {
            supportActionBar?.setBackgroundDrawable(ColorDrawable(getResColor(backgroundColor)))
        }
    }

    override fun setupChildFragment(frameId: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(frameId, fragment)
            commit()
        }
    }

    override fun showToast(message: String) {
        showToast(message, Toast.LENGTH_SHORT)
    }

    override fun setupEmptyView(view: View, isEmpty: Boolean) {
        view.emptyViewHandle(isEmpty)
    }

    override fun setupProgressView(view: View, isProgress: Boolean) {
        view.progressViewHandle(isProgress)
    }

    override fun checkExtra(extraKey: String): Boolean {
        return intent?.hasExtra(extraKey)!!
    }

    override fun <Model> frogoFragmentNewInstance(
        fragment: FrogoFragment<*>,
        argumentKey: String,
        extraDataResult: Model
    ) {
        fragment.frogoNewInstance(argumentKey, extraDataResult)
    }

    protected inline fun <reified ClassActivity> frogoStartActivity() {
        singleStartActivity<ClassActivity>()
    }

    protected inline fun <reified ClassActivity, reified Model> frogoStartActivity(
        extraKey: String,
        data: Model
    ) {
        singleStartActivity<ClassActivity, Model>(extraKey, data)
    }

    protected inline fun <reified Model> frogoGetExtraData(extraKey: String): Model {
        return singleGetExtraData(extraKey)
    }

    override fun isNetworkConnected(): Boolean {
        return FrogoFunc.isNetworkConnected(this)
    }

    override fun shareApp(packageName: String, appName: String) {
        singleStartActivityShareApp(
            appName,
            "Hi, let's play $appName. Download now on Google Play! $BASE_PLAY_STORE_URL$packageName"
        )
    }

    override fun rateApp(packageName: String) {
        singleStartActivityOpenApp("$BASE_PLAY_STORE_URL$packageName")
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
        showLogDebug("$TAG Hide System UI a.k.a Status Bar Android CutOut")
    }

}