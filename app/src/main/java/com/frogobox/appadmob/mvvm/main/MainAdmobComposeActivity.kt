package com.frogobox.appadmob.mvvm.main

import android.app.Activity
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.frogobox.BuildConfig
import com.frogobox.R
import com.frogobox.ads.FrogoAdmobApplication
import com.frogobox.ads.callback.FrogoAdInterstitialCallback
import com.frogobox.ads.callback.FrogoAdmobAppOpenAdCallback
import com.frogobox.ads.callback.FrogoAdmobInterstitialCallback
import com.frogobox.ads.callback.FrogoAdmobRewardedCallback
import com.frogobox.ads.core.FrogoAppOpenAdManager
import com.frogobox.ads.core.IFrogoAdConsent
import com.frogobox.ads.core.IFrogoAppOpenAdOptOut
import com.frogobox.ads.ui.compose.FrogoAdComposeActivity
import com.frogobox.ads.ui.compose.FrogoBannerAdCompose
import com.frogobox.appadmob.util.AdHelper
import com.frogobox.composeui.template.appbar.FrogoTopAppBar
import com.frogobox.composeui.template.scaffold.FrogoScaffold
import com.frogobox.composeui.widget.FrogoSpacerMediumHeight
import com.frogobox.composeui.widget.FrogoSpacerSmallHeight
import com.frogobox.sdk.ext.showLogDebug
import com.frogobox.sdk.ext.showToast
import com.google.android.libraries.ads.mobile.sdk.rewarded.RewardItem
import com.google.android.ump.FormError

enum class AppOpenUiState(
    val label: String,
    val containerColor: Color,
    val contentColor: Color
) {
    NOT_LOADED("NOT LOADED", Color(0xFF2D264D), Color(0xFFB39DDB)),
    LOADING("LOADING", Color(0xFF311B92), Color(0xFFD1C4E9)),
    READY("READY", Color(0xFF1B3E2B), Color(0xFF81C784)),
    SHOWING("SHOWING", Color(0xFF004D40), Color(0xFF80CBC4)),
    FAILED("FAILED", Color(0xFF4A151B), Color(0xFFEF9A9A)),
    EXPIRED("EXPIRED", Color(0xFF422606), Color(0xFFFFB74D))
}

class MainAdmobComposeActivity : FrogoAdComposeActivity(),
    FrogoAdmobInterstitialCallback, FrogoAdmobRewardedCallback, FrogoAdmobAppOpenAdCallback,
    FrogoAdInterstitialCallback, IFrogoAppOpenAdOptOut {

    private val adStatus = mutableStateOf("Ready to request ads")
    private val isAdLoading = mutableStateOf(false)

    // App Open Ad reactive state
    private val appOpenUiState = mutableStateOf(AppOpenUiState.NOT_LOADED)
    private val isAppOpenLoading = mutableStateOf(false)
    private val appOpenLoadTime = mutableStateOf<Long?>(null)
    private val isOptOutSimulated = mutableStateOf(false)

    companion object {
        private const val HTTP_TIMEOUT_MILLIS = 3000
    }

    override fun isAppOpenAdOptedOut(): Boolean = isOptOutSimulated.value

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

    override fun onResume() {
        super.onResume()
        refreshAppOpenState()
    }

    private fun refreshAppOpenState() {
        val available = isAppOpenAdAvailable()
        val app = application as? FrogoAdmobApplication
        val loadTime = app?.getAppOpenAdLoadTime() ?: 0L
        appOpenLoadTime.value = if (loadTime > 0L) loadTime else null

        if (available) {
            appOpenUiState.value = AppOpenUiState.READY
        } else if (loadTime > 0L && (System.currentTimeMillis() - loadTime) >= 4 * 3600000L) {
            appOpenUiState.value = AppOpenUiState.EXPIRED
        } else if (!isAppOpenLoading.value) {
            appOpenUiState.value = AppOpenUiState.NOT_LOADED
        }
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
                                if (isAdLoading.value || isAppOpenLoading.value) {
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
                                    showAdInterstitial(getString(R.string.admob_interstitial), callback = this@MainAdmobComposeActivity)
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Show Interstitial Ad")
                            }
                            FrogoSpacerSmallHeight()
                            Button(
                                onClick = {
                                    showAdInterstitial(getString(R.string.admob_interstitial), HTTP_TIMEOUT_MILLIS, callback = this@MainAdmobComposeActivity)
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
                                    showAdRewardedInterstitial(getString(R.string.admob_rewarded_interstitial), callback = this@MainAdmobComposeActivity)
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Show Rewarded Interstitial")
                            }
                        }

                        FrogoSpacerMediumHeight()

                        // App Open Section - Modernized & Reactive (Angel UI Spec)
                        AppOpenAdComposeSection(
                            uiState = appOpenUiState.value,
                            isAdLoading = isAppOpenLoading.value,
                            isAdAvailable = isAppOpenAdAvailable(),
                            loadTimeMillis = appOpenLoadTime.value,
                            isOptOutSimulated = isOptOutSimulated.value,
                            onOptOutToggle = { isOptOutSimulated.value = it },
                            onPreloadClick = {
                                val adUnitId = AdHelper.getAdOpenAppUnitId(this@MainAdmobComposeActivity)
                                loadAppOpenAd(adUnitId, this@MainAdmobComposeActivity)
                            },
                            onShowAdClick = {
                                val adUnitId = AdHelper.getAdOpenAppUnitId(this@MainAdmobComposeActivity)
                                showAppOpenAd(adUnitId, this@MainAdmobComposeActivity)
                            },
                            onLoadAndShowClick = {
                                val adUnitId = AdHelper.getAdOpenAppUnitId(this@MainAdmobComposeActivity)
                                loadAndShowAppOpenAd(adUnitId, this@MainAdmobComposeActivity)
                            }
                        )

                        FrogoSpacerMediumHeight()

                        // Banner Section
                        AdSectionCard(title = "Banner Ad") {
                            Text(
                                text = "Rendered via FrogoBannerAdCompose with automatic lifecycle management",
                                fontSize = 12.sp,
                                color = Color.Gray,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            FrogoBannerAdCompose(
                                adUnitId = getString(R.string.admob_banner),
                                modifier = Modifier.fillMaxWidth()
                            )
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

    @Composable
    private fun AppOpenAdComposeSection(
        uiState: AppOpenUiState,
        isAdLoading: Boolean,
        isAdAvailable: Boolean,
        loadTimeMillis: Long?,
        isOptOutSimulated: Boolean,
        onOptOutToggle: (Boolean) -> Unit,
        onPreloadClick: () -> Unit,
        onShowAdClick: () -> Unit,
        onLoadAndShowClick: () -> Unit
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Header with Title and Reactive Status Chip
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "App Open Ad",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF9E82F0)
                    )

                    // Status Badge Chip
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = uiState.containerColor,
                        modifier = Modifier.heightIn(min = 28.dp)
                    ) {
                        Text(
                            text = uiState.label,
                            color = uiState.contentColor,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                }

                // Expiration and Cache Timestamp
                val validityText = remember(loadTimeMillis) {
                    if (loadTimeMillis == null || loadTimeMillis == 0L) {
                        "Cache: None"
                    } else {
                        val ageMinutes = (System.currentTimeMillis() - loadTimeMillis) / (60 * 1000)
                        "Cached: ${ageMinutes}m ago (Valid for 4h)"
                    }
                }
                Text(
                    text = validityText,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
                )

                // Progress Indicator
                if (isAdLoading) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(3.dp)
                            .padding(vertical = 2.dp),
                        color = Color(0xFF03DAC6),
                        trackColor = Color(0xFF2D264D)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                // Action CTAs (Touch Targets >= 48dp)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // 1. Preload CTA
                    Button(
                        onClick = onPreloadClick,
                        enabled = !isAdLoading && uiState != AppOpenUiState.SHOWING,
                        modifier = Modifier
                            .weight(1f)
                            .heightIn(min = 48.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2D264D),
                            contentColor = Color(0xFFB39DDB),
                            disabledContainerColor = Color(0xFF231F33),
                            disabledContentColor = Color.Gray
                        )
                    ) {
                        Text(text = "Preload", fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                    }

                    // 2. Show If Available CTA
                    Button(
                        onClick = onShowAdClick,
                        enabled = isAdAvailable && !isAdLoading && uiState != AppOpenUiState.SHOWING,
                        modifier = Modifier
                            .weight(1f)
                            .heightIn(min = 48.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFB39DDB),
                            contentColor = Color.Black,
                            disabledContainerColor = Color(0xFF333333),
                            disabledContentColor = Color.DarkGray
                        )
                    ) {
                        Text(text = "Show Ad", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // 3. Load & Show Full Flow CTA
                Button(
                    onClick = onLoadAndShowClick,
                    enabled = !isAdLoading && uiState != AppOpenUiState.SHOWING,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 48.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3700B3),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Load & Show Immediately", fontSize = 13.sp, fontWeight = FontWeight.Medium)
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Opt-Out / VIP Simulation Switch (AC-04 Showcase)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 48.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Simulate Opt-Out (VIP / Ad-Free)",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                        Text(
                            text = "Disables auto App Open Ad on foreground return",
                            fontSize = 11.sp,
                            color = Color.LightGray
                        )
                    }

                    Switch(
                        checked = isOptOutSimulated,
                        onCheckedChange = onOptOutToggle,
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color(0xFF03DAC6),
                            checkedTrackColor = Color(0xFF004D40),
                            uncheckedThumbColor = Color.Gray,
                            uncheckedTrackColor = Color(0xFF333333)
                        )
                    )
                }
            }
        }
    }

    // Callbacks
    override fun onShowAdRequestProgress(tag: String, message: String) {
        if (tag == FrogoAppOpenAdManager.LOG_TAG) {
            isAppOpenLoading.value = true
            appOpenUiState.value = AppOpenUiState.LOADING
            adStatus.value = "App Open Ad loading... $message"
        } else {
            isAdLoading.value = true
            adStatus.value = "Loading ad... $message"
        }
    }

    override fun onHideAdRequestProgress(tag: String, message: String) {
        if (tag == FrogoAppOpenAdManager.LOG_TAG) {
            isAppOpenLoading.value = false
            adStatus.value = "App Open Ad request complete: $message"
        } else {
            isAdLoading.value = false
            adStatus.value = "Ad request finished. $message"
        }
    }

    override fun onAdDismissed(tag: String, message: String) {
        if (tag == FrogoAppOpenAdManager.LOG_TAG) {
            refreshAppOpenState()
            adStatus.value = "App Open Ad dismissed: $message"
        } else {
            adStatus.value = "Ad dismissed: $message"
        }
        showToast(message)
    }

    override fun onAdFailed(tag: String, errorMessage: String) {
        if (tag == FrogoAppOpenAdManager.LOG_TAG) {
            isAppOpenLoading.value = false
            appOpenUiState.value = AppOpenUiState.FAILED
            adStatus.value = "App Open Ad failed: $errorMessage"
        } else {
            adStatus.value = "Ad failed: $errorMessage"
        }
        showToast(errorMessage)
    }

    override fun onAdLoaded(tag: String, message: String) {
        if (tag == FrogoAppOpenAdManager.LOG_TAG) {
            isAppOpenLoading.value = false
            appOpenUiState.value = AppOpenUiState.READY
            val app = application as? FrogoAdmobApplication
            appOpenLoadTime.value = app?.getAppOpenAdLoadTime() ?: System.currentTimeMillis()
            adStatus.value = "App Open Ad loaded: $message"
        } else {
            adStatus.value = "Ad loaded: $message"
        }
    }

    override fun onAdShowed(tag: String, message: String) {
        if (tag == FrogoAppOpenAdManager.LOG_TAG) {
            appOpenUiState.value = AppOpenUiState.SHOWING
            adStatus.value = "App Open Ad showed: $message"
        } else {
            adStatus.value = "Ad showed: $message"
        }
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
