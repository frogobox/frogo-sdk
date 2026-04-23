# Frogo SDK 🚀

### The Ultimate Android & Desktop Development Toolkit

![Frogo SDK Hero Banner](docs/image/hero_banner.png)

[![JitPack](https://jitpack.io/v/frogobox/frogo-sdk.svg?style=flat-square)](https://jitpack.io/#frogobox/frogo-sdk)
[![Android CI](https://github.com/frogobox/frogo-sdk/actions/workflows/android-ci.yml/badge.svg)](https://github.com/frogobox/frogo-sdk/actions/workflows/android-ci.yml)
[![License](https://img.shields.io/github/license/frogobox/frogo-sdk?style=flat-square)](https://github.com/frogobox/frogo-sdk/blob/master/LICENSE)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com)

**Frogo SDK** is a comprehensive suite of Android libraries designed to accelerate your development workflow. From stunning Jetpack Compose UIs to seamless AdMob integration, Frogo SDK provides the building blocks you need to create high-quality apps faster.

- SDK for anything your problem to make easier developing android apps
- Available for android and desktop
- Privacy Policy [Click Here](https://github.com/frogobox/frogo-sdk/blob/master/PRIVACY-POLICY.md)
- License [Click Here](https://github.com/frogobox/frogo-sdk/blob/master/LICENSE)

---

## Version Release

This Is Latest Release

    $version_release = 3.0.0

What's New??

    * Library Update Latest Version *
    * LTS (Long Term Service) *
    * Migrate AGP 9.1.0 *

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
Latest Version: `3.0.0`

```kotlin
dependencies {
    // Core SDK
    implementation("com.github.frogobox:frogo-sdk:3.0.0")

    // Or specific modules (recommended)
    implementation("com.github.frogobox.frogo-sdk:frogo-compose-ui:3.0.0")
    implementation("com.github.frogobox.frogo-sdk:frogo-ext-ads:3.0.0")
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

## 🤖 AI Agent Skill

Frogo SDK provides an **AI Agent Skill** that enables AI coding assistants (such as Antigravity, Gemini, etc.) to understand and automatically integrate all Frogo SDK modules into your Android project.

### What is AI Agent Skill?

AI Agent Skill is a folder of instructions, code examples, and API references that extends the capabilities of an AI agent for specialized tasks. With this skill, the AI agent can:

- ✅ **Automatically set up** Frogo SDK dependencies in a new project
- ✅ **Generate correct code** using the Frogo SDK API
- ✅ **Understand the architecture** of multi-module structure and design patterns used
- ✅ **Troubleshoot** integration issues with accurate solutions

### Skill Structure

```
skills/frogo-sdk/
├── SKILL.md                              # Main instructions for AI Agent
└── references/
    ├── compose-ui-reference.md           # API for 60+ Jetpack Compose widgets
    ├── core-android-reference.md         # Base classes & extension functions
    ├── ads-reference.md                  # AdMob & Unity Ads integration API
    └── recyclerview-reference.md         # RecyclerView widgets API
```

### How to Install the Skill

#### Method 1: Manual Copy

Copy the `skills/frogo-sdk/` folder to your AI agent's skills directory:

```bash
# For Antigravity Agent
cp -r skills/frogo-sdk/ ~/.gemini/antigravity/skills/frogo-sdk/

# For custom agent
cp -r skills/frogo-sdk/ <YOUR_AGENT_SKILLS_DIR>/frogo-sdk/
```

#### Method 2: Clone from Repository

```bash
# Clone the repo
git clone https://github.com/frogobox/frogo-sdk.git

# Copy the skill to the agent
cp -r frogo-sdk/skills/frogo-sdk/ ~/.gemini/antigravity/skills/frogo-sdk/
```

### How to Use the Skill

Once the skill is installed, the AI agent will **automatically detect** it when you provide prompts related to Frogo SDK. Here are some example prompts you can use:

#### 🎨 Compose UI

```
"Create a dashboard page using FrogoScaffold with TopAppBar and LazyColumn"
```

The AI agent will generate code like:

```kotlin
import com.frogobox.composeui.template.scaffold.FrogoScaffold
import com.frogobox.composeui.template.appbar.FrogoTopAppBar
import com.frogobox.composeui.list.basic.FrogoLazyColumn
import com.frogobox.composeui.list.basic.FrogoListItem

@Composable
fun DashboardScreen(items: List<DashboardItem>) {
    FrogoScaffold(
        topBar = {
            FrogoTopAppBar(title = "Dashboard")
        }
    ) { paddingValues ->
        FrogoLazyColumn(
            data = items,
            contentPadding = paddingValues,
            emptyContent = {
                FrogoEmptyState(
                    title = "No Data Yet",
                    description = "Data will appear here"
                )
            }
        ) { index, item ->
            FrogoListItem(
                headlineText = item.title,
                supportingText = item.description
            )
        }
    }
}
```

#### 📢 AdMob Integration

```
"Add a banner ad and interstitial ad to an Activity using Frogo Ads"
```

The AI agent will generate code like:

```kotlin
import com.frogobox.ads.delegate.AdmobDelegates
import com.frogobox.ads.delegate.AdmobDelegatesImpl

class MainActivity : AppCompatActivity(),
    AdmobDelegates by AdmobDelegatesImpl() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupAdmobDelegates(this)

        // Banner Ad
        showAdBanner(binding.adView)

        // Interstitial Ad
        showAdInterstitial("ca-app-pub-xxxxx/xxxxx")
    }
}
```

#### ♻️ RecyclerView

```
"Create an article list with shimmer loading using FrogoShimmerRecyclerView"
```

The AI agent will generate code like:

```kotlin
// Show shimmer while loading
binding.frogoShimmerRv
    .defineShimmerView(R.layout.shimmer_item_article)
    .showShimmer()

// Display data
viewModel.articles.observe(this) { articles ->
    binding.frogoShimmerRv.hideShimmer()
    binding.frogoShimmerRv
        .injector<Article>()
        .addData(articles)
        .addCallback(object : IFrogoViewHolder<Article> {
            override fun setupInitComponent(view: View, data: Article, position: Int) {
                view.findViewById<TextView>(R.id.tvTitle).text = data.title
            }
        })
        .createLayoutLinearVertical(false)
        .build()
}
```

#### 🛠️ Core Android Utilities

```
"Set up a base Application class and Activity with ViewBinding using Frogo SDK"
```

The AI agent will generate code like:

```kotlin
// Application
class MyApp : FrogoApplication() {
    override fun onCreateExt() {
        // Init DI, analytics, etc.
    }
    override fun isDebugMode(): Boolean = BuildConfig.DEBUG
}

// Activity with ViewBinding
class MyActivity : FrogoBindActivity<ActivityMainBinding>() {
    override fun setupViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onCreateExt(savedInstanceState: Bundle?) {
        binding.textView.text = "Hello Frogo!"
    }
}
```

### Complete API Reference

| Document | Description | API Count |
| :--- | :--- | :--- |
| [SKILL.md](skills/frogo-sdk/SKILL.md) | Main instructions & architecture | - |
| [compose-ui-reference.md](skills/frogo-sdk/references/compose-ui-reference.md) | Jetpack Compose widgets | 60+ composables |
| [core-android-reference.md](skills/frogo-sdk/references/core-android-reference.md) | Base classes & extensions | 7 classes, 16 ext files |
| [ads-reference.md](skills/frogo-sdk/references/ads-reference.md) | AdMob & Unity Ads | 32+ methods |
| [recyclerview-reference.md](skills/frogo-sdk/references/recyclerview-reference.md) | RecyclerView widgets | 3 widgets |

---

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
