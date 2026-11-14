package com.frogobox

import androidx.viewbinding.ViewBinding
import androidx.viewpager2.widget.ViewPager2
import com.frogobox.sdk.delegate.preference.PreferenceDelegates
import com.frogobox.sdk.delegate.preference.PreferenceDelegatesImpl
import com.frogobox.sdk.view.FrogoBindActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

/**
 * Created by faisalamircs on 02/11/2025
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * GitHub   : github.com/amirisback
 * -----------------------------------------
 */

abstract class BaseActivity<VB : ViewBinding> : FrogoBindActivity<VB>() {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface BaseActivityEntryPoint {
        fun singlePref(): PreferenceDelegatesImpl
    }

    protected val singlePref: PreferenceDelegates by lazy {
        val entryPoint = EntryPointAccessors.fromApplication(
            applicationContext,
            BaseActivityEntryPoint::class.java
        )
        entryPoint.singlePref()
    }

    override fun setupDebugMode(): Boolean {
        return BuildConfig.DEBUG
    }

    override fun setupContentView() {
        super.setupContentView()
        setContentView(binding.root)
    }

    protected fun setupTabTitles(
        tabLayout: TabLayout,
        viewPager2: ViewPager2,
        titles: MutableList<String>,
    ) {
        TabLayoutMediator(tabLayout, viewPager2) { tab: TabLayout.Tab, position: Int ->
            tab.text = titles[position]
        }.attach()
    }

}
