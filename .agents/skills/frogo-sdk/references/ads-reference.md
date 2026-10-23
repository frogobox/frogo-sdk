# Frogo Ext Ads — Full API Reference (v3.0.13)

Package: `com.frogobox.ads`  
Artifact: `com.github.frogobox.frogo-sdk:frogo-ext-ads:3.0.13`

> [!IMPORTANT]
> **Next-Gen Mobile Ads SDK Architecture**:
> `frogo-ext-ads` exclusively utilizes the official Google Mobile Ads SDK Next-Gen (`com.google.android.libraries.ads.mobile.sdk:1.6.0`).
> Fully aligned with official Google best practices: [googleads/gma-next-gen-sdk-android-examples](https://github.com/googleads/gma-next-gen-sdk-android-examples).
> Never import legacy `com.google.android.gms.ads.*`. All legacy artifacts are excluded.

---

## 1. Application Setup

### `FrogoAdmobApplication`
Location: `com.frogobox.ads.FrogoAdmobApplication`  
Base application class that initializes the Google Mobile Ads SDK on a background coroutine thread and manages App Open Ads lifecycle via `ProcessLifecycleOwner`.

```kotlin
package com.example.app

import com.frogobox.ads.FrogoAdmobApplication

class MyApp : FrogoAdmobApplication() {
    override fun onCreate() {
        super.onCreate()
        // InitializationConfig is auto-configured with APPLICATION_ID from AndroidManifest
    }

    override fun getAdOpenAppUnitId(context: android.content.Context?): String {
        return "ca-app-pub-3940256099942544/9257395921" // Return your App Open Ad Unit ID
    }
}
```

### AndroidManifest.xml Requirement:
```xml
<application
    android:name=".MyApp" ...>
    <meta-data
        android:name="com.google.android.gms.ads.APPLICATION_ID"
        android:value="ca-app-pub-3940256099942544~3347511713" />
</application>
```

---

## 2. Next-Gen AdMob Delegates (`com.frogobox.ads.delegate`)

### AdmobDelegates Interface
```kotlin
package com.frogobox.ads.delegate

import androidx.appcompat.app.AppCompatActivity
import android.widget.RelativeLayout
import com.google.android.libraries.ads.mobile.sdk.banner.AdSize
import com.google.android.libraries.ads.mobile.sdk.banner.AdView
import com.frogobox.ads.callback.FrogoAdmobBannerCallback
import com.frogobox.ads.callback.FrogoAdmobInterstitialCallback
import com.frogobox.ads.callback.FrogoAdmobRewardedCallback
import com.frogobox.ads.callback.IFrogoAdConsent

interface AdmobDelegates {
    fun setupAdmobDelegates(activity: AppCompatActivity)
    fun showAdConsent(callback: IFrogoAdConsent)
    fun setupAdmobApp()

    // Banner Ads
    fun showAdBanner(mAdView: AdView)
    fun showAdBanner(mAdView: AdView, timeoutMilliSecond: Int)
    fun showAdBanner(mAdView: AdView, keyword: List<String>)
    fun showAdBanner(mAdView: AdView, timeoutMilliSecond: Int, keyword: List<String>)
    fun showAdBanner(mAdView: AdView, callback: FrogoAdmobBannerCallback)
    fun showAdBanner(mAdView: AdView, timeoutMilliSecond: Int, callback: FrogoAdmobBannerCallback)
    fun showAdBanner(mAdView: AdView, keyword: List<String>, callback: FrogoAdmobBannerCallback)
    fun showAdBanner(mAdView: AdView, timeoutMilliSecond: Int, keyword: List<String>, callback: FrogoAdmobBannerCallback)

    // Banner Ads with RelativeLayout Container
    fun showAdBannerContainer(bannerAdUnitId: String, mAdsSize: AdSize, container: RelativeLayout)
    fun showAdBannerContainer(bannerAdUnitId: String, mAdsSize: AdSize, container: RelativeLayout, timeoutMilliSecond: Int)
    fun showAdBannerContainer(bannerAdUnitId: String, mAdsSize: AdSize, container: RelativeLayout, callback: FrogoAdmobBannerCallback)

    // Interstitial Ads
    fun showAdInterstitial(interstitialAdUnitId: String)
    fun showAdInterstitial(interstitialAdUnitId: String, timeoutMilliSecond: Int)
    fun showAdInterstitial(interstitialAdUnitId: String, keyword: List<String>)
    fun showAdInterstitial(interstitialAdUnitId: String, callback: FrogoAdmobInterstitialCallback)
    fun showAdInterstitial(interstitialAdUnitId: String, timeoutMilliSecond: Int, callback: FrogoAdmobInterstitialCallback)

    // Rewarded Ads
    fun showAdRewarded(mAdUnitIdRewarded: String, callback: FrogoAdmobRewardedCallback)
    fun showAdRewarded(mAdUnitIdRewarded: String, timeoutMilliSecond: Int, callback: FrogoAdmobRewardedCallback)

    // Rewarded Interstitial Ads
    fun showAdRewardedInterstitial(mAdUnitIdRewardedInterstitial: String, callback: FrogoAdmobRewardedCallback)
    fun showAdRewardedInterstitial(mAdUnitIdRewardedInterstitial: String, timeoutMilliSecond: Int, callback: FrogoAdmobRewardedCallback)
}
```

### XML Activity Usage (Delegate Pattern):
```kotlin
package com.example.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.frogobox.ads.delegate.AdmobDelegates
import com.frogobox.ads.delegate.AdmobDelegatesImpl
import com.frogobox.ads.callback.FrogoAdmobRewardedCallback
import com.google.android.libraries.ads.mobile.sdk.rewarded.RewardItem

class MainActivity : AppCompatActivity(), AdmobDelegates by AdmobDelegatesImpl() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupAdmobDelegates(this)

        // Show Next-Gen banner
        showAdBanner(binding.adView)

        // Show Rewarded
        showAdRewarded("ca-app-pub-3940256099942544/5224354917", object : FrogoAdmobRewardedCallback {
            override fun onUserEarnedReward(tag: String, rewardItem: RewardItem) {
                // Reward amount: rewardItem.amount
            }
            override fun onShowAdRequestProgress(tag: String, message: String) {}
            override fun onHideAdRequestProgress(tag: String, message: String) {}
            override fun onAdDismissed(tag: String, message: String) {}
            override fun onAdFailed(tag: String, errorMessage: String) {}
            override fun onAdLoaded(tag: String, message: String) {}
            override fun onAdShowed(tag: String, message: String) {}
        })
    }
}
```

---

## 3. Unity Ads Delegates (`com.frogobox.ads.delegate`)

### UnityAdDelegates Interface
```kotlin
package com.frogobox.ads.delegate

import androidx.appcompat.app.AppCompatActivity
import com.frogobox.ads.callback.FrogoUnityAdInitializationCallback
import com.frogobox.ads.callback.FrogoUnityAdInterstitialCallback

interface UnityAdDelegates {
    fun setupUnityAdDelegates(activity: AppCompatActivity)
    fun setupUnityAdApp(testMode: Boolean, unityGameId: String, callback: FrogoUnityAdInitializationCallback? = null)
    fun showUnityAdInterstitial(adInterstitialUnitId: String, callback: FrogoUnityAdInterstitialCallback? = null)
}
```

### Implementation Class:
`UnityAdDelegatesImpl()`

---

## 4. Hybrid Mediation Fallback Delegates (`FrogoAdDelegates`)

Mediation delegates that attempt one network and automatically fallback to the other if loading fails:
- **AdMob with Unity Ads Fallback**: `showAdmobXUnityAdInterstitial`
- **Unity Ads with AdMob Fallback**: `showUnityXAdmobAdInterstitial`

```kotlin
interface FrogoAdDelegates {
    fun setupFrogoAdDelegates(activity: AppCompatActivity)
    fun showAdmobXUnityAdInterstitial(admobInterstitialId: String, unityInterstitialId: String, callback: FrogoAdInterstitialCallback)
    fun showAdmobXUnityAdInterstitial(admobInterstitialId: String, unityInterstitialId: String, timeout: Int, callback: FrogoAdInterstitialCallback)
    fun showUnityXAdmobAdInterstitial(admobInterstitialId: String, unityInterstitialId: String, callback: FrogoAdInterstitialCallback)
    fun showUnityXAdmobAdInterstitial(admobInterstitialId: String, unityInterstitialId: String, timeout: Int, callback: FrogoAdInterstitialCallback)
}
```

---

## 5. Compose Ad Activities (`com.frogobox.ads.ui.compose`)

Pre-built Activities combining Jetpack Compose with Ad delegates:

| Activity | Base Class | Features |
| :--- | :--- | :--- |
| `FrogoAdComposeActivity` | `FrogoComposeActivity` | Full delegation: AdMob (`AdmobDelegates`), Unity (`UnityAdDelegates`), and Hybrid fallback (`FrogoAdDelegates`) |
| `FrogoAdmobComposeActivity` | `FrogoComposeActivity` | AdMob delegates only |
| `FrogoUnityAdComposeActivity` | `FrogoComposeActivity` | Unity Ads delegates only |
| `FrogoAdBindComposeActivity<VB>` | `FrogoComposeActivity` | Full ad delegates + ViewBinding |
| `AdComposeActivity` | `AppCompatActivity` | Non-Frogo base with full ad delegates |
| `AdmobComposeActivity` | `AppCompatActivity` | Non-Frogo base with AdMob delegates |
| `UnityAdComposeActivity` | `AppCompatActivity` | Non-Frogo base with Unity Ads delegates |

---

## 6. FrogoBannerAdCompose (Jetpack Compose Banner)

Location: `com.frogobox.ads.ui.compose.FrogoBannerAdCompose`

A modern, declarative Jetpack Compose wrapper for Google Mobile Ads Next-Gen Banner Ads ([AdView]):
- **Automatic Lifecycle Management:** Calls `adView.destroy()` on `DisposableEffect.onDispose` when leaving composition, preventing memory leaks.
- **Preview Support:** Renders a surface placeholder in `@Preview` and `LocalInspectionMode`.
- **Targeting & Customization:** Supports custom `AdSize`, timeout, keyword targeting, and callbacks.

### Composable Signature:
```kotlin
@Composable
fun FrogoBannerAdCompose(
    adUnitId: String,
    modifier: Modifier = Modifier,
    adSize: AdSize = AdSize.BANNER,
    timeoutMilliSecond: Int? = null,
    keyword: List<String>? = null,
    callback: FrogoAdmobBannerCallback? = null
)
```

### Example: Compose Screen with Ads & Banner
```kotlin
package com.example.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.frogobox.ads.ui.compose.FrogoAdmobComposeActivity
import com.frogobox.ads.ui.compose.FrogoBannerAdCompose
import com.google.android.libraries.ads.mobile.sdk.banner.AdSize

class MyAdmobScreenActivity : FrogoAdmobComposeActivity() {

    @Composable
    override fun SetupCompose() {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Button(onClick = {
                showAdInterstitial("ca-app-pub-3940256099942544/1033173712")
            }) {
                Text("Show Interstitial Ad")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Modern Banner Ad in Compose with auto-lifecycle disposal & collapsible support
            FrogoBannerAdCompose(
                adUnitId = "ca-app-pub-3940256099942544/6300978111",
                modifier = Modifier.fillMaxWidth(),
                adSize = FrogoAdmobBannerAdaptive.largeAnchored(this, 360),
                collapsible = "bottom"
            )
        }
    }
}
```

---

## 7. Modern UMP Consent Management (`FrogoAdConsent`)

Location: `com.frogobox.ads.core.FrogoAdConsent`

Adheres directly to official Google `GoogleMobileAdsConsentManager` patterns:

```kotlin
// 1. Gather consent upon app startup
FrogoAdConsent.gatherConsent(
    activity = this,
    testDeviceHashedId = if (BuildConfig.DEBUG) "TEST_DEVICE_HASHED_ID" else null
) { formError ->
    if (formError != null) {
        Log.e("Consent", "Error: ${formError.message}")
    }
    // 2. Check if ads can be requested
    if (FrogoAdConsent.canRequestAds(this)) {
        initializeMobileAds()
    }
}

// 3. Privacy Options Form (GDPR settings button in Settings / About screen)
if (FrogoAdConsent.isPrivacyOptionsRequired(this)) {
    privacyOptionsButton.visibility = View.VISIBLE
    privacyOptionsButton.setOnClickListener {
        FrogoAdConsent.showPrivacyOptionsForm(this) { formError ->
            if (formError != null) Log.e("Consent", "Error: ${formError.message}")
        }
    }
}
```

---

## 8. Next-Gen Preloaders (`FrogoAdmobPreloader`)

Location: `com.frogobox.ads.core.FrogoAdmobPreloader`

Google Mobile Ads SDK Next-Gen introduces official Preloaders to manage background ad memory buffers without manual caching logic:

```kotlin
// 1. Start preloading ads with specified buffer size
FrogoAdmobPreloader.startInterstitialPreload(adUnitId = "ca-app-pub-3940256099942544/1033173712", bufferSize = 2)
FrogoAdmobPreloader.startRewardedPreload(adUnitId = "ca-app-pub-3940256099942544/5224354917", bufferSize = 2)
FrogoAdmobPreloader.startAppOpenPreload(adUnitId = "ca-app-pub-3940256099942544/9257395921", bufferSize = 1)

// 2. Check availability
if (FrogoAdmobPreloader.isInterstitialAvailable(adUnitId)) {
    // 3. Poll and show immediately
    FrogoAdmobPreloader.pollAndShowInterstitial(activity, adUnitId, callback)
}

// Also accessible directly via instance manager:
admobManager.pollAndShowInterstitialPreload(activity, adUnitId, callback)
admobManager.pollAndShowRewardedPreload(activity, adUnitId, callback)
```

---

## 9. Collapsible Banners & Adaptive Sizing

### Adaptive Banner Sizing:
```kotlin
// Large anchored adaptive banner (recommended for phone/tablet screens):
val adSize = FrogoAdmobBannerAdaptive.largeAnchored(context, containerWidthDp)

// Inline adaptive banner (ideal for LazyColumn / RecyclerView):
val inlineSize = FrogoAdmobBannerAdaptive.inline(context, containerWidthDp)
```

### Collapsible Banner Configuration:
Pass `collapsible = "bottom"` or `collapsible = "top"` to request an expandable overlay banner that automatically collapses to standard height after interaction:
```kotlin
FrogoBannerAdCompose(
    adUnitId = "ca-app-pub-3940256099942544/6300978111",
    adSize = FrogoAdmobBannerAdaptive.largeAnchored(LocalContext.current, 360),
    collapsible = "bottom",
    callback = object : FrogoAdmobBannerCallback {
        override fun onAdRefreshed(tag: String, message: String) {
            Log.d("Banner", "Auto-refreshed: $message")
        }
    }
)
```

---

## 10. Jetpack Compose Native Ads (`FrogoNativeAdCompose`)

Location: `com.frogobox.ads.ui.compose.FrogoNativeAdCompose`

Declarative Compose wrapper around Next-Gen `NativeAdView` and `MediaView` providing full Material 3 styling and attribution badge compliance:

### Ready-to-Use Lifecycle Managed Native Ad:
```kotlin
FrogoNativeAdCompose(
    adUnitId = "ca-app-pub-3940256099942544/2247696110",
    modifier = Modifier.fillMaxWidth().padding(16.dp)
)
```

### Fully Custom Component Layout:
```kotlin
FrogoNativeAdView(
    nativeAd = loadedNativeAd,
    modifier = Modifier.fillMaxWidth().cardStyle()
) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            FrogoNativeAdIconView(modifier = Modifier.size(48.dp))
            Column {
                FrogoNativeAdHeadlineView {
                    Text(text = it, style = MaterialTheme.typography.titleMedium)
                }
                FrogoNativeAdAttribution() // Official "Ad" badge
            }
        }

        FrogoNativeAdMediaView(
            modifier = Modifier.fillMaxWidth().height(180.dp)
        )

        FrogoNativeAdBodyView {
            Text(text = it, style = MaterialTheme.typography.bodyMedium)
        }

        FrogoNativeAdButton(
            text = "Install Now",
            modifier = Modifier.fillMaxWidth()
        )
    }
}
```
