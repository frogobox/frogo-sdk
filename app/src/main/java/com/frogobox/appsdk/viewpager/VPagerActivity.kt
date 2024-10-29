package com.frogobox.appsdk.viewpager

import android.os.Bundle
import com.frogobox.appsdk.core.BaseActivity
import com.frogobox.databinding.ActivityVpagerBinding
import com.frogobox.appsdk.news.NewsMainFragment
import com.frogobox.sdk.ext.getViewPager2Adapter
import com.frogobox.sdk.ext.setupWithViewPager2

class VPagerActivity : BaseActivity<ActivityVpagerBinding>() {

    override fun setupViewBinding(): ActivityVpagerBinding {
        return ActivityVpagerBinding.inflate(layoutInflater)
    }

    override fun onCreateExt(savedInstanceState: Bundle?) {
        super.onCreateExt(savedInstanceState)
        setupDetailActivity("ViewPager2")
        binding.viewPager2.adapter = getViewPager2Adapter().apply {
            addFragment(VPagerFragment(), "Fragment 1")
            addFragment(NewsMainFragment(), "Fragment 2")
            addFragment(VPagerFragment(), "Fragment 3")
        }

        binding.tabLayout.setupWithViewPager2(binding.viewPager2)

    }
}