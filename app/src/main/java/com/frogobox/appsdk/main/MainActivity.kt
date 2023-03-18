package com.frogobox.appsdk.main

import android.os.Bundle
import com.frogobox.appsdk.databinding.ActivityMainBinding
import com.frogobox.appsdk.log.LogActivity
import com.frogobox.appsdk.news.NewsActivity
import com.frogobox.appsdk.notification.simple.MainNotifActivity
import com.frogobox.appsdk.viewpager.VPagerActivity
import com.frogobox.sdk.ext.showLogD
import com.frogobox.sdk.ext.startActivityExt
import com.frogobox.sdk.ui.FrogoAboutUsActivity
import com.frogobox.sdk.ui.FrogoWebViewActivity

class MainActivity : CoreMainActivity<ActivityMainBinding>() {

    private val tes: String by lazy {
        loadPrefString("test")
    }

    override fun setupViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        showLogD<MainActivity>(tes)
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
                FrogoWebViewActivity.startActivityExt(this@MainActivity,
                    "https://frogobox.github.io",
                    "Frogobox")
            }

            btnWebviewAmirisback.setOnClickListener {
                FrogoWebViewActivity.startActivityExt(this@MainActivity,
                    "https://amirisback.github.io",
                    "Faisal Amir")
            }

            btnAboutUs.setOnClickListener {
                startActivityExt<FrogoAboutUsActivity>()
            }

        }
    }

}