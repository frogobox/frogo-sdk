package com.frogobox.appadmob.mvvm.interstitial

import android.os.Bundle
import com.frogobox.BaseActivity
import com.frogobox.R
import com.frogobox.ads.callback.FrogoAdInterstitialCallback
import com.frogobox.ads.callback.FrogoAdmobInterstitialCallback
import com.frogobox.ads.callback.FrogoUnityAdInterstitialCallback
import com.frogobox.databinding.ActivityInterstitialBinding
import com.frogobox.sdk.ext.gone
import com.frogobox.sdk.ext.showLogDebug
import com.frogobox.sdk.ext.showToast
import com.frogobox.sdk.ext.visible

class InterstitialActivity : BaseActivity<ActivityInterstitialBinding>(),
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
                showAdInterstitial(interstitialAdUnitId = getString(R.string.admob_interstitial))
            }

            btnAdmobInterstitialTimeout.setOnClickListener {
                showAdInterstitial(
                    interstitialAdUnitId = getString(R.string.admob_interstitial),
                    timeoutMilliSecond = HTTP_TIMEOUT_MILLIS
                )
            }

            btnAdmobInterstitialKeyword.setOnClickListener {
                showAdInterstitial(
                    interstitialAdUnitId = getString(R.string.admob_interstitial),
                    keyword = getKeyword()
                )
            }

            btnAdmobInterstitialTimeoutKeyword.setOnClickListener {
                showAdInterstitial(
                    interstitialAdUnitId = getString(R.string.admob_interstitial),
                    timeoutMilliSecond = HTTP_TIMEOUT_MILLIS,
                    keyword = getKeyword()
                )
            }

            btnAdmobInterstitialFailedEmpty.setOnClickListener {
                showAdInterstitial(interstitialAdUnitId = "")
            }

            btnAdmobInterstitialFailedWrong.setOnClickListener {
                showAdInterstitial(interstitialAdUnitId = "Wrong")
            }

            // -------------------------------------------------------------------------------------

            btnAdmobInterstitialCallback.setOnClickListener {
                showAdInterstitial(
                    interstitialAdUnitId = getString(R.string.admob_interstitial),
                    callback = this@InterstitialActivity
                )
            }

            btnAdmobInterstitialCallbackTimeout.setOnClickListener {
                showAdInterstitial(
                    interstitialAdUnitId = getString(R.string.admob_interstitial),
                    timeoutMilliSecond = HTTP_TIMEOUT_MILLIS,
                    callback = this@InterstitialActivity
                )
            }

            btnAdmobInterstitialCallbackKeyword.setOnClickListener {
                showAdInterstitial(
                    interstitialAdUnitId = getString(R.string.admob_interstitial),
                    keyword = getKeyword(),
                    callback =  this@InterstitialActivity
                )
            }

            btnAdmobInterstitialCallbackTimeoutKeyword.setOnClickListener {
                showAdInterstitial(
                    interstitialAdUnitId = getString(R.string.admob_interstitial),
                    timeoutMilliSecond = HTTP_TIMEOUT_MILLIS,
                    keyword = getKeyword(),
                    callback = this@InterstitialActivity
                )
            }

            btnAdmobInterstitialCallbackFailedEmpty.setOnClickListener {
                showAdInterstitial(interstitialAdUnitId = "", callback = this@InterstitialActivity)
            }

            btnAdmobInterstitialCallbackFailedWrong.setOnClickListener {
                showAdInterstitial(interstitialAdUnitId = "Wrong", callback = this@InterstitialActivity)
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
                    adInterstitialUnitId = getString(R.string.unity_ad_interstitial),
                    callback = this@InterstitialActivity
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
                    admobInterstitialId = "",
                    unityInterstitialId = getString(R.string.unity_ad_interstitial),
                    callback = this@InterstitialActivity
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
                    admobInterstitialId = "",
                    unityInterstitialId = "Wrong",
                    callback = this@InterstitialActivity
                )
            }

            btnAdmobXUnityInterstitialFailedEmpty.setOnClickListener {
                showAdmobXUnityAdInterstitial(
                    admobInterstitialId = "",
                    unityInterstitialId = "",
                    callback = this@InterstitialActivity
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