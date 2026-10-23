package com.example.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.frogobox.ads.callback.FrogoAdInterstitialCallback
import com.frogobox.ads.ui.compose.FrogoAdComposeActivity
import com.frogobox.ads.ui.compose.FrogoBannerAdCompose

class MonetizedScreenActivity : FrogoAdComposeActivity() {

    @Composable
    override fun SetupCompose() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("Frogo SDK Monetization Showcase")

            Spacer(modifier = Modifier.height(16.dp))

            // 1. Hybrid Interstitial Ad (AdMob with Unity Ads Fallback)
            Button(
                onClick = {
                    showAdmobXUnityAdInterstitial(
                        admobInterstitialId = "ca-app-pub-3940256099942544/1033173712",
                        unityInterstitialId = "interstitial_unit",
                        callback = object : FrogoAdInterstitialCallback {
                            override fun onAdLoaded(tag: String, message: String) {}
                            override fun onAdFailed(tag: String, errorMessage: String) {}
                            override fun onAdDismissed(tag: String, message: String) {}
                            override fun onAdShowed(tag: String, message: String) {}
                        }
                    )
                }
            ) {
                Text("Show Interstitial Ad")
            }

            Spacer(modifier = Modifier.weight(1f))

            // 2. Next-Gen Declarative Banner Ad with auto-lifecycle cleanup
            FrogoBannerAdCompose(
                adUnitId = "ca-app-pub-3940256099942544/6300978111",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
