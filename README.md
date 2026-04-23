# Frogo SDK 🚀
## About This Project (release 👷🔧️👷‍♀️⛏)

[![](https://jitpack.io/v/frogobox/frogo-sdk.svg?style=flat-square)](https://jitpack.io/#frogobox/frogo-sdk)
[![Android CI](https://github.com/frogobox/frogo-sdk/actions/workflows/android-ci.yml/badge.svg)](https://github.com/frogobox/frogo-sdk/actions/workflows/android-ci.yml)
[![pages-build-deployment](https://github.com/frogobox/frogo-sdk/actions/workflows/pages/pages-build-deployment/badge.svg)](https://github.com/frogobox/frogo-sdk/actions/workflows/pages/pages-build-deployment)

- SDK for anything your problem to make easier developing android apps
- Available for android and desktop
- Privacy Policy [Click Here](https://github.com/frogobox/frogo-sdk/blob/master/PRIVACY-POLICY.md)
- License [Click Here](https://github.com/frogobox/frogo-sdk/blob/master/LICENSE)

## Version Release

This Is Latest Release

    $version_release = 2.3.7

What's New??

    * Library Update Latest Version *
    * LTS (Long Term Service) *
    * Migrate AGP 9.1.0 *

## Download this project

### Step 1. Add the JitPack repository to your build file (build.gradle : Project)

#### <Option 1> Groovy Gradle

    // Add it in your root build.gradle at the end of repositories:

    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }

#### <Option 2> Kotlin DSL Gradle

```kotlin
// Add it in your root build.gradle.kts at the end of repositories:

allprojects {
    repositories {
        ...
        maven("https://jitpack.io")
    }
}
```

### Step 2. Add the dependency (build.gradle : Module)

#### <Option 1> Groovy Gradle

```groovy
dependencies {
    // library frogo-sdk
    implementation 'com.github.frogobox:frogo-sdk:2.3.7'
}
```

#### <Option 2> Kotlin DSL Gradle

```groovy
dependencies {
    // library frogo-sdk
    implementation("com.github.frogobox:frogo-sdk:2.3.7")
}
```

#### <Option 3> libs.versions.toml
```yml
[versions]
frogoAndroid = "2.3.7"

[libraries]
frogo-android = { group = "com.github.frogobox", name = "frogo-sdk", version.ref = "frogoAndroid" }

dependencies {
    // library frogo-android
    implementation(libs.frogobox.android)
}
```

### The Ultimate Android & Desktop Development Toolkit

![Frogo SDK Hero Banner](docs/image/hero_banner.png)

[![JitPack](https://jitpack.io/v/frogobox/frogo-sdk.svg?style=flat-square)](https://jitpack.io/#frogobox/frogo-sdk)
[![Android CI](https://github.com/frogobox/frogo-sdk/actions/workflows/android-ci.yml/badge.svg)](https://github.com/frogobox/frogo-sdk/actions/workflows/android-ci.yml)
[![License](https://img.shields.io/github/license/frogobox/frogo-sdk?style=flat-square)](https://github.com/frogobox/frogo-sdk/blob/master/LICENSE)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com)

**Frogo SDK** is a comprehensive suite of Android libraries designed to accelerate your development workflow. From stunning Jetpack Compose UIs to seamless AdMob integration, Frogo SDK provides the building blocks you need to create high-quality apps faster.

---

## 🌟 Key Features

- **🎨 Frogo Compose UI**: 60+ reusable Jetpack Compose widgets and professional templates.
- **📢 Frogo Ext Ads**: Simplify AdMob integration with easy-to-use wrappers for Banner, Interstitial, and Native ads.
- **♻️ Frogo UI RecyclerView**: Advanced RecyclerView helpers for painless list management.
- **🛠️ Frogo Core**: Essential utilities, base classes, and extensions for Kotlin development.
- **📱 Multi-Platform**: Available for both Android and Desktop environments.

---

## 📸 Screenshots & Mockups

### Premium Jetpack Compose Components
![Frogo Compose UI Mockup](docs/image/compose_ui_mockup.png)
*Beautiful, modern, and highly customizable UI components following Material Design 3.*

### Seamless Ad Integration
![Frogo Ads Mockup](docs/image/ads_mockup.png)
*Monetize your apps effortlessly with optimized ad placements.*

---

## 🚀 Quick Start

### Step 1: Add JitPack Repository
Add it to your `settings.gradle.kts` (or root `build.gradle`):

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

### Step 2: Add Dependencies
Latest Version: `2.3.7`

```kotlin
dependencies {
    // Core SDK
    implementation("com.github.frogobox:frogo-sdk:2.3.7")

    // Or specific modules (recommended)
    implementation("com.github.frogobox.frogo-sdk:frogo-compose-ui:2.3.7")
    implementation("com.github.frogobox.frogo-sdk:frogo-ext-ads:2.3.7")
}
```

---

## 📦 Modules Overview

| Module | Description | Key Features |
| :--- | :--- | :--- |
| `frogo-compose-ui` | Modern UI Kit | Widgets, Dialogs, Bottom Sheets, Scaffolds |
| `frogo-ext-ads` | Monetization Kit | Easy AdMob Integration (Banner, Interstitial, Native) |
| `frogo-ui-recyclerview` | List Management | Multi-view adapters, simplified ViewHolders |
| `frogo-core-android` | Core Utilities | Extensions, Base Classes, Lifecycle Helpers |

---

## 🤝 Contributing

We welcome contributions! Whether you're fixing a bug or suggesting a new feature, your help is appreciated.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📄 License & Privacy

- **License**: Distributed under the [Apache 2.0 License](https://github.com/frogobox/frogo-sdk/blob/master/LICENSE).
- **Privacy Policy**: Read our policy [here](https://github.com/frogobox/frogo-sdk/blob/master/PRIVACY-POLICY.md).

---

## 📬 Contact

**Muhammad Faisal Amir** - [faisalamircs@gmail.com](mailto:faisalamircs@gmail.com)

Project Link: [https://github.com/frogobox/frogo-sdk](https://github.com/frogobox/frogo-sdk)

---

<!-- SEO Keywords (Hidden) -->
<!-- 
Keywords: Android SDK, Jetpack Compose UI Library, Kotlin Android, Android UI Components, AdMob Helper, RecyclerView Adapter, Android Development Tools, Frogo SDK, Android Library, Material Design 3, Android Monetization, Android UI Kit
-->

## Colaborator

Very open to anyone, I'll write your name under this, please contribute by sending an email to me

- Mail To faisalamircs@gmail.com
- Subject : Github _ [Github-Username-Account] _ [Language] _ [Repository-Name]
- Example : Github_amirisback_kotlin_admob-helper-implementation

Name Of Contribute

- Muhammad Faisal Amir
- Waiting List
- Waiting List

Waiting for your contribute

## Attention !!!

- Please enjoy and don't forget fork and give a star
- Don't Forget Follow My Github Account

![ScreenShoot Apps](docs/image/mad_score.png?raw=true)
