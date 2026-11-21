# Agent Project Guidelines & Frogo SDK Skills Enforcement

Welcome to **Frogo SDK** (`frogobox/frogo-sdk`). This document serves as the master guide for all AI coding agents (Antigravity, Claude Code, GitHub Copilot, Cursor, OpenAI Codex, Windsurf, Devin, etc.) operating in this repository.

---

## 🧭 Repository Overview & Modules

Frogo SDK is a production-grade multi-module Android and Kotlin library suite designed to accelerate Android development.

| Module | Package / Namespace | Description | Key Tech Stack |
| :--- | :--- | :--- | :--- |
| `frogo-core` | `com.frogobox.coreutil` | Pure Kotlin utilities (platform-agnostic) | Kotlin 2.4.21 |
| `frogo-android` | `com.frogobox.sdk` | Android core foundation & XML UI kit: Base Activity/Fragment/BottomSheet, `FrogoStateViewModel` (UDF/MVI), Shimmer skeleton, loading indicators, activity animations, 16+ extension files | Android SDK 37, ViewBinding |
| `frogo-compose` | `com.frogobox.compose` | Complete Jetpack Compose architecture and UI kit: `FrogoComposeActivity` (Edge-to-Edge, system UI), `FrogoComposeStateViewModel` (UDF/MVI), 70+ ready-to-use widgets, templates, animations, fireworks canvas, loading spinners | Compose BOM 2026.09.00, Coil 3.6.3, Glide Compose |
| `app` | `com.frogobox.app` | Showcase application demonstrating all modules | Hilt, Compose, Navigation |

**Current Version:** `4.0.1` (defined in `build-logic/convention/src/main/kotlin/ProjectSetting.kt`)

---

## ⚡ Skills System for AI Agents

This repository provides standardized agent skills to teach any AI agent how to integrate and use the Frogo SDK accurately without hallucinating outdated APIs.

### Available Skills
- **`frogo-sdk`**:
  - **Skill Entrypoint:** [skills/frogo-sdk/SKILL.md](skills/frogo-sdk/SKILL.md)
  - **Mirrored Discovery Path:** [.agents/skills/frogo-sdk/SKILL.md](.agents/skills/frogo-sdk/SKILL.md)
  - **Registration Config:** [.agents/skills.json](.agents/skills.json)

### Specialized Reference Runbooks & Starter Templates
When generating code or resolving issues for specific domains, consult the reference guides and starter templates:
1. **Jetpack Compose UI (Material 3) & Coil 3:** [skills/frogo-sdk/references/compose-ui-reference.md](skills/frogo-sdk/references/compose-ui-reference.md)
2. **Core Android Utilities, Extensions & UDF ViewModel:** [skills/frogo-sdk/references/core-android-reference.md](skills/frogo-sdk/references/core-android-reference.md)
3. **Starter Templates:** [skills/frogo-sdk/templates/](skills/frogo-sdk/templates/) (`libs.versions.toml`, `settings.gradle.kts`, Compose activities)

### 🚀 Using this Skill in Other Projects
To install this skill so other Android projects and AI coding agents (Antigravity, Gemini CLI, Claude Code, Cursor, Copilot, Codex) can use it:

**Global Installation (All Agents on Machine):**
```powershell
.\scripts\install-skill.ps1 -Global
```
*(Installs to `~/.gemini/config/skills/frogo-sdk`, `~/.claude/skills/frogo-sdk`, and `~/.agents/skills/frogo-sdk`)*

**Project-Specific Installation:**
```powershell
.\scripts\install-skill.ps1 -TargetProject "d:\AndroidProject\MyOtherApp"
```

**Linux / macOS / WSL:**
```bash
./scripts/install-skill.sh -t /path/to/my-other-app
```

---

## 🛡️ Critical Agent Enforcement Rules

### 1. Zero Warning Suppression Policy (Mandatory)
- **STRICTLY PROHIBITED:** Agents must NEVER use deprecation suppression annotations:
  - Kotlin/Java: `@Suppress("DEPRECATION")`, `@SuppressWarnings("deprecation")`
  - Any form of lint suppression ignoring obsolete APIs.
- **MANDATORY MIGRATION:** Always migrate to modern official replacements:
  - Migrate image loading to Coil 3 (`coil3.compose.AsyncImage`).
  - Migrate edge-to-edge layout to `androidx.activity.enableEdgeToEdge`.

### 2. Dependency Catalog Enforcement
- All dependencies must be managed through `gradle/libs.versions.toml`.
- When adding or modifying dependencies, do NOT hardcode version strings in `build.gradle.kts`. Use the version catalog aliases (`libs.*`).

### 3. Package Import Guardrails
Agents must adhere to current package namespaces:
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