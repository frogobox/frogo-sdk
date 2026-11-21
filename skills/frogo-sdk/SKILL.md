---
name: frogo-sdk
description: Use when developing Android apps or libraries integrating Frogo SDK (v4.0.1), implementing Jetpack Compose UI with Material Design 3, building edge-to-edge Compose activities with UDF/MVI State ViewModels, or using Frogo core Android utilities and extensions.
globs:
  - "**/*.kt"
  - "**/*.kts"
  - "**/libs.versions.toml"
tags:
  - Android
  - Jetpack Compose
  - Material Design 3
  - UDF MVI State ViewModel
---

# Frogo SDK Integration & Development Specialist

This skill provides complete instructions for integrating, developing, and extending applications using the **Frogo SDK** suite of Android libraries (v4.0.1).

**Repository:** `frogobox/frogo-sdk`  
**Latest Stable Version:** `4.0.1`  
**Core Tech Stack:**
- **AGP:** `9.5.1`
- **Kotlin:** `2.4.21`
- **KSP:** `2.3.10`
- **Compile / Target SDK:** `37` (Min SDK: `24`)
- **Compose BOM:** `2026.09.00` (Material 3)
- **Coil:** `3.6.3` (Coil 3 multiplatform/compose: `io.coil-kt.coil3:coil-compose:3.6.3`)
- **Glide Compose:** `1.0.0-beta10`

---

## 🏛️ Architecture Overview

Frogo SDK is organized into 3 consolidated Android/Kotlin libraries:

| Module | Package / Namespace | Artifact Coordinates (JitPack) | Purpose |
| :--- | :--- | :--- | :--- |
| `frogo-core` | `com.frogobox.coreutil` | Pure Kotlin (no Android dependency) | Cross-platform utilities, string, date, math helpers |
| `frogo-android` | `com.frogobox.sdk`, `com.frogobox.ui` | `com.github.frogobox.frogo-sdk:frogo-android:4.0.1` | Base Activity/Fragment/BottomSheet (ViewBinding), UDF `FrogoStateViewModel`, 100% Kotlin Shimmer, XML animations & indicators, 16+ extension files |
| `frogo-compose` | `com.frogobox.compose`, `com.frogobox.composeui` | `com.github.frogobox.frogo-sdk:frogo-compose:4.0.1` | `FrogoComposeActivity` (Edge-to-Edge, system UI), `FrogoComposeStateViewModel` (UDF/MVI), 70+ ready-to-use Compose widgets, templates, Coil 3 & Glide lists, animations, fireworks canvas, loading indicators |

### 🌟 Key Highlights in v4.0.0
- **100% Kotlin Codebase**: Converted all remaining legacy Java classes and Shimmer components (`FrogoShimmer`, `FrogoShimmerDrawable`, `FrogoShimmerFrameLayout`) to clean, idiomatic Kotlin.
- **Architectural Refocus**: Completely removed legacy advertising modules (`frogo-ads-admob`, `frogo-ads-unity`, `frogo-ext-ads`) to make Frogo SDK a pure, lightweight Android architecture and Compose UI framework.
- **Modern Toolchain Upgrade**: Updated AGP to `9.5.1`, Kotlin to `2.4.21`, KSP to `2.3.10`, and Compile/Target SDK to `37` (Android 16).

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
frogoSdk = "4.0.1"
composeBom = "2026.09.00"
coil = "3.6.3"

[libraries]
# Frogo SDK Consolidated Suite
frogo-core = { group = "com.github.frogobox.frogo-sdk", name = "frogo-core", version.ref = "frogoSdk" }
frogo-android = { group = "com.github.frogobox.frogo-sdk", name = "frogo-android", version.ref = "frogoSdk" }
frogo-compose = { group = "com.github.frogobox.frogo-sdk", name = "frogo-compose", version.ref = "frogoSdk" }

# Compose BOM & Coil 3
androidx-compose-bom = { group = "androidx.compose", name = "compose-bom", version.ref = "composeBom" }
coil-compose = { group = "io.coil-kt.coil3", name = "coil-compose", version.ref = "coil" }
coil-network-okhttp = { group = "io.coil-kt.coil3", name = "coil-network-okhttp", version.ref = "coil" }
```

### Module `build.gradle.kts` Example:

```kotlin
dependencies {
    // Jetpack Compose Architecture & UI Kit
    implementation(libs.frogo.compose)

    // Android Core & XML UI Kit
    implementation(libs.frogo.android)

    // Pure Kotlin Utilities
    implementation(libs.frogo.core)

    // Coil 3 Image Loading
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
}
```

---

## 📋 Starter Templates & References

The skill bundle provides ready-to-copy templates and deep reference runbooks:
- **`templates/libs.versions.toml`**: Ready-to-use Gradle Version Catalog snippet.
- **`templates/settings.gradle.kts`**: Repository configuration snippet.
- **`templates/FrogoComposeActivityTemplate.kt`**: Edge-to-edge Compose screen with UDF/MVI State ViewModel.
- **`references/compose-ui-reference.md`**: 70+ Material Design 3 Compose widgets, Coil 3 & Glide lists, fireworks canvas, animations.
- **`references/core-android-reference.md`**: Base classes, ViewBinding, extensions, UDF ViewModel, and Scoped Storage file helpers.

---

## 🛠️ Module Runbooks & Code Recipes

### 1. `frogo-compose` — Edge-to-Edge Activity & UDF ViewModel

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

### 2. `frogo-compose` — 70+ Widgets, Coil 3, Lists & Fireworks

Detailed catalog: [Compose UI Reference](references/compose-ui-reference.md)

#### A. Coil 3 Image Lazy Column:
```kotlin
package com.example.app.ui

import androidx.compose.runtime.Composable
import com.frogobox.composeui.list.coil.FrogoCoilLazyColumn

data class Article(val title: String, val imageUrl: String)

@Composable
fun ArticleList(articles: List<Article>) {
    FrogoCoilLazyColumn(
        data = articles,
        imageUrl = { it.imageUrl },
        title = { it.title },
        onItemClick = { article ->
            // Handle item click
        }
    )
}
```

#### B. Fireworks Canvas & Loading Spinners:
```kotlin
package com.example.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.frogobox.composeui.fireworks.FrogoFireworksCompose
import com.frogobox.composeui.fireworks.frogoClickWithFireworksCompose
import com.frogobox.composeui.fireworks.rememberFrogoFireworksStateCompose
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

## 🛡️ Critical Agent Guidelines & Rules

### 1. No Warning Suppression Policy
- **STRICTLY FORBIDDEN**: Never write `@Suppress("DEPRECATION")` or `@SuppressWarnings("deprecation")`.
- **ALWAYS MIGRATE**: When an API is deprecated, inspect the recommended replacement and refactor to modern equivalents:
  - Migrated Image Loading: Use Coil 3 (`coil3.compose.AsyncImage`).
  - Migrated Edge-to-Edge: Use `androidx.activity.enableEdgeToEdge`.

### 2. Package & Import Guardrails for Other Agents
| Component | CORRECT Package Import | INCORRECT / BANNED Import |
| :--- | :--- | :--- |
| Coil 3 Composable | `coil3.compose.AsyncImage` | `coil.compose.AsyncImage` (Coil 2 package) |
| Frogo Compose Activity | `com.frogobox.compose.view.FrogoComposeActivity` | |
| Frogo State ViewModel | `com.frogobox.compose.viewmodel.FrogoComposeStateViewModel` | |

### 3. Verification Commands
When verifying changes in this project, execute:
- Unit Tests: `./gradlew testDebugUnitTest`
- Assemble Debug: `./gradlew assembleDebug`
- Lint: `./gradlew lintDebug`
