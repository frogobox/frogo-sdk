---
name: frogo-sdk
description: >-
  Comprehensive guide and runbook for integrating and developing with the Frogo SDK Android suite (v3.0.8).
  Covers Jetpack Compose UI (Material 3), Google Mobile Ads SDK (Next-Gen 1.4.0) & Unity Ads (4.20.0),
  Advanced RecyclerView helpers, core Android utilities, UDF/MVI State ViewModels, and edge-to-edge Compose activities.
  Use this skill whenever building Android features, generating screens, integrating ads, or working with Frogo SDK libraries.
metadata:
  author: Frogobox (Muhammad Faisal Amir)
  version: "3.0.8"
  keywords:
  - Frogo SDK
  - Android SDK
  - Jetpack Compose UI
  - Android UI Components
  - AdMob Next-Gen Integration
  - Unity Ads
  - RecyclerView Adapter
  - Android Development Tools
  - Kotlin Android Library
  - Material Design 3
  - Android Monetization
  - UDF MVI State ViewModel
---

# Frogo SDK Integration & Development Specialist

This skill provides complete instructions for integrating, developing, and extending applications using the **Frogo SDK** suite of Android libraries (v3.0.8).

**Repository:** `frogobox/frogo-sdk`  
**Latest Stable Version:** `3.0.8`  
**Core Tech Stack:**
- **AGP:** `9.4.0`
- **Kotlin:** `2.4.20`
- **Compile / Target SDK:** `37` (Min SDK: `24`)
- **Compose BOM:** `2026.09.00` (Material 3)
- **Google Mobile Ads SDK (Next-Gen):** `1.4.0` (`com.google.android.libraries.ads.mobile.sdk:ads-mobile-sdk:1.4.0`)
- **Unity Ads:** `4.20.0`
- **Coil:** `3.6.2` (Coil 3 multiplatform/compose: `io.coil-kt.coil3:coil-compose:3.6.2`)
- **Glide Compose:** `1.0.0-beta10`

---

## 🏛️ Architecture Overview

Frogo SDK is organized into 7 modular Android/Kotlin libraries:

| Module | Package / Namespace | Artifact Coordinates (JitPack) | Purpose |
| :--- | :--- | :--- | :--- |
| `frogo-core` | `com.frogobox.coreutil` | Pure Kotlin (no Android dependency) | Cross-platform utilities, string, date, math helpers |
| `frogo-core-android` | `com.frogobox.sdk` | `com.github.frogobox.frogo-sdk:frogo-core-android:3.0.8` | Base Activity/Fragment/BottomSheet (ViewBinding), UDF `FrogoStateViewModel`, 16+ extension files |
| `frogo-compose-android` | `com.frogobox.compose` | `com.github.frogobox.frogo-sdk:frogo-compose-android:3.0.8` | `FrogoComposeActivity` (Edge-to-Edge, system UI), `FrogoComposeStateViewModel` (UDF/MVI) |
| `frogo-compose-ui` | `com.frogobox.composeui` | `com.github.frogobox.frogo-sdk:frogo-compose-ui:3.0.8` | 70+ ready-to-use Compose widgets, templates, Coil 3 & Glide lists, animations, fireworks canvas, loading indicators |
| `frogo-ui-base` | `com.frogobox.ui` | `com.github.frogobox.frogo-sdk:frogo-ui-base:3.0.8` | XML-based UI utilities and view helpers |
| `frogo-ui-recyclerview` | `com.frogobox.recycler` | `com.github.frogobox.frogo-sdk:frogo-ui-recyclerview:3.0.8` | High-performance RecyclerView with builder pattern, shimmer loading, progress states |
| `frogo-ext-ads` | `com.frogobox.ads` | `com.github.frogobox.frogo-sdk:frogo-ext-ads:3.0.8` | Google Mobile Ads SDK (Next-Gen) & Unity Ads delegates, App Open Ads, hybrid fallback, Compose ad activities |

---

## 🚀 Step 1: Repository Setup

In `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}
```

---

## 📦 Step 2: Dependency Setup

### Recommended: Gradle Version Catalog (`libs.versions.toml`)

```toml
[versions]
frogoSdk = "3.0.8"
composeBom = "2026.09.00"
googleAdmob = "1.4.0"
unityAd = "4.20.0"
coil = "3.6.2"

[libraries]
# Frogo SDK Modular Suite
frogo-compose-ui = { group = "com.github.frogobox.frogo-sdk", name = "frogo-compose-ui", version.ref = "frogoSdk" }
frogo-compose-android = { group = "com.github.frogobox.frogo-sdk", name = "frogo-compose-android", version.ref = "frogoSdk" }
frogo-core-android = { group = "com.github.frogobox.frogo-sdk", name = "frogo-core-android", version.ref = "frogoSdk" }
frogo-ext-ads = { group = "com.github.frogobox.frogo-sdk", name = "frogo-ext-ads", version.ref = "frogoSdk" }
frogo-ui-recyclerview = { group = "com.github.frogobox.frogo-sdk", name = "frogo-ui-recyclerview", version.ref = "frogoSdk" }

# Compose BOM & Coil 3
androidx-compose-bom = { group = "androidx.compose", name = "compose-bom", version.ref = "composeBom" }
coil-compose = { group = "io.coil-kt.coil3", name = "coil-compose", version.ref = "coil" }
coil-network-okhttp = { group = "io.coil-kt.coil3", name = "coil-network-okhttp", version.ref = "coil" }
```

### Module `build.gradle.kts` Example:

```kotlin
dependencies {
    // Jetpack Compose UI Kit & Base
    implementation(libs.frogo.compose.ui)
    implementation(libs.frogo.compose.android)

    // Core Android & Extensions
    implementation(libs.frogo.core.android)

    // Next-Gen Ads & Monetization
    implementation(libs.frogo.ext.ads)

    // Advanced RecyclerView
    implementation(libs.frogo.ui.recyclerview)

    // Coil 3 Image Loading
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
}
```

> [!WARNING]
> **AdMob Dependency Conflict Prevention:** `frogo-ext-ads` uses the official Google Mobile Ads SDK Next-Gen (`com.google.android.libraries.ads.mobile.sdk:ads-mobile-sdk:1.4.0`). Ensure legacy `com.google.android.gms:play-services-ads` is excluded to prevent runtime duplicate class errors:
> ```kotlin
> configurations.configureEach {
>     exclude(group = "com.google.android.gms", module = "play-services-ads")
>     exclude(group = "com.google.android.gms", module = "play-services-ads-lite")
> }
> ```

---

## 🛠️ Module Runbooks & Code Recipes

### 1. `frogo-compose-android` — Edge-to-Edge Activity & UDF ViewModel

Detailed guide: [Core Android Reference](references/core-android-reference.md)

Extend `FrogoComposeActivity` which automatically configures modern edge-to-edge via `androidx.activity.enableEdgeToEdge`, window insets, and predictive back callback.

```kotlin
package com.example.app.ui

import android.os.Bundle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.activity.viewModels
import com.frogobox.compose.view.FrogoComposeActivity
import com.frogobox.compose.viewmodel.FrogoComposeStateViewModel

// 1. Define State & Effect
data class MainUiState(val isLoading: Boolean = false, val items: List<String> = emptyList())
sealed interface MainUiEffect {
    data class ShowToast(val message: String) : MainUiEffect
}

// 2. ViewModel using FrogoComposeStateViewModel
class MainViewModel : FrogoComposeStateViewModel<MainUiState, MainUiEffect>(MainUiState()) {
    fun loadData() {
        updateState { copy(isLoading = true) }
        // Fetch or process data
        updateState { copy(isLoading = false, items = listOf("Alpha", "Beta", "Gamma")) }
        emitEffect(MainUiEffect.ShowToast("Items loaded successfully!"))
    }
}

// 3. Activity extending FrogoComposeActivity
class MainActivity : FrogoComposeActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateExt(savedInstanceState: Bundle?) {
        super.onCreateExt(savedInstanceState)
        // Edge-to-edge and system insets are already configured automatically!
        viewModel.loadData()
    }

    @Composable
    override fun SetupCompose() {
        val state by viewModel.uiState.collectAsStateWithLifecycle()
        // Compose Screen UI
        Text(text = "Item count: ${state.items.size}")
    }
}
```

---

### 2. `frogo-compose-ui` — 70+ Material 3 Widgets & Templates

Detailed catalog: [Compose UI Reference](references/compose-ui-reference.md)

#### A. Scaffold + TopAppBar + LazyColumn with Empty State:
```kotlin
import androidx.compose.runtime.Composable
import com.frogobox.composeui.template.scaffold.FrogoScaffold
import com.frogobox.composeui.template.appbar.FrogoTopAppBar
import com.frogobox.composeui.list.basic.FrogoLazyColumn
import com.frogobox.composeui.list.basic.FrogoListItem
import com.frogobox.composeui.template.empty.FrogoEmptyState

@Composable
fun ProductListScreen(products: List<String>, onProductClick: (String) -> Unit) {
    FrogoScaffold(
        topBar = {
            FrogoTopAppBar(title = "Products")
        }
    ) { paddingValues ->
        FrogoLazyColumn(
            data = products,
            contentPadding = paddingValues,
            emptyContent = {
                FrogoEmptyState(title = "No Products Found", description = "Add a product to get started.")
            }
        ) { index, product ->
            FrogoListItem(
                headlineText = product,
                supportingText = "SKU #$index",
                onClick = { onProductClick(product) }
            )
        }
    }
}
```

#### B. Coil 3 Image Lists:
Import from `com.frogobox.composeui.list.coil.*` and use `FrogoCoilLazyColumn` with Coil 3 (`coil3.compose.AsyncImage` internally):
```kotlin
import com.frogobox.composeui.list.coil.FrogoCoilLazyColumn
import com.frogobox.composeui.list.coil.FrogoCoilListItem

FrogoCoilLazyColumn(data = photoList) { index, photo ->
    FrogoCoilListItem(
        imageUrl = photo.url,
        headlineText = photo.title,
        supportingText = photo.subtitle,
        onClick = { /* Handle click */ }
    )
}
```

#### C. Fireworks Canvas & Custom Loading Indicators:
```kotlin
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.frogobox.composeui.fireworks.FrogoFireworksCompose
import com.frogobox.composeui.fireworks.rememberFrogoFireworksStateCompose
import com.frogobox.composeui.ext.frogoClickWithFireworksCompose
import com.frogobox.composeui.loadingindicator.FrogoLoadingIndicatorCompose

@Composable
fun InteractiveDemo() {
    val fireworksState = rememberFrogoFireworksStateCompose()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(24.dp)) {
            Button(
                onClick = {},
                modifier = Modifier.frogoClickWithFireworksCompose(fireworksState) {
                    // Tap explodes fireworks at coordinate
                }
            ) {
                Text("Tap for Celebration Fireworks!")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Canvas-based Pacman / BallPulse Loading Indicator
            FrogoLoadingIndicatorCompose(
                indicatorName = "Pacman",
                color = Color(0xFFFFCC00),
                size = 48.dp
            )
        }

        // Render fireworks overlay canvas
        FrogoFireworksCompose(state = fireworksState, modifier = Modifier.fillMaxSize())
    }
}
```

---

### 3. `frogo-ext-ads` — Next-Gen Google Mobile Ads & Unity Ads

Detailed delegates API: [Ads Reference](references/ads-reference.md)

#### AndroidManifest.xml Prerequisite:
```xml
<application ...>
    <meta-data
        android:name="com.google.android.gms.ads.APPLICATION_ID"
        android:value="ca-app-pub-3940256099942544~3347511713"/>
</application>
```

#### A. Application Class:
```kotlin
package com.example.app

import com.frogobox.ads.FrogoAdmobApplication

class App : FrogoAdmobApplication() {
    override fun onCreate() {
        super.onCreate()
        // Initializes Google Mobile Ads SDK Next-Gen in background thread automatically
    }

    override fun getAdOpenAppUnitId(context: android.content.Context?): String {
        return "ca-app-pub-3940256099942544/9257395921" // App Open Ad Unit ID
    }
}
```

#### B. Jetpack Compose Ad Activities:
Extend `FrogoAdmobComposeActivity` (AdMob only), `FrogoUnityAdComposeActivity` (Unity only), or `FrogoAdComposeActivity` (Hybrid Mediation with both AdMob and Unity Ads):

```kotlin
package com.example.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.frogobox.ads.ui.compose.FrogoAdComposeActivity
import com.frogobox.ads.callback.FrogoAdInterstitialCallback
import com.google.android.libraries.ads.mobile.sdk.banner.AdSize
import com.google.android.libraries.ads.mobile.sdk.banner.AdView

class AdScreenActivity : FrogoAdComposeActivity() {

    @Composable
    override fun SetupCompose() {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text("Monetization with Frogo SDK")

            // 1. Show Hybrid Interstitial with Fallback (AdMob -> Unity Ads)
            Button(onClick = {
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
            }) {
                Text("Show Interstitial Ad (Hybrid Fallback)")
            }

            // 2. Next-Gen Banner Ad via AndroidView
            AndroidView(
                modifier = Modifier.fillMaxWidth().height(50.dp),
                factory = { context ->
                    AdView(context).apply {
                        setAdSize(AdSize.BANNER)
                        adUnitId = "ca-app-pub-3940256099942544/6300978111"
                        showAdBanner(this)
                    }
                }
            )
        }
    }
}
```

---

### 4. `frogo-ui-recyclerview` — High-Performance Recycler Kit

Detailed builder API: [RecyclerView Reference](references/recyclerview-reference.md)

```kotlin
import com.frogobox.recycler.core.IFrogoViewHolder
import com.frogobox.recycler.widget.FrogoRecyclerView

binding.frogoRecyclerView
    .injector<NewsItem>()
    .addData(newsList)
    .addCallback(object : IFrogoViewHolder<NewsItem> {
        override fun setupInitComponent(view: View, data: NewsItem, position: Int) {
            val itemBinding = ItemNewsBinding.bind(view)
            itemBinding.tvTitle.text = data.title
        }
    })
    .createLayoutLinearVertical(false)
    .build()
```

---

## 🛡️ Critical Agent Guidelines & Rules

### 1. No Warning Suppression Policy
- **STRICTLY FORBIDDEN**: Never write `@Suppress("DEPRECATION")` or `@SuppressWarnings("deprecation")`.
- **ALWAYS MIGRATE**: When an API is deprecated, inspect the recommended replacement and refactor to modern equivalents:
  - Migrated Google Mobile Ads: Use `com.google.android.libraries.ads.mobile.sdk.*` (Next-Gen).
  - Migrated Image Loading: Use Coil 3 (`coil3.compose.AsyncImage`).
  - Migrated Edge-to-Edge: Use `androidx.activity.enableEdgeToEdge`.

### 2. Package & Import Guardrails for Other Agents
| Component | CORRECT Package Import | INCORRECT / BANNED Import |
| :--- | :--- | :--- |
| AdMob `AdView` / `AdSize` | `com.google.android.libraries.ads.mobile.sdk.banner.*` | `com.google.android.gms.ads.*` (Excluded / Incompatible) |
| AdMob `MobileAds` | `com.google.android.libraries.ads.mobile.sdk.MobileAds` | `com.google.android.gms.ads.MobileAds` |
| Coil 3 Composable | `coil3.compose.AsyncImage` | `coil.compose.AsyncImage` (Coil 2 package) |
| Frogo Compose Activity | `com.frogobox.compose.view.FrogoComposeActivity` | |
| Frogo Ad Activity | `com.frogobox.ads.ui.compose.FrogoAdComposeActivity` | |
| Frogo State ViewModel | `com.frogobox.compose.viewmodel.FrogoComposeStateViewModel` | |

### 3. Verification Commands
When verifying changes in this project, execute:
- Unit Tests: `./gradlew testDebugUnitTest`
- Assemble Debug: `./gradlew assembleDebug`
- Lint: `./gradlew lintDebug`
