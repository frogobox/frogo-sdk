# Agent Project Guidelines & Frogo SDK Skills Enforcement

Welcome to **Frogo SDK** (`frogobox/frogo-sdk`). This document serves as the master guide for all AI coding agents (Antigravity, Claude Code, GitHub Copilot, Cursor, OpenAI Codex, Windsurf, Devin, etc.) operating in this repository.

---

## 🧭 Repository Overview & Modules

Frogo SDK is a production-grade multi-module Android and Kotlin library suite designed to accelerate Android development.

| Module | Package / Namespace | Description | Key Tech Stack |
| :--- | :--- | :--- | :--- |
| `frogo-core` | `com.frogobox.coreutil` | Pure Kotlin utilities (platform-agnostic) | Kotlin 2.4.20 |
| `frogo-core-android` | `com.frogobox.sdk` | Base Activity/Fragment/BottomSheet, `FrogoStateViewModel` (UDF/MVI), 16+ extension files | Android SDK 37, ViewBinding |
| `frogo-compose-android` | `com.frogobox.compose` | `FrogoComposeActivity` (Edge-to-Edge, system UI), `FrogoComposeStateViewModel` (UDF/MVI) | Activity Compose, Lifecycle Compose |
| `frogo-compose-ui` | `com.frogobox.composeui` | 70+ ready-to-use Compose widgets, templates, animations, fireworks canvas, loading spinners | Compose BOM 2026.09.00, Coil 3.6.2, Glide Compose |
| `frogo-ui-base` | `com.frogobox.ui` | XML-based UI utilities and view helpers | Android Views |
| `frogo-ui-recyclerview` | `com.frogobox.recycler` | High-performance RecyclerView with `injector()` builder, shimmer & progress states | RecyclerView 1.4.0 |
| `frogo-ext-ads` | `com.frogobox.ads` | Google Mobile Ads SDK (Next-Gen 1.4.0), Unity Ads (4.20.0), App Open Ads, Compose Ad Activities | Next-Gen Ads Mobile SDK, Unity Ads |
| `app` | `com.frogobox.app` | Showcase application demonstrating all modules | Hilt, Compose, Navigation |

**Current Version:** `3.0.8` (defined in `buildSrc/src/main/kotlin/ProjectSetting.kt`)

---

## ⚡ Skills System for AI Agents

This repository provides standardized agent skills to teach any AI agent how to integrate and use the Frogo SDK accurately without hallucinating outdated APIs.

### Available Skills
- **`frogo-sdk`**:
  - **Skill Entrypoint:** [skills/frogo-sdk/SKILL.md](skills/frogo-sdk/SKILL.md)
  - **Mirrored Discovery Path:** [.agents/skills/frogo-sdk/SKILL.md](.agents/skills/frogo-sdk/SKILL.md)
  - **Registration Config:** [.agents/skills.json](.agents/skills.json)

### Specialized Reference Runbooks
When generating code or resolving issues for specific domains, consult the reference guides:
1. **Google Mobile Ads Next-Gen & Unity Ads:** [skills/frogo-sdk/references/ads-reference.md](skills/frogo-sdk/references/ads-reference.md)
2. **Jetpack Compose UI (Material 3) & Coil 3:** [skills/frogo-sdk/references/compose-ui-reference.md](skills/frogo-sdk/references/compose-ui-reference.md)
3. **Core Android Utilities, Extensions & UDF ViewModel:** [skills/frogo-sdk/references/core-android-reference.md](skills/frogo-sdk/references/core-android-reference.md)
4. **Advanced RecyclerView & Shimmer Builders:** [skills/frogo-sdk/references/recyclerview-reference.md](skills/frogo-sdk/references/recyclerview-reference.md)

---

## 🛡️ Critical Agent Enforcement Rules

### 1. Zero Warning Suppression Policy (Mandatory)
- **STRICTLY PROHIBITED:** Agents must NEVER use deprecation suppression annotations:
  - Kotlin/Java: `@Suppress("DEPRECATION")`, `@SuppressWarnings("deprecation")`
  - Any form of lint suppression ignoring obsolete APIs.
- **MANDATORY MIGRATION:** Always migrate to modern official replacements:
  - Migrate legacy AdMob to Google Mobile Ads SDK Next-Gen (`com.google.android.libraries.ads.mobile.sdk.*`).
  - Migrate image loading to Coil 3 (`coil3.compose.AsyncImage`).
  - Migrate edge-to-edge layout to `androidx.activity.enableEdgeToEdge`.

### 2. Dependency Catalog Enforcement
- All dependencies must be managed through `gradle/libs.versions.toml`.
- When adding or modifying dependencies, do NOT hardcode version strings in `build.gradle.kts`. Use the version catalog aliases (`libs.*`).

### 3. Package Import Guardrails
Agents must adhere to current package namespaces:
- **AdMob (Next-Gen):** Use `com.google.android.libraries.ads.mobile.sdk.banner.AdView`, `AdSize`, `MobileAds`. NEVER import `com.google.android.gms.ads.*`.
- **Unity Ads:** Use `com.unity3d.ads.*` via `com.frogobox.ads.delegate.UnityAdDelegates`.
- **Coil 3:** Use `coil3.compose.AsyncImage` and `coil3.request.ImageRequest`.
- **Compose Base Activity:** Use `com.frogobox.compose.view.FrogoComposeActivity`.
- **Compose State ViewModel:** Use `com.frogobox.compose.viewmodel.FrogoComposeStateViewModel`.

---

## 🧪 Verification & Testing Commands

Before completing any task, agents must run verification:
- **Unit Tests:**
  ```bash
  ./gradlew testDebugUnitTest
  ```
- **Build APK:**
  ```bash
  ./gradlew assembleDebug
  ```
- **Lint Check:**
  ```bash
  ./gradlew lintDebug
  ```