package com.frogobox.appadmob.base

import android.content.SharedPreferences
import android.view.Menu
import android.view.MenuItem
import androidx.viewbinding.ViewBinding
import com.frogobox.BuildConfig
import com.frogobox.R
import com.frogobox.ads.model.FrogoAdmobId
import com.frogobox.ads.source.FrogoAdmobApiResponse
import com.frogobox.ads.source.FrogoAdmobRepository
import com.frogobox.ads.ui.FrogoAdActivity
import com.frogobox.sdk.ext.showLogDebug
import com.google.gson.Gson
import org.koin.android.ext.android.inject

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * ImplementationAdmob
 * Copyright (C) 27/11/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.admobhelper.base
 *
 */
abstract class BaseActivity<VB : ViewBinding> : FrogoAdActivity() {

    protected val binding: VB by lazy { setupViewBinding() }

    protected val frogoSharedPreferences: SharedPreferences by inject()

    abstract fun setupViewBinding(): VB

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
        val baseUrl =
            "https://raw.githubusercontent.com/amirisback/frogo-admob/master/app/src/main/assets/"
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

                override fun onFinish() {
                }

                override fun onShowProgress() {
                }

                override fun onHideProgress() {
                }
            })
    }

    protected inline fun <reified Model> baseGetExtraData(extraKey: String): Model {
        val extraIntent = intent.getStringExtra(extraKey)
        return Gson().fromJson(extraIntent, Model::class.java)
    }

    protected fun <Model, VB : ViewBinding> baseFragmentNewInstance(
        fragment: BaseFragment<VB>,
        argumentKey: String,
        extraDataResult: Model
    ) {
        fragment.baseNewInstance(argumentKey, extraDataResult)
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

}