# Frogo Ext Ads — Full API Reference

Package: `com.frogobox.ads`

## Application

### FrogoAdmobApplication
Base Application class for AdMob-enabled apps.

```kotlin
class MyApp : FrogoAdmobApplication() {
    override fun onCreateExt() {
        super.onCreateExt()
    }
}
```

---

## Delegate Pattern

The recommended approach uses Kotlin's **delegation pattern** for clean separation of ad logic.

### AdmobDelegates Interface

```kotlin
interface AdmobDelegates {
    fun setupAdmobDelegates(activity: AppCompatActivity)
    fun showAdConsent(callback: IFrogoAdConsent)
    fun setupAdmobApp()

    // Banner Ads (8 overloads)
    fun showAdBanner(mAdView: AdView)
    fun showAdBanner(mAdView: AdView, timeoutMilliSecond: Int)
    fun showAdBanner(mAdView: AdView, keyword: List<String>)
    fun showAdBanner(mAdView: AdView, timeoutMilliSecond: Int, keyword: List<String>)
    fun showAdBanner(mAdView: AdView, callback: FrogoAdmobBannerCallback)
    fun showAdBanner(mAdView: AdView, timeoutMilliSecond: Int, callback: FrogoAdmobBannerCallback)
    fun showAdBanner(mAdView: AdView, keyword: List<String>, callback: FrogoAdmobBannerCallback)
    fun showAdBanner(mAdView: AdView, timeoutMilliSecond: Int, keyword: List<String>, callback: FrogoAdmobBannerCallback)

    // Banner Ads with Container (8 overloads)
    fun showAdBannerContainer(bannerAdUnitId: String, mAdsSize: AdSize, container: RelativeLayout)
    fun showAdBannerContainer(bannerAdUnitId: String, mAdsSize: AdSize, container: RelativeLayout, timeoutMilliSecond: Int)
    fun showAdBannerContainer(bannerAdUnitId: String, mAdsSize: AdSize, container: RelativeLayout, keyword: List<String>)
    fun showAdBannerContainer(bannerAdUnitId: String, mAdsSize: AdSize, container: RelativeLayout, timeoutMilliSecond: Int, keyword: List<String>)
    fun showAdBannerContainer(bannerAdUnitId: String, mAdsSize: AdSize, container: RelativeLayout, callback: FrogoAdmobBannerCallback)
    fun showAdBannerContainer(bannerAdUnitId: String, mAdsSize: AdSize, container: RelativeLayout, timeoutMilliSecond: Int, callback: FrogoAdmobBannerCallback)
    fun showAdBannerContainer(bannerAdUnitId: String, mAdsSize: AdSize, container: RelativeLayout, keyword: List<String>, callback: FrogoAdmobBannerCallback)
    fun showAdBannerContainer(bannerAdUnitId: String, mAdsSize: AdSize, container: RelativeLayout, timeoutMilliSecond: Int, keyword: List<String>, callback: FrogoAdmobBannerCallback)

    // Interstitial Ads (8 overloads)
    fun showAdInterstitial(interstitialAdUnitId: String)
    fun showAdInterstitial(interstitialAdUnitId: String, timeoutMilliSecond: Int)
    fun showAdInterstitial(interstitialAdUnitId: String, keyword: List<String>)
    fun showAdInterstitial(interstitialAdUnitId: String, timeoutMilliSecond: Int, keyword: List<String>)
    fun showAdInterstitial(interstitialAdUnitId: String, callback: FrogoAdmobInterstitialCallback)
    fun showAdInterstitial(interstitialAdUnitId: String, timeoutMilliSecond: Int, callback: FrogoAdmobInterstitialCallback)
    fun showAdInterstitial(interstitialAdUnitId: String, keyword: List<String>, callback: FrogoAdmobInterstitialCallback)
    fun showAdInterstitial(interstitialAdUnitId: String, timeoutMilliSecond: Int, keyword: List<String>, callback: FrogoAdmobInterstitialCallback)

    // Rewarded Ads (4 overloads)
    fun showAdRewarded(mAdUnitIdRewarded: String, callback: FrogoAdmobRewardedCallback)
    fun showAdRewarded(mAdUnitIdRewarded: String, timeoutMilliSecond: Int, callback: FrogoAdmobRewardedCallback)
    fun showAdRewarded(mAdUnitIdRewarded: String, keyword: List<String>, callback: FrogoAdmobRewardedCallback)
    fun showAdRewarded(mAdUnitIdRewarded: String, timeoutMilliSecond: Int, keyword: List<String>, callback: FrogoAdmobRewardedCallback)

    // Rewarded Interstitial Ads (4 overloads)
    fun showAdRewardedInterstitial(mAdUnitIdRewardedInterstitial: String, callback: FrogoAdmobRewardedCallback)
    fun showAdRewardedInterstitial(mAdUnitIdRewardedInterstitial: String, timeoutMilliSecond: Int, callback: FrogoAdmobRewardedCallback)
    fun showAdRewardedInterstitial(mAdUnitIdRewardedInterstitial: String, keyword: List<String>, callback: FrogoAdmobRewardedCallback)
    fun showAdRewardedInterstitial(mAdUnitIdRewardedInterstitial: String, timeoutMilliSecond: Int, keyword: List<String>, callback: FrogoAdmobRewardedCallback)
}
```

### Implementation Class
```kotlin
class AdmobDelegatesImpl : AdmobDelegates { /* full implementation */ }
```

### Usage Example

```kotlin
class MyActivity : AppCompatActivity(), AdmobDelegates by AdmobDelegatesImpl() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupAdmobDelegates(this)

        // Show banner ad using XML AdView
        showAdBanner(binding.adView)

        // Show banner ad with timeout and keywords
        showAdBanner(
            mAdView = binding.adView,
            timeoutMilliSecond = 5000,
            keyword = listOf("games", "apps"),
            callback = object : FrogoAdmobBannerCallback {
                override fun onAdLoaded() { }
                override fun onAdFailedToLoad(error: String) { }
                override fun onAdOpened() { }
                override fun onAdClosed() { }
                override fun onAdClicked() { }
            }
        )

        // Show banner in a container programmatically
        showAdBannerContainer(
            bannerAdUnitId = "ca-app-pub-xxxxx/xxxxx",
            mAdsSize = AdSize.BANNER,
            container = binding.adContainer
        )

        // Show interstitial ad
        showAdInterstitial("ca-app-pub-xxxxx/xxxxx")

        // Show rewarded ad
        showAdRewarded("ca-app-pub-xxxxx/xxxxx", object : FrogoAdmobRewardedCallback {
            override fun onUserEarnedReward(rewardItem: RewardItem) {
                // Grant reward
            }
            override fun onAdDismissed() { }
            override fun onAdFailedToLoad() { }
        })
    }
}
```

---

## FrogoAdDelegates

Higher-level combined delegate that wraps both AdMob and Unity ad functionality.

```kotlin
interface FrogoAdDelegates {
    fun setupFrogoAdDelegates(activity: AppCompatActivity)
    // ... combined ad methods
}
```

---

## Unity Ads

### UnityAdDelegates Interface
```kotlin
interface UnityAdDelegates {
    fun setupUnityAdDelegates(activity: AppCompatActivity)
    fun showUnityBanner(adUnitId: String, container: RelativeLayout)
    fun showUnityInterstitial(adUnitId: String)
}
```

### Usage
```kotlin
class MyActivity : AppCompatActivity(), UnityAdDelegates by UnityAdDelegatesImpl() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUnityAdDelegates(this)
        showUnityBanner("unity-banner-id", binding.adContainer)
    }
}
```

---

## Callbacks

### FrogoAdmobBannerCallback
```kotlin
interface FrogoAdmobBannerCallback {
    fun onAdLoaded()
    fun onAdFailedToLoad(error: String)
    fun onAdOpened()
    fun onAdClosed()
    fun onAdClicked()
}
```

### FrogoAdmobInterstitialCallback
```kotlin
interface FrogoAdmobInterstitialCallback {
    fun onAdLoaded()
    fun onAdFailedToLoad(error: String)
    fun onAdDismissed()
    fun onShowAdRequestProgress()
    fun onHideAdRequestProgress()
}
```

### FrogoAdmobRewardedCallback
```kotlin
interface FrogoAdmobRewardedCallback {
    fun onUserEarnedReward(rewardItem: RewardItem)
    fun onAdDismissed()
    fun onAdFailedToLoad()
}
```

### IFrogoAdConsent
```kotlin
interface IFrogoAdConsent {
    fun onConsentSuccess()
    fun onConsentError(error: String)
}
```

---

## Required Dependencies (via `libs.versions.toml`)

```toml
[versions]
googleAdmob = "25.2.0"
unityAd = "4.17.0"

[libraries]
ads-google-admob = { group = "com.google.android.gms", name = "play-services-ads", version.ref = "googleAdmob" }
ads-unityAd = { group = "com.unity3d.ads", name = "unity-ads", version.ref = "unityAd" }
```

## AndroidManifest.xml Setup

```xml
<manifest>
    <application>
        <meta-data
            android:name="com.google.android.gms.ads.APPLICATION_ID"
            android:value="ca-app-pub-xxxxx~xxxxx"/>
    </application>
</manifest>
```
