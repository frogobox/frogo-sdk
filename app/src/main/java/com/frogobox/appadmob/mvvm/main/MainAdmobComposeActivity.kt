package com.frogobox.appadmob.mvvm.main

import android.app.Activity
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.frogobox.BuildConfig
import com.frogobox.FrogoApp
import com.frogobox.R
import com.frogobox.ads.callback.FrogoAdInterstitialCallback
import com.frogobox.ads.callback.FrogoAdmobAppOpenAdCallback
import com.frogobox.ads.callback.FrogoAdmobInterstitialCallback
import com.frogobox.ads.callback.FrogoAdmobRewardedCallback
import com.frogobox.ads.core.IFrogoAdConsent
import com.frogobox.ads.ui.compose.FrogoAdComposeActivity
import com.frogobox.appadmob.util.AdHelper
import com.frogobox.composeui.template.appbar.FrogoTopAppBar
import com.frogobox.composeui.template.scaffold.FrogoScaffold
import com.frogobox.composeui.widget.FrogoSpacerSmallHeight
import com.frogobox.composeui.widget.FrogoSpacerMediumHeight
import com.frogobox.sdk.ext.showLogDebug
import com.frogobox.sdk.ext.showToast
import com.google.android.libraries.ads.mobile.sdk.banner.AdSize
import com.google.android.libraries.ads.mobile.sdk.banner.AdView
import com.google.android.libraries.ads.mobile.sdk.rewarded.RewardItem
import com.google.android.ump.FormError

class MainAdmobComposeActivity : FrogoAdComposeActivity(),
    FrogoAdmobInterstitialCallback, FrogoAdmobRewardedCallback, FrogoAdmobAppOpenAdCallback,
    FrogoAdInterstitialCallback {

    private val adStatus = mutableStateOf("Ready to request ads")
    private val isAdLoading = mutableStateOf(false)

    companion object {
        private const val HTTP_TIMEOUT_MILLIS = 3000
    }

    override fun setupMonetized() {
        super.setupMonetized()
        setupUnityAdApp(BuildConfig.DEBUG, getString(R.string.unity_ad_game_id))
    }

    override fun onCreateExt(savedInstanceState: Bundle?) {
        super.onCreateExt(savedInstanceState)

        showAdConsent(object : IFrogoAdConsent {
            override fun activity(): Activity = this@MainAdmobComposeActivity
            override fun isDebug(): Boolean = BuildConfig.DEBUG
            override fun isUnderAgeAd(): Boolean = false
            override fun onNotUsingAdConsent() {
                // Done consent
            }
            override fun onConsentSuccess() {
                // Done consent
            }
            override fun onConsentError(formError: FormError) {
                showLogDebug("FrogoAdConsent ${formError.message}")
            }
        })
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun SetupCompose() {
        MaterialTheme(
            colorScheme = darkColorScheme(
                primary = Color(0xFFB39DDB),
                onPrimary = Color.Black,
                secondary = Color(0xFF03DAC6),
                background = Color(0xFF121212),
                surface = Color(0xFF1E1E1E),
                onBackground = Color.White,
                onSurface = Color.White
            )
        ) {
            FrogoScaffold(
                topBar = {
                    FrogoTopAppBar(
                        title = "Admob Compose",
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color(0xFF1E1E1E),
                            titleContentColor = Color.White
                        )
                    )
                }
            ) { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color(0xFF121212), Color(0xFF1F1A30))
                            )
                        )
                        .padding(paddingValues)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        // Status Card
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFF2D264D)
                            ),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Ad Status",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFFB39DDB)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = adStatus.value,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    textAlign = TextAlign.Center
                                )
                                if (isAdLoading.value) {
                                    Spacer(modifier = Modifier.height(12.dp))
                                    CircularProgressIndicator(
                                        color = Color(0xFF03DAC6),
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
                        }

                        // Hybrid Google Admob X Unity Ads Section
                        AdSectionCard(title = "Google Admob X Unity Ads") {
                            // Admob >> Unity Interstitial
                            HybridAdButton(
                                text = "Admob >> Unity Interstitial",
                                onClick = {
                                    showAdmobXUnityAdInterstitial(
                                        "",
                                        getString(R.string.unity_ad_interstitial),
                                        this@MainAdmobComposeActivity
                                    )
                                }
                            )
                            FrogoSpacerSmallHeight()

                            // Admob >> Unity Interstitial Timeout
                            HybridAdButton(
                                text = "Admob >> Unity Interstitial Timeout",
                                onClick = {
                                    showAdmobXUnityAdInterstitial(
                                        "",
                                        getString(R.string.unity_ad_interstitial),
                                        HTTP_TIMEOUT_MILLIS,
                                        this@MainAdmobComposeActivity
                                    )
                                }
                            )
                            FrogoSpacerSmallHeight()

                            // Admob >> Unity Interstitial Failed Wrong
                            HybridAdButton(
                                text = "Admob >> Unity Interstitial Failed Wrong",
                                onClick = {
                                    showAdmobXUnityAdInterstitial(
                                        "",
                                        "Wrong",
                                        this@MainAdmobComposeActivity
                                    )
                                }
                            )
                            FrogoSpacerSmallHeight()

                            // Admob >> Unity Interstitial Failed Empty
                            HybridAdButton(
                                text = "Admob >> Unity Interstitial Failed Empty",
                                onClick = {
                                    showAdmobXUnityAdInterstitial(
                                        "",
                                        "",
                                        this@MainAdmobComposeActivity
                                    )
                                }
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            // Unity >> Admob Interstitial
                            HybridAdButton(
                                text = "Unity >> Admob Interstitial",
                                onClick = {
                                    showUnityXAdmobAdInterstitial(
                                        getString(R.string.admob_interstitial),
                                        "",
                                        this@MainAdmobComposeActivity
                                    )
                                }
                            )
                            FrogoSpacerSmallHeight()

                            // Unity >> Admob Interstitial Timeout
                            HybridAdButton(
                                text = "Unity >> Admob Interstitial Timeout",
                                onClick = {
                                    showUnityXAdmobAdInterstitial(
                                        getString(R.string.admob_interstitial),
                                        "",
                                        HTTP_TIMEOUT_MILLIS,
                                        this@MainAdmobComposeActivity
                                    )
                                }
                            )
                            FrogoSpacerSmallHeight()

                            // Unity >> Admob Interstitial Failed Wrong
                            HybridAdButton(
                                text = "Unity >> Admob Interstitial Failed Wrong",
                                onClick = {
                                    showUnityXAdmobAdInterstitial(
                                        "Wrong",
                                        "",
                                        this@MainAdmobComposeActivity
                                    )
                                }
                            )
                            FrogoSpacerSmallHeight()

                            // Unity >> Admob Interstitial Failed Empty
                            HybridAdButton(
                                text = "Unity >> Admob Interstitial Failed Empty",
                                onClick = {
                                    showUnityXAdmobAdInterstitial(
                                        "",
                                        "",
                                        this@MainAdmobComposeActivity
                                    )
                                }
                            )
                        }

                        FrogoSpacerMediumHeight()

                        // Interstitial Section
                        AdSectionCard(title = "Interstitial Ads") {
                            Button(
                                onClick = {
                                    showAdInterstitial(getString(R.string.admob_interstitial), callback =this@MainAdmobComposeActivity)
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Show Interstitial Ad")
                            }
                            FrogoSpacerSmallHeight()
                            Button(
                                onClick = {
                                    showAdInterstitial(getString(R.string.admob_interstitial), HTTP_TIMEOUT_MILLIS, callback =this@MainAdmobComposeActivity)
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Show Interstitial (Timeout)")
                            }
                        }

                        FrogoSpacerMediumHeight()

                        // Rewarded Section
                        AdSectionCard(title = "Rewarded Ads") {
                            Button(
                                onClick = {
                                    showAdRewarded(getString(R.string.admob_rewarded), callback = this@MainAdmobComposeActivity)
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Show Rewarded Ad")
                            }
                            FrogoSpacerSmallHeight()
                            Button(
                                onClick = {
                                    showAdRewardedInterstitial(getString(R.string.admob_rewarded_interstitial), callback =this@MainAdmobComposeActivity)
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Show Rewarded Interstitial")
                            }
                        }

                        FrogoSpacerMediumHeight()

                        // App Open Section
                        AdSectionCard(title = "App Open Ad") {
                            Button(
                                onClick = {
                                    setupAppOpenAd()
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Show App Open Ad")
                            }
                        }

                        FrogoSpacerMediumHeight()

                        // Banner Section
                        AdSectionCard(title = "Banner Ad") {
                            Text(
                                text = "Banner will be shown below in real-time",
                                fontSize = 12.sp,
                                color = Color.Gray,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .background(Color.Black, RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                AndroidView(
                                    modifier = Modifier.fillMaxSize(),
                                    factory = { context ->
                                        AdView(context).apply {
                                            showAdBanner(this)
                                        }
                                    }
                                )
                            }
                        }

                        // Extra space at bottom to avoid overlapping with anything
                        Spacer(modifier = Modifier.height(40.dp))
                    }
                }
            }
        }
    }

    @Composable
    private fun HybridAdButton(
        text: String,
        onClick: () -> Unit
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFB39DDB),
                contentColor = Color.Black
            )
        ) {
            Text(
                text = text,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        }
    }

    @Composable
    private fun AdSectionCard(
        title: String,
        content: @Composable ColumnScope.() -> Unit
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF1E1E1E)
            ),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF9E82F0),
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                content()
            }
        }
    }

    private fun setupAppOpenAd() {
        val application = application as? FrogoApp
        if (application != null) {
            application.showAdIfAvailable(
                this,
                AdHelper.getAdOpenAppUnitId(this),
                this
            )
        } else {
            adStatus.value = "Failed to load App Open Ad: Application class mismatch"
        }
    }

    // Callbacks
    override fun onShowAdRequestProgress(tag: String, message: String) {
        isAdLoading.value = true
        adStatus.value = "Loading ad... $message"
    }

    override fun onHideAdRequestProgress(tag: String, message: String) {
        isAdLoading.value = false
        adStatus.value = "Ad request finished. $message"
    }

    override fun onAdDismissed(tag: String, message: String) {
        adStatus.value = "Ad dismissed: $message"
        showToast(message)
    }

    override fun onAdFailed(tag: String, errorMessage: String) {
        adStatus.value = "Ad failed: $errorMessage"
        showToast(errorMessage)
    }

    override fun onAdLoaded(tag: String, message: String) {
        adStatus.value = "Ad loaded: $message"
    }

    override fun onAdShowed(tag: String, message: String) {
        adStatus.value = "Ad showed: $message"
    }

    override fun onUserEarnedReward(tag: String, rewardItem: RewardItem) {
        adStatus.value = "Earned Reward: ${rewardItem.amount} ${rewardItem.type}"
        showToast("Reward earned: ${rewardItem.amount}")
    }

    override fun onClicked(tag: String, message: String) {
        adStatus.value = "Ad clicked: $message"
        showToast(message)
    }
}
