package com.frogobox.appsdk.main

import android.os.Bundle
import com.frogobox.appsdk.log.LogActivity
import com.frogobox.appsdk.news.NewsActivity
import com.frogobox.appsdk.news.result.NewsResultActivity
import com.frogobox.appsdk.notification.simple.MainNotifActivity
import com.frogobox.appsdk.piracy.KotlinActivity
import com.frogobox.appsdk.piracy.PiracyMainActivity
import com.frogobox.appsdk.viewpager.VPagerActivity
import com.frogobox.databinding.ActivityMainFrogoSdkBinding
import com.frogobox.sdk.ext.showLogD
import com.frogobox.sdk.ext.startActivityExt
import com.frogobox.sdk.ui.FrogoAboutUsActivity
import com.frogobox.sdk.ui.FrogoWebViewActivity

class MainSDKActivity : CoreMainActivity<ActivityMainFrogoSdkBinding>() {

    private val tes: String by lazy {
        singlePref.getPrefString("test")
    }

    override fun setupViewBinding(): ActivityMainFrogoSdkBinding {
        return ActivityMainFrogoSdkBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        showLogD<MainSDKActivity>(tes)
    }

    override fun onCreateExt(savedInstanceState: Bundle?) {
        super.onCreateExt(savedInstanceState)
        setupHideSystemUI()
        binding.apply {

            btnMenuLog.setOnClickListener {
                startActivityExt<LogActivity>()
            }

            btnMenuNotif.setOnClickListener {
                startActivityExt<MainNotifActivity>()
            }

            btnMenuNews.setOnClickListener {
                startActivityExt<NewsActivity>()
            }

            btnMenuNewsResult.setOnClickListener {
                startActivityExt<NewsResultActivity>()
            }

            btnMenuJavaActivity.setOnClickListener {
                startActivityExt<MainJavaActivity>()
            }

            btnMenuViewPager2.setOnClickListener {
                startActivityExt<VPagerActivity>()
            }

            btnError.setOnClickListener {
                throw RuntimeException("I'm a cool exception and I crashed the main thread!")
            }

            btnWebviewFrogobox.setOnClickListener {
                FrogoWebViewActivity.startActivityExt(
                    this@MainSDKActivity,
                    "https://frogobox.github.io",
                    "Frogobox"
                )
            }

            btnWebviewAmirisback.setOnClickListener {
                FrogoWebViewActivity.startActivityExt(
                    this@MainSDKActivity,
                    "https://amirisback.github.io",
                    "Faisal Amir"
                )
            }

            btnAboutUs.setOnClickListener {
                startActivityExt<FrogoAboutUsActivity>()
            }

            btnPiracyKotlin.setOnClickListener {
                startActivityExt<KotlinActivity>()
            }

            btnPiracyMain.setOnClickListener {
                startActivityExt<PiracyMainActivity>()
            }

        }
    }

}