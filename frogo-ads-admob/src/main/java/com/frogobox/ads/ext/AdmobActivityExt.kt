package com.frogobox.ads.ext

import android.widget.RelativeLayout
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.frogobox.ads.callback.FrogoAdmobBannerCallback
import com.frogobox.ads.callback.FrogoAdmobRewardedCallback
import com.frogobox.ads.core.FrogoAdmobManager
import com.google.android.libraries.ads.mobile.sdk.banner.AdSize

/**
 * Creates and attaches an instance-scoped [FrogoAdmobManager] to this [ComponentActivity].
 * The manager will automatically detach and clean up references upon [ComponentActivity.onDestroy].
 */
fun ComponentActivity.createAdmobManager(): FrogoAdmobManager {
    return FrogoAdmobManager(this)
}

/**
 * Extension to display an AdMob Banner in a given [container] without extending any Frogo activity.
 */
fun AppCompatActivity.showAdmobBanner(
    container: RelativeLayout,
    bannerAdUnitId: String,
    adSize: AdSize = AdSize.BANNER,
    timeoutMilliSecond: Int? = null,
    keyword: List<String>? = null,
    callback: FrogoAdmobBannerCallback? = null
) {
    val manager = FrogoAdmobManager(this)
    manager.showAdBannerContainer(
        context = this,
        bannerAdUnitId = bannerAdUnitId,
        mAdsSize = adSize,
        container = container,
        timeoutMilliSecond = timeoutMilliSecond,
        keyword = keyword,
        callback = callback
    )
}

/**
 * Extension to load an AdMob Rewarded ad without extending any Frogo activity.
 */
fun AppCompatActivity.loadAdmobRewarded(
    rewardedAdUnitId: String,
    timeoutMilliSecond: Int? = null,
    keyword: List<String>? = null,
    callback: FrogoAdmobRewardedCallback
) {
    com.frogobox.ads.core.FrogoAdmob.loadAdRewarded(
        activity = this,
        mAdUnitIdRewarded = rewardedAdUnitId,
        timeoutMilliSecond = timeoutMilliSecond,
        keyword = keyword,
        callback = callback
    )
}

/**
 * Extension to show an AdMob Rewarded ad without extending any Frogo activity.
 */
fun AppCompatActivity.showAdmobRewarded(
    rewardedAdUnitId: String,
    timeoutMilliSecond: Int? = null,
    keyword: List<String>? = null,
    callback: FrogoAdmobRewardedCallback
) {
    com.frogobox.ads.core.FrogoAdmob.showAdRewarded(
        activity = this,
        mAdUnitIdRewarded = rewardedAdUnitId,
        timeoutMilliSecond = timeoutMilliSecond,
        keyword = keyword,
        callback = callback
    )
}
