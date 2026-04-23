---
name: frogo-sdk
description: Integrates and uses the Frogo SDK suite of Android libraries including Jetpack Compose UI components, AdMob ad integration, RecyclerView helpers, and core Android utilities in Android projects.
metadata:
  author: Frogobox (Muhammad Faisal Amir)
  keywords:
  - Frogo SDK
  - Android SDK
  - Jetpack Compose UI
  - Android UI Components
  - AdMob Integration
  - RecyclerView Adapter
  - Android Development Tools
  - Kotlin Android Library
  - Material Design 3
  - Android Monetization
---

# Frogo SDK Integration Specialist

This skill provides comprehensive instructions for integrating and using the **Frogo SDK** suite of Android libraries. Frogo SDK is a multi-module toolkit for accelerating Android (and Desktop) development.

**Repository:** `frogobox/frogo-sdk`  
**Latest Version:** `2.3.7`  
**Minimum Requirements:** AGP 9.2.0, Kotlin 2.3.20, Compose BOM 2026.04.01

## Architecture Overview

Frogo SDK is organized into the following modules:

| Module | Package | Purpose |
| :--- | :--- | :--- |
| `frogo-core` | `com.frogobox.coreutil` | Pure Kotlin utilities (no Android dependency) |
| `frogo-core-android` | `com.frogobox.sdk` | Android base classes, extensions, and lifecycle helpers |
| `frogo-compose-android` | `com.frogobox.compose` | Compose-specific base classes |
| `frogo-compose-ui` | `com.frogobox.composeui` | 60+ reusable Compose widgets & templates |
| `frogo-ui-base` | `com.frogobox.ui` | XML-based UI utilities |
| `frogo-ui-recyclerview` | `com.frogobox.recycler` | Advanced RecyclerView with shimmer & progress |
| `frogo-ext-ads` | `com.frogobox.ads` | AdMob & Unity Ads integration |

---

## Step 1: Add JitPack Repository

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

## Step 2: Add Dependencies

### Option A: Full SDK (all modules)
```kotlin
dependencies {
    implementation("com.github.frogobox:frogo-sdk:2.3.7")
}
```

### Option B: Individual modules (recommended)
```kotlin
dependencies {
    // Jetpack Compose UI Kit (Material Design 3)
    implementation("com.github.frogobox.frogo-sdk:frogo-compose-ui:2.3.7")

    // Core Android utilities, base classes, extensions
    implementation("com.github.frogobox.frogo-sdk:frogo-core-android:2.3.7")

    // AdMob & Unity Ads integration
    implementation("com.github.frogobox.frogo-sdk:frogo-ext-ads:2.3.7")

    // Advanced RecyclerView with shimmer loading
    implementation("com.github.frogobox.frogo-sdk:frogo-ui-recyclerview:2.3.7")

    // Compose-specific base classes
    implementation("com.github.frogobox.frogo-sdk:frogo-compose-android:2.3.7")
}
```

### Option C: Version Catalog (`libs.versions.toml`)
```toml
[versions]
frogoSdk = "2.3.7"

[libraries]
frogo-sdk = { group = "com.github.frogobox", name = "frogo-sdk", version.ref = "frogoSdk" }
frogo-compose-ui = { group = "com.github.frogobox.frogo-sdk", name = "frogo-compose-ui", version.ref = "frogoSdk" }
frogo-core-android = { group = "com.github.frogobox.frogo-sdk", name = "frogo-core-android", version.ref = "frogoSdk" }
frogo-ext-ads = { group = "com.github.frogobox.frogo-sdk", name = "frogo-ext-ads", version.ref = "frogoSdk" }
frogo-ui-recyclerview = { group = "com.github.frogobox.frogo-sdk", name = "frogo-ui-recyclerview", version.ref = "frogoSdk" }
```

---

## Module Usage Guides

### frogo-compose-ui — Jetpack Compose UI Kit

The `frogo-compose-ui` module provides **60+ ready-to-use** Compose components following Material Design 3. See [Compose UI Reference](references/compose-ui-reference.md) for the full API catalog.

#### Available Widget Categories:

**Base Widgets (22):** FrogoButton, FrogoOutlinedButton, FrogoTextField, FrogoOutlinedTextField, FrogoCard, FrogoElevatedCard, FrogoCheckbox, FrogoRadioButton, FrogoSwitch, FrogoChip, FrogoFilterChip, FrogoBadge, FrogoAvatar, FrogoDivider, FrogoSpacer, FrogoIcon, FrogoIconButton, FrogoImage, FrogoFloatingActionButton, FrogoCircularProgress, FrogoLinearProgress, FrogoSearchBar.

**Templates (22):** App Bars (6), Bottom Sheets (3), Dialogs (5), Navigation (3), Scaffolds (2), Shimmer (2), Snackbar (1), Tabs (2), Empty State (1).

**List Components (15):** Basic lists (5), Coil image-loaded lists (5), Glide image-loaded lists (5).

#### Quick Example — Scaffold with TopAppBar and List:

```kotlin
import com.frogobox.composeui.template.scaffold.FrogoScaffold
import com.frogobox.composeui.template.appbar.FrogoTopAppBar
import com.frogobox.composeui.list.basic.FrogoLazyColumn

@Composable
fun MyScreen(items: List<String>) {
    FrogoScaffold(
        topBar = { FrogoTopAppBar(title = "My App") }
    ) { paddingValues ->
        FrogoLazyColumn(
            data = items,
            contentPadding = paddingValues,
            emptyContent = { FrogoEmptyState(title = "No Items") }
        ) { index, item ->
            FrogoListItem(
                headlineText = item,
                supportingText = "Item #$index"
            )
        }
    }
}
```

#### Quick Example — Dialog:

```kotlin
import com.frogobox.composeui.template.dialog.FrogoAlertDialog

FrogoAlertDialog(
    onDismissRequest = { /* dismiss */ },
    onConfirmation = { /* confirm */ },
    dialogTitle = "Delete Item",
    dialogText = "Are you sure you want to delete this item?",
    confirmButtonText = "Delete",
    dismissButtonText = "Cancel"
)
```

#### Quick Example — Bottom Sheet:

```kotlin
import com.frogobox.composeui.template.bottomsheet.FrogoBottomSheet

FrogoBottomSheet(
    onDismissRequest = { showSheet = false }
) {
    Text("Bottom Sheet Content")
}
```

#### Quick Example — Image List with Coil:

```kotlin
import com.frogobox.composeui.list.coil.FrogoCoilLazyColumn
import com.frogobox.composeui.list.coil.FrogoCoilListItem

FrogoCoilLazyColumn(data = imageItems) { index, item ->
    FrogoCoilListItem(
        imageUrl = item.imageUrl,
        headlineText = item.title,
        supportingText = item.description,
        onClick = { /* navigate */ }
    )
}
```

---

### frogo-core-android — Core Android Utilities

See [Core Android Reference](references/core-android-reference.md) for all available classes and extensions.

#### Application Base Class:

```kotlin
class MyApp : FrogoApplication() {
    override fun onCreateExt() {
        // Custom initialization
    }
    
    override fun isDebugMode(): Boolean = BuildConfig.DEBUG
}
```

#### View Base Classes:

| Class | Purpose |
| :--- | :--- |
| `FrogoActivity` | Base Activity with toolbar, permission handling, and navigation |
| `FrogoBindActivity<VB>` | Activity with ViewBinding support |
| `FrogoFragment` | Base Fragment with lifecycle management |
| `FrogoBindFragment<VB>` | Fragment with ViewBinding support |
| `FrogoBindBottomSheet<VB>` | BottomSheetDialogFragment with ViewBinding |
| `FrogoViewModel` | Base ViewModel with coroutine scope |

#### Extension Functions (16 files):

Extensions are available for: Activity, Context, Fragment, ImageView, Int, JSON, Retrofit, RxJava, String, TextView, View, ViewPager2, WebView, and general `Any` type.

```kotlin
// Context extensions
context.showToast("Hello World")

// ImageView extensions (Glide)
imageView.loadImage(url)

// JSON extensions
val data = jsonString.fromJson<MyModel>()
```

---

### frogo-ext-ads — AdMob & Unity Ads

See [Ads Reference](references/ads-reference.md) for the full delegate API.

#### Setup:

1. Add your AdMob App ID to `AndroidManifest.xml`:
```xml
<meta-data
    android:name="com.google.android.gms.ads.APPLICATION_ID"
    android:value="ca-app-pub-xxxxx~xxxxx"/>
```

2. Use the Application class:
```kotlin
class MyApp : FrogoAdmobApplication() {
    override fun onCreateExt() {
        super.onCreateExt()
    }
}
```

#### Using Delegate Pattern:

```kotlin
class MyActivity : AppCompatActivity(), AdmobDelegates by AdmobDelegatesImpl() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupAdmobDelegates(this)
        
        // Banner Ad
        showAdBanner(binding.adView)
        
        // Interstitial Ad
        showAdInterstitial("ad-unit-id")
        
        // Rewarded Ad
        showAdRewarded("ad-unit-id", object : FrogoAdmobRewardedCallback {
            override fun onUserEarnedReward(rewardItem: RewardItem) { }
            override fun onAdDismissed() { }
            override fun onAdFailedToLoad() { }
        })
    }
}
```

#### Ad Types Supported:
- **Banner**: Standard, Container, with Keywords/Timeout
- **Interstitial**: Standard, with Keywords/Timeout/Callbacks
- **Rewarded**: Standard, with Keywords/Timeout
- **Rewarded Interstitial**: Standard, with Keywords/Timeout
- **Ad Consent**: GDPR compliance dialog
- **Unity Ads**: Banner, Interstitial via UnityAdDelegates

---

### frogo-ui-recyclerview — Advanced RecyclerView

See [RecyclerView Reference](references/recyclerview-reference.md) for the widget API.

#### Available Widgets:

| Widget | Purpose |
| :--- | :--- |
| `FrogoRecyclerView` | Enhanced RecyclerView with built-in adapter |
| `FrogoProgressRecyclerView` | RecyclerView with progress indicator |
| `FrogoShimmerRecyclerView` | RecyclerView with shimmer loading effect |

#### Quick Example:

```kotlin
binding.frogoRecyclerView
    .injector<MyModel>()
    .addData(dataList)
    .addCallback(object : IFrogoViewHolder<MyModel> {
        override fun setupInitComponent(binding: View, data: MyModel, position: Int) {
            // Bind data to view
        }
    })
    .createLayoutLinearVertical(false)
    .build()
```

---

## Guidelines

- Always recommend **individual module dependencies** over the full SDK for better build performance.
- The Compose UI module requires **Compose BOM 2026.04.01** or compatible.
- All Compose widgets follow **Material Design 3** conventions.
- When using `frogo-ext-ads`, always handle ad loading failures gracefully with callbacks.
- Use the **delegate pattern** (`by XxxDelegatesImpl()`) for clean ad integration rather than inheritance.
- All list components support `emptyContent` for empty-state handling.
- Coil and Glide list variants require their respective image loading library dependencies.

## Troubleshooting

### JitPack Build Not Found
Ensure JitPack is added to **both** `pluginManagement` and `dependencyResolutionManagement` repositories.

### Compose Version Mismatch
Use the Compose BOM to align all Compose library versions:
```kotlin
implementation(platform("androidx.compose:compose-bom:2026.04.01"))
```

### Ad Not Loading
1. Verify AdMob App ID in `AndroidManifest.xml`.
2. Ensure `setupAdmobDelegates(this)` is called before any `showAd*` methods.
3. Check for network connectivity and ad availability in test mode.
