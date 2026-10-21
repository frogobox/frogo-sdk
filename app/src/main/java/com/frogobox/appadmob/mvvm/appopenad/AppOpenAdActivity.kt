package com.frogobox.appadmob.mvvm.appopenad

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.frogobox.BaseActivity
import com.frogobox.ads.FrogoAdmobApplication
import com.frogobox.ads.callback.FrogoAdmobAppOpenAdCallback
import com.frogobox.appadmob.util.AdHelper
import com.frogobox.databinding.ActivityAppOpenAdBinding
import com.frogobox.sdk.ext.px
import com.frogobox.sdk.ext.showToast
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Created by Faisal Amir on 24/10/22
 * -----------------------------------------
 * E-mail   : faisalamircs@gmail.com
 * GitHub   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) Frogobox ID / amirisback
 * All rights reserved
 */

class AppOpenAdActivity : BaseActivity<ActivityAppOpenAdBinding>(),
    FrogoAdmobAppOpenAdCallback {

    companion object {
        const val TAG = "AppOpenAdActivity"
    }

    enum class AdState(
        val label: String,
        val bgColor: Int,
        val textColor: Int
    ) {
        NOT_LOADED("NOT LOADED", 0xFFEDE7F6.toInt(), 0xFF512DA8.toInt()),
        LOADING("LOADING", 0xFFEDE7F6.toInt(), 0xFF512DA8.toInt()),
        READY("READY", 0xFFE8F5E9.toInt(), 0xFF1B5E20.toInt()),
        SHOWING("SHOWING", 0xFFE0F2F1.toInt(), 0xFF004D40.toInt()),
        FAILED("FAILED", 0xFFFFEBEE.toInt(), 0xFFC62828.toInt()),
        EXPIRED("EXPIRED", 0xFFFFF3E0.toInt(), 0xFFE65100.toInt())
    }

    private var currentState: AdState = AdState.NOT_LOADED

    override fun setupViewBinding(): ActivityAppOpenAdBinding {
        return ActivityAppOpenAdBinding.inflate(layoutInflater)
    }

    override fun onCreateExt(savedInstanceState: Bundle?) {
        super.onCreateExt(savedInstanceState)
        setupInsets()
        setupUI()
    }

    override fun onResume() {
        super.onResume()
        refreshAdState()
    }

    private fun setupInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.rootLayout) { _, insets ->
            val statusBarInsets = insets.getInsets(WindowInsetsCompat.Type.statusBars())
            val navBarInsets = insets.getInsets(WindowInsetsCompat.Type.navigationBars())

            binding.appBarLayout.updatePadding(top = statusBarInsets.top)
            binding.nestedScrollView.updatePadding(bottom = navBarInsets.bottom + 24.px)
            insets
        }
    }

    private fun setupUI() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        val adUnitId = AdHelper.getAdOpenAppUnitId(this)
        binding.tvAdUnitInfo.text = "AdUnit: $adUnitId"

        binding.apply {
            btnPreloadAd.setOnClickListener {
                logEvent("Action", "User tapped Preload App Open Ad")
                loadAppOpenAd(adUnitId, this@AppOpenAdActivity)
            }

            btnShowAd.setOnClickListener {
                logEvent("Action", "User tapped Show Ad If Available")
                showAppOpenAd(adUnitId, this@AppOpenAdActivity)
            }

            btnLoadAndShow.setOnClickListener {
                logEvent("Action", "User tapped Load & Show Immediately")
                loadAndShowAppOpenAd(adUnitId, this@AppOpenAdActivity)
            }

            btnClearAd.setOnClickListener {
                logEvent("Action", "User tapped Clear / Invalidate Cached Ad")
                (application as? FrogoAdmobApplication)?.clearAppOpenAd()
                updateAdState(AdState.NOT_LOADED, "Ad cache cleared")
                showToast("Cached ad invalidated")
            }

            btnClearLog.setOnClickListener {
                tvEventLog.text = ""
            }
        }

        refreshAdState()
    }

    private fun refreshAdState() {
        val available = isAppOpenAdAvailable()
        val app = application as? FrogoAdmobApplication
        val loadTime = app?.getAppOpenAdLoadTime() ?: 0L

        if (available) {
            val ageMinutes = (System.currentTimeMillis() - loadTime) / (60 * 1000)
            updateAdState(AdState.READY, "Cached: ${ageMinutes}m ago (Valid for 4h)")
        } else if (loadTime > 0L && (System.currentTimeMillis() - loadTime) >= 4 * 3600000L) {
            updateAdState(AdState.EXPIRED, "Ad expired (> 4 hours)")
        } else if (currentState != AdState.LOADING) {
            updateAdState(AdState.NOT_LOADED, "No cached ad")
        }
    }

    private fun updateAdState(state: AdState, detail: String? = null) {
        currentState = state
        binding.apply {
            chipAdStatus.text = state.label
            chipAdStatus.backgroundTintList = ColorStateList.valueOf(state.bgColor)
            chipAdStatus.setTextColor(state.textColor)

            when (state) {
                AdState.LOADING -> {
                    progressAdLoading.visibility = View.VISIBLE
                    btnPreloadAd.isEnabled = false
                    btnShowAd.isEnabled = false
                    btnLoadAndShow.isEnabled = false
                    tvAdExpiration.text = "Ad Validity: Loading ad..."
                }
                AdState.SHOWING -> {
                    progressAdLoading.visibility = View.INVISIBLE
                    btnPreloadAd.isEnabled = false
                    btnShowAd.isEnabled = false
                    btnLoadAndShow.isEnabled = false
                    tvAdExpiration.text = "Ad Validity: Showing on screen"
                }
                AdState.READY -> {
                    progressAdLoading.visibility = View.INVISIBLE
                    btnPreloadAd.isEnabled = true
                    btnShowAd.isEnabled = true
                    btnLoadAndShow.isEnabled = true
                    tvAdExpiration.text = detail ?: "Ad Validity: Active & Ready (Valid for 4h)"
                }
                AdState.EXPIRED -> {
                    progressAdLoading.visibility = View.INVISIBLE
                    btnPreloadAd.isEnabled = true
                    btnShowAd.isEnabled = false
                    btnLoadAndShow.isEnabled = true
                    tvAdExpiration.text = "Ad Validity: Expired (> 4h). Request new ad."
                }
                AdState.FAILED -> {
                    progressAdLoading.visibility = View.INVISIBLE
                    btnPreloadAd.isEnabled = true
                    btnShowAd.isEnabled = isAppOpenAdAvailable()
                    btnLoadAndShow.isEnabled = true
                    tvAdExpiration.text = "Ad Validity: Load failed. Tap Preload to retry."
                }
                AdState.NOT_LOADED -> {
                    progressAdLoading.visibility = View.INVISIBLE
                    btnPreloadAd.isEnabled = true
                    btnShowAd.isEnabled = false
                    btnLoadAndShow.isEnabled = true
                    tvAdExpiration.text = detail ?: "Ad Validity: Idle (No cached ad)"
                }
            }
        }
    }

    private fun logEvent(tag: String, message: String) {
        val time = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
        binding.tvEventLog.append("[$time] $tag: $message\n")
        binding.scrollLog.post {
            binding.scrollLog.fullScroll(View.FOCUS_DOWN)
        }
    }

    // =============================================================================================
    // FrogoAdmobAppOpenAdCallback Implementation
    // =============================================================================================

    override fun onShowAdRequestProgress(tag: String, message: String) {
        logEvent("onShowAdRequestProgress", message)
        updateAdState(AdState.LOADING)
    }

    override fun onHideAdRequestProgress(tag: String, message: String) {
        logEvent("onHideAdRequestProgress", message)
        if (currentState == AdState.LOADING) {
            binding.progressAdLoading.visibility = View.INVISIBLE
        }
    }

    override fun onAdDismissed(tag: String, message: String) {
        logEvent("onAdDismissed", message)
        refreshAdState()
    }

    override fun onAdFailed(tag: String, errorMessage: String) {
        logEvent("onAdFailed", errorMessage)
        updateAdState(AdState.FAILED, errorMessage)
        showToast(errorMessage)
    }

    override fun onAdLoaded(tag: String, message: String) {
        logEvent("onAdLoaded", message)
        updateAdState(AdState.READY)
    }

    override fun onAdShowed(tag: String, message: String) {
        logEvent("onAdShowed", message)
        updateAdState(AdState.SHOWING)
    }
}