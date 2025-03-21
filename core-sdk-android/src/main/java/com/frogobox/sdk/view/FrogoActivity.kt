package com.frogobox.sdk.view

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.*
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import com.frogobox.sdk.R
import com.frogobox.sdk.ext.getColorExt
import com.frogobox.sdk.ext.getDrawableExt
import java.util.*
import androidx.core.graphics.drawable.toDrawable


/**
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

abstract class FrogoActivity : AppCompatActivity() {

    companion object {
        val TAG: String = FrogoActivity::class.java.simpleName
    }

    protected val frogoActivity by lazy { this }

    protected val textCopyright: String by lazy {
        "${getString(R.string.about_all_right_reserved)} ${getString(R.string.about_copyright)} ${
            Calendar.getInstance().get(Calendar.YEAR)
        }"
    }

    private var activityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        setupActivityResultExt(result)
    }

    // ---------------------------------------------------------------------------------------------

    open fun doOnBackPressedExt() {
        finish()
    }

    open fun onBackPressedExt() {
        onBackPressedDispatcher.onBackPressed()
    }

    open fun setupDebugMode(): Boolean {
        return true
    }

    // ---------------------------------------------------------------------------------------------

    open fun setupPiracyMode() {}
    open fun setupDelegates() {}
    open fun setupViewModel() {}
    open fun setupMonetized() {}
    open fun setupContentView() {}
    open fun setupActivityResultExt(result: ActivityResult) {}
    open fun onCreateExt(savedInstanceState: Bundle?) {}


    open fun setupDoOnBackPressedExt() {
        if (Build.VERSION.SDK_INT >= 33) {
            onBackInvokedDispatcher.registerOnBackInvokedCallback(
                OnBackInvokedDispatcher.PRIORITY_DEFAULT
            ) {
                // Back is pressed... Finishing the activity
                doOnBackPressedExt()
            }
        } else {
            onBackPressedDispatcher.addCallback(
                this,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        // Back is pressed... Finishing the activity
                        doOnBackPressedExt()
                    }
                })
        }
    }
    // ---------------------------------------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupContentView()
        setupDoOnBackPressedExt()
        setupDelegates()
        setupPiracyMode()
        setupMonetized()
        setupViewModel()
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

    open fun setupDetailActivity(title: String) {
        setupDetailActivity(title, null, null)
    }

    open fun setupDetailActivity(title: String, actionBackIcon: Int?) {
        setupDetailActivity(title, actionBackIcon, null)
    }

    open fun setupDetailActivity(
        title: String,
        @DrawableRes actionBackIcon: Int?,
        @ColorRes backgroundColor: Int?,
    ) {
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (actionBackIcon != null) {
            supportActionBar?.setHomeAsUpIndicator(getDrawableExt(actionBackIcon))
        }
        if (backgroundColor != null) {
            supportActionBar?.setBackgroundDrawable(getColorExt(backgroundColor).toDrawable())
        }
    }

    open fun startActivityResultExt(intent: Intent) {
        activityResult.launch(intent)
    }

    open fun setupChildFragment(frameId: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(frameId, fragment)
            commit()
        }
    }

    open fun setupFullScreen() {
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

    open fun setupHideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

}