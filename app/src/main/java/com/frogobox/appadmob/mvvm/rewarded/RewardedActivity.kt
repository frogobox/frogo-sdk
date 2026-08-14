package com.frogobox.appadmob.mvvm.rewarded

import android.os.Bundle
import com.frogobox.BaseActivity
import com.frogobox.R
import com.frogobox.ads.callback.FrogoAdmobRewardedCallback
import com.frogobox.databinding.ActivityRewardedBinding
import com.frogobox.sdk.ext.gone
import com.frogobox.sdk.ext.showToast
import com.frogobox.sdk.ext.visible
import com.google.android.libraries.ads.mobile.sdk.rewarded.RewardItem

class RewardedActivity : BaseActivity<ActivityRewardedBinding>(), FrogoAdmobRewardedCallback {

    private fun getKeyword(): MutableList<String> {
        val keywords = mutableListOf<String>()
        keywords.add("Kids")
        keywords.add("Toys")
        keywords.add("Game")
        keywords.add("Music")
        keywords.add("Piano")
        return keywords
    }

    private val HTTP_TIMEOUT_MILLIS = 30000

    override fun setupViewBinding(): ActivityRewardedBinding {
        return ActivityRewardedBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDetailActivity("Rewarded Activity")
        setupUI()
    }

    private fun setupUI() {
        binding.apply {
            btnAdmobRewarded.setOnClickListener {
                showAdRewarded(
                    mAdUnitIdRewarded = getString(R.string.admob_rewarded),
                    callback = this@RewardedActivity
                )
            }

            btnAdmobRewardedTimeout.setOnClickListener {
                showAdRewarded(
                    mAdUnitIdRewarded = getString(R.string.admob_rewarded),
                    timeoutMilliSecond = HTTP_TIMEOUT_MILLIS,
                    callback = this@RewardedActivity
                )
            }

            btnAdmobRewardedKeyword.setOnClickListener {
                showAdRewarded(
                    mAdUnitIdRewarded = getString(R.string.admob_rewarded),
                    keyword = getKeyword(),
                    callback = this@RewardedActivity
                )
            }

            btnAdmobRewardedTimeoutKeyword.setOnClickListener {
                showAdRewarded(
                    mAdUnitIdRewarded = getString(R.string.admob_rewarded),
                    timeoutMilliSecond = HTTP_TIMEOUT_MILLIS,
                    keyword = getKeyword(),
                    callback = this@RewardedActivity
                )
            }


            btnAdmobRewardedInterstitial.setOnClickListener {
                showAdRewardedInterstitial(
                    mAdUnitIdRewardedInterstitial = getString(R.string.admob_rewarded_interstitial),
                    callback = this@RewardedActivity
                )
            }

            btnAdmobRewardedInterstitialTimeout.setOnClickListener {
                showAdRewardedInterstitial(
                    mAdUnitIdRewardedInterstitial = getString(R.string.admob_rewarded_interstitial),
                    timeoutMilliSecond = HTTP_TIMEOUT_MILLIS,
                    callback = this@RewardedActivity
                )
            }

            btnAdmobRewardedInterstitialKeyword.setOnClickListener {
                showAdRewardedInterstitial(
                    mAdUnitIdRewardedInterstitial = getString(R.string.admob_rewarded_interstitial),
                    keyword = getKeyword(),
                    callback = this@RewardedActivity
                )
            }

            btnAdmobRewardedInterstitialTimeoutKeyword.setOnClickListener {
                showAdRewardedInterstitial(
                    mAdUnitIdRewardedInterstitial = getString(R.string.admob_rewarded_interstitial),
                    timeoutMilliSecond = HTTP_TIMEOUT_MILLIS,
                    keyword = getKeyword(),
                    callback = this@RewardedActivity
                )
            }
        }
    }

    override fun onUserEarnedReward(tag: String, rewardItem: RewardItem) {
        showToast("${rewardItem.amount}")
    }

    override fun onShowAdRequestProgress(tag: String, message: String) {
        binding.ivProgress.visible()
    }

    override fun onHideAdRequestProgress(tag: String, message: String) {
        binding.ivProgress.gone()
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