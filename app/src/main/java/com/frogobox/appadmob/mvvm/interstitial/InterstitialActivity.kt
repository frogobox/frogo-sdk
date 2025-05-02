package com.frogobox.appadmob.mvvm.interstitial

import android.os.Bundle
import com.frogobox.R
import com.frogobox.ads.callback.FrogoAdInterstitialCallback
import com.frogobox.ads.callback.FrogoAdmobInterstitialCallback
import com.frogobox.ads.callback.FrogoUnityAdInterstitialCallback
import com.frogobox.ads.ui.FrogoAdBindActivity
import com.frogobox.databinding.ActivityInterstitialBinding
import com.frogobox.sdk.ext.gone
import com.frogobox.sdk.ext.showLogDebug
import com.frogobox.sdk.ext.showToast
import com.frogobox.sdk.ext.visible

class InterstitialActivity : FrogoAdBindActivity<ActivityInterstitialBinding>(),
    FrogoAdmobInterstitialCallback, FrogoUnityAdInterstitialCallback, FrogoAdInterstitialCallback {

    companion object {
        private const val HTTP_TIMEOUT_MILLIS = 3000
    }

    private fun getKeyword(): MutableList<String> {
        val keywords = mutableListOf<String>()
        keywords.add("Kids")
        keywords.add("Toys")
        keywords.add("Game")
        keywords.add("Music")
        keywords.add("Piano")
        return keywords
    }

    override fun setupViewBinding(): ActivityInterstitialBinding {
        return ActivityInterstitialBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {}

    override fun onCreateExt(savedInstanceState: Bundle?) {
        setupDetailActivity("Sample Frogo Sdk Admob 2")
        setupUI()
    }


    private fun setupUI() {
        binding.apply {

            btnAdmobInterstitial.setOnClickListener {
                showAdInterstitial(getString(R.string.admob_interstitial))
            }

            btnAdmobInterstitialTimeout.setOnClickListener {
                showAdInterstitial(getString(R.string.admob_interstitial), HTTP_TIMEOUT_MILLIS)
            }

            btnAdmobInterstitialKeyword.setOnClickListener {
                showAdInterstitial(getString(R.string.admob_interstitial), getKeyword())
            }

            btnAdmobInterstitialTimeoutKeyword.setOnClickListener {
                showAdInterstitial(
                    getString(R.string.admob_interstitial),
                    HTTP_TIMEOUT_MILLIS,
                    getKeyword()
                )
            }

            btnAdmobInterstitialFailedEmpty.setOnClickListener {
                showAdInterstitial("")
            }

            btnAdmobInterstitialFailedWrong.setOnClickListener {
                showAdInterstitial("Wrong")
            }

            // -------------------------------------------------------------------------------------

            btnAdmobInterstitialCallback.setOnClickListener {
                showAdInterstitial(
                    getString(R.string.admob_interstitial),
                    this@InterstitialActivity
                )
            }

            btnAdmobInterstitialCallbackTimeout.setOnClickListener {
                showAdInterstitial(
                    getString(R.string.admob_interstitial),
                    HTTP_TIMEOUT_MILLIS,
                    this@InterstitialActivity
                )
            }

            btnAdmobInterstitialCallbackKeyword.setOnClickListener {
                showAdInterstitial(
                    getString(R.string.admob_interstitial),
                    getKeyword(),
                    this@InterstitialActivity
                )
            }

            btnAdmobInterstitialCallbackTimeoutKeyword.setOnClickListener {
                showAdInterstitial(
                    getString(R.string.admob_interstitial),
                    HTTP_TIMEOUT_MILLIS,
                    getKeyword(),
                    this@InterstitialActivity
                )
            }

            btnAdmobInterstitialCallbackFailedEmpty.setOnClickListener {
                showAdInterstitial("", this@InterstitialActivity)
            }

            btnAdmobInterstitialCallbackFailedWrong.setOnClickListener {
                showAdInterstitial("Wrong", this@InterstitialActivity)
            }

            // -------------------------------------------------------------------------------------

            btnUnityInterstitial.setOnClickListener {
                showUnityAdInterstitial(getString(R.string.unity_ad_interstitial))
            }

            btnUnityInterstitialFailedEmpty.setOnClickListener {
                showUnityAdInterstitial("")
            }

            btnUnityInterstitialFailedWrong.setOnClickListener {
                showUnityAdInterstitial("Wrong")
            }

            btnUnityInterstitialCallback.setOnClickListener {
                showUnityAdInterstitial(
                    getString(R.string.unity_ad_interstitial),
                    this@InterstitialActivity
                )
            }

            btnUnityInterstitialCallbackFailedEmpty.setOnClickListener {
                showUnityAdInterstitial("", this@InterstitialActivity)
            }

            btnUnityInterstitialCallbackFailedWrong.setOnClickListener {
                showUnityAdInterstitial("Wrong", this@InterstitialActivity)
            }

            // -------------------------------------------------------------------------------------

            btnAdmobXUnityInterstitial.setOnClickListener {
                showAdmobXUnityAdInterstitial(
                    "",
                    getString(R.string.unity_ad_interstitial),
                    this@InterstitialActivity
                )
            }

            btnAdmobXUnityInterstitialTimeout.setOnClickListener {
                showAdmobXUnityAdInterstitial(
                    "",
                    getString(R.string.unity_ad_interstitial),
                    HTTP_TIMEOUT_MILLIS,
                    this@InterstitialActivity
                )
            }

            btnAdmobXUnityInterstitialFailedWrong.setOnClickListener {
                showAdmobXUnityAdInterstitial(
                    "",
                    "Wrong",
                    this@InterstitialActivity
                )
            }

            btnAdmobXUnityInterstitialFailedEmpty.setOnClickListener {
                showAdmobXUnityAdInterstitial(
                    "",
                    "",
                    this@InterstitialActivity
                )
            }

            btnUnityXAdmobInterstitial.setOnClickListener {
                showUnityXAdmobAdInterstitial(
                    getString(R.string.admob_interstitial),
                    "",
                    this@InterstitialActivity
                )
            }

            btnUnityXAdmobInterstitialTimeout.setOnClickListener {
                showUnityXAdmobAdInterstitial(
                    getString(R.string.admob_interstitial),
                    "",
                    HTTP_TIMEOUT_MILLIS,
                    this@InterstitialActivity
                )
            }

            btnUnityXAdmobInterstitialFailedWrong.setOnClickListener {
                showUnityXAdmobAdInterstitial(
                    "Wrong",
                    "",
                    this@InterstitialActivity
                )
            }

            btnUnityXAdmobInterstitialFailedEmpty.setOnClickListener {
                showUnityXAdmobAdInterstitial(
                    "",
                    "",
                    this@InterstitialActivity
                )
            }

        }
    }

    override fun onClicked(tag: String, message: String) {}

    override fun onShowAdRequestProgress(tag: String, message: String) {
        binding.ivProgress.visible()
    }

    override fun onHideAdRequestProgress(tag: String, message: String) {
        binding.ivProgress.gone()
        showLogDebug(message)
    }

    override fun onAdDismissed(tag: String, message: String) {
        showToast(message)
    }

    override fun onAdFailed(tag: String, errorMessage: String) {
        showToast(errorMessage)
    }

    override fun onAdLoaded(tag: String, message: String) {}

    override fun onAdShowed(tag: String, message: String) {}

}