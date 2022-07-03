package com.frogobox.sdk.view

import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import com.frogobox.sdk.R
import com.frogobox.sdk.delegate.piracy.PiracyDelegates
import com.frogobox.sdk.delegate.piracy.PiracyDelegatesImpl
import com.frogobox.sdk.delegate.preference.PreferenceDelegates
import com.frogobox.sdk.delegate.preference.PreferenceDelegatesImpl
import com.frogobox.sdk.delegate.util.DateDelegates
import com.frogobox.sdk.delegate.util.DateDelegatesImpl
import com.frogobox.sdk.delegate.util.UtilDelegates
import com.frogobox.sdk.delegate.util.UtilDelegatesImpl
import com.frogobox.sdk.delegate.view.ViewDelegates
import com.frogobox.sdk.delegate.view.ViewDelegatesImpl
import com.frogobox.sdk.ext.*
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

abstract class FrogoActivity: AppCompatActivity(),
    IFrogoActivity,
    ViewDelegates by ViewDelegatesImpl(),
    UtilDelegates by UtilDelegatesImpl(),
    DateDelegates by DateDelegatesImpl(),
    PiracyDelegates by PiracyDelegatesImpl(),
    PreferenceDelegates by PreferenceDelegatesImpl() {

    companion object {
        val TAG: String = FrogoActivity::class.java.simpleName
    }

    protected val frogoActivity by lazy { this }

    protected val textCopyright: String by lazy {
        "${getString(R.string.about_all_right_reserved)} ${getString(R.string.about_copyright)} ${
            Calendar.getInstance().get(Calendar.YEAR)
        }"
    }

    // ---------------------------------------------------------------------------------------------

    @Deprecated("Use onCreateExt instead", ReplaceWith("onCreateExt"))
    open fun setupOnCreate(savedInstanceState: Bundle?) {}

    open fun onCreateExt(savedInstanceState: Bundle?) {
        showLogD<FrogoActivity>("onCreateExt()")
    }

    open fun setupViewModel() {
        showLogD<FrogoActivity>("setupViewModel()")
    }

    // ---------------------------------------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            setupPreferenceDelegates(this)
            setupPiracyDelegate(this)
            setupViewDelegates(this)
            setupUtilDelegates(this)
            showLogDebug("$TAG Internet Status : ${isNetworkConnected()}")
        }
        setupViewModel()
        setupOnCreate(savedInstanceState)
        onCreateExt(savedInstanceState)
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

    // ---------------------------------------------------------------------------------------------

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

    override fun checkExtra(extraKey: String): Boolean {
        return intent?.hasExtra(extraKey)!!
    }

    override fun <Model> frogoFragmentNewInstance(
        fragment: FrogoFragment,
        argumentKey: String,
        extraDataResult: Model
    ) {
        fragment.frogoNewInstance(argumentKey, extraDataResult)
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

    protected inline fun <reified ClassActivity> frogoStartActivity() {
        startActivityExt<ClassActivity>()
    }

    protected inline fun <reified ClassActivity, reified Model> frogoStartActivity(
        extraKey: String,
        data: Model
    ) {
        startActivityExt<ClassActivity, Model>(extraKey, data)
    }

    protected inline fun <reified Model> frogoGetExtraData(extraKey: String): Model {
        return getExtraDataExt(extraKey)
    }

}