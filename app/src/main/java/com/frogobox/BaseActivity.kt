package com.frogobox

import androidx.viewbinding.ViewBinding
import androidx.viewpager2.widget.ViewPager2
import com.frogobox.ads.model.FrogoAdmobId
import com.frogobox.ads.source.FrogoAdmobApiResponse
import com.frogobox.ads.source.FrogoAdmobRepository
import com.frogobox.ads.ui.FrogoAdBindActivity
import com.frogobox.sdk.delegate.preference.PreferenceDelegates
import com.frogobox.sdk.delegate.preference.PreferenceDelegatesImpl
import com.frogobox.sdk.ext.showLogDebug
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


abstract class BaseActivity<VB : ViewBinding> : FrogoAdBindActivity<VB>() {

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

    override fun setupMonetized() {
        super.setupMonetized()
        setupUnityAdApp(BuildConfig.DEBUG, getString(R.string.unity_ad_game_id))
    }

    override fun setupContentView() {
        super.setupContentView()
        setContentView(binding.root)
    }

    protected fun requestAdmobApi() {
        val baseUrl = "https://raw.githubusercontent.com/amirisback/frogo-admob/master/app/src/main/assets/"
        val frogoAdmobRepository = FrogoAdmobRepository(BuildConfig.DEBUG, baseUrl)
        frogoAdmobRepository.usingClient(this)
        frogoAdmobRepository.getFrogoAdmobId(
            "admob_id.json",
            object : FrogoAdmobApiResponse<FrogoAdmobId> {
                override fun onSuccess(data: FrogoAdmobId) {
                    runOnUiThread {
                        showLogDebug(data.admobAppId)
                        showLogDebug(data.admobBannerID[0])
                        showLogDebug(data.admobInterstitialID[0])
                        showLogDebug(data.testAdmobAppId)
                        showLogDebug(data.testAdmobBanner)
                        showLogDebug(data.testAdmobInterstitial)
                    }
                }

                override fun onFailed(statusCode: Int, errorMessage: String) {
                    runOnUiThread {
                        showLogDebug(errorMessage)
                    }
                }

                override fun onFinish() {}
                override fun onShowProgress() {}
                override fun onHideProgress() {}
            })
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