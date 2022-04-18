package com.frogobox.appsdk.main

import android.os.Bundle
import com.frogobox.appsdk.core.BaseActivity
import com.frogobox.appsdk.databinding.ActivityMainBinding
import com.frogobox.appsdk.log.LogActivity
import com.frogobox.appsdk.news.NewsActivity
import com.frogobox.appsdk.notification.simple.MainNotifActivity
import com.frogobox.appsdk.viewpager.VPagerActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun setupViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
    }

    override fun setupOnCreate(savedInstanceState: Bundle?) {
        setupHideSystemUI()
        binding.apply {

            btnMenuLog.setOnClickListener {
                frogoStartActivity<LogActivity>()
            }

            btnMenuNotif.setOnClickListener {
                frogoStartActivity<MainNotifActivity>()
            }

            btnMenuNews.setOnClickListener {
                frogoStartActivity<NewsActivity>()
            }

            btnMenuJavaActivity.setOnClickListener {
                frogoStartActivity<MainJavaActivity>()
            }

            btnMenuViewPager2.setOnClickListener {
                frogoStartActivity<VPagerActivity>()
            }

        }
    }

}