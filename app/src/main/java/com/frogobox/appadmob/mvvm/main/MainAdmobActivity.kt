package com.frogobox.appadmob.mvvm.main

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.frogobox.admob.core.IFrogoAdConsent
import com.frogobox.BuildConfig
import com.frogobox.R
import com.frogobox.appadmob.base.BaseActivity
import com.frogobox.databinding.ActivityMainAdmobBinding
import com.frogobox.appadmob.mvvm.appopenad.AppOpenAdActivity
import com.frogobox.appadmob.mvvm.interstitial.InterstitialActivity
import com.frogobox.appadmob.mvvm.movie.MovieAdsActivity
import com.frogobox.appadmob.mvvm.news.NewsAdsActivity
import com.frogobox.appadmob.mvvm.rewarded.RewardedActivity
import com.frogobox.sdk.ext.showLogDebug
import com.frogobox.sdk.ext.startActivityExt
import com.google.android.gms.ads.AdSize
import com.google.android.ump.FormError

class MainAdmobActivity : BaseActivity<ActivityMainAdmobBinding>() {

    override fun setupViewBinding(): ActivityMainAdmobBinding {
        return ActivityMainAdmobBinding.inflate(layoutInflater)
    }

    override fun onCreateExt(savedInstanceState: Bundle?) {
        super.onCreateExt(savedInstanceState)

        showAdConsent(object : IFrogoAdConsent {

            override fun activity(): Activity {
                return this@MainAdmobActivity
            }

            override fun isDebug(): Boolean {
                return BuildConfig.DEBUG
            }

            override fun isUnderAgeAd(): Boolean {
                return false
            }

            override fun onNotUsingAdConsent() {
                requestAdmobApi()
                setupButtonClick()
                setupBannerAds()
            }

            override fun onConsentSuccess() {
                requestAdmobApi()
                setupButtonClick()
                setupBannerAds()
            }

            override fun onConsentError(formError: FormError) {
                showLogDebug("FrogoAdConsent ${formError.message}")
            }

        })

    }

    private fun setupBannerAds() {
        showAdBanner(binding.adsXml.adsPhoneTabSpecialSmartBanner)
        showAdBannerContainer(
            getString(R.string.admob_banner),
            AdSize.SMART_BANNER,
            binding.includeAdsView.frogoAdsBanner
        )
    }

    private fun setupButtonClick() {

        binding.apply {

            btnInterstitial.setOnClickListener {
                startActivityExt<InterstitialActivity>()
            }

            btnRewarded.setOnClickListener {
                startActivityExt<RewardedActivity>()
            }

            btnRecyclerView.setOnClickListener {
                startActivityExt<NewsAdsActivity>()
            }

            btnRecyclerView2.setOnClickListener {
                startActivityExt<MovieAdsActivity>()
            }

            btnJavaSampleActivity.setOnClickListener {
                startActivityExt<MainJavaAdmobActivity>()
            }

            btnAppOpenAd.setOnClickListener {
                startActivityExt<AppOpenAdActivity>()
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.toolbar_menu_about -> {
                startActivityExt<AboutUsActivity>()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}