# 📋 Issue Planning: Pembuatan Module `frogo-compose-android`

## 📌 Ringkasan

Buat module library Android baru bernama **`frogo-compose-android`** sebagai padanan dari `frogo-core-android`, namun seluruh base class-nya menggunakan **Jetpack Compose** sebagai UI toolkit, bukan XML View / ViewBinding. Package name utama adalah `com.frogobox.compose`.

---

## 🎯 Tujuan

- Menyediakan base class Compose (Activity, BottomSheet, ViewModel) yang dapat digunakan kembali di seluruh project yang berbasis Compose.
- Mengikuti pola dan konvensi modul yang sudah ada (`frogo-core-android`).
- Module ini **kosong saat pertama dibuat** — hanya scaffolding, file konfigurasi, dan struktur package yang disiapkan terlebih dahulu.
- Base class yang mengisi module ini akan dibuat pada fase selanjutnya.

---

## 📂 Struktur Module Yang Akan Dibuat

```
frogo-compose-android/
├── .gitignore
├── build.gradle.kts
├── consumer-rules.pro
├── proguard-rules.pro
└── src/
    └── main/
        ├── AndroidManifest.xml
        └── java/
            └── com/
                └── frogobox/
                    └── compose/
                        └── view/
                            ├── FrogoComposeActivity.kt      (placeholder kosong)
                            ├── FrogoComposeBottomSheet.kt   (placeholder kosong)
                            ├── FrogoComposeViewModel.kt     (placeholder kosong)
                            └── FrogoComposeScreen.kt        (placeholder kosong / object / interface)
```

> **Catatan:** File-file di dalam `view/` dibuat sebagai placeholder dengan `abstract class` kosong atau `open class` minimal. Implementasi penuh dilakukan pada fase berikutnya.

---

## ⚙️ Konfigurasi Gradle (`build.gradle.kts`)

Mengikuti pola `frogo-core-android`, dengan penyesuaian berikut:

| Item | Value |
|---|---|
| `namespace` | `com.frogobox.compose` |
| `groupId` (publish) | `com.frogobox.compose` |
| `artifactId` (publish) | `frogo-compose-android` |
| `compileSdk` | `ProjectSetting.PROJECT_COMPILE_SDK` |
| `minSdk` | `ProjectSetting.PROJECT_MIN_SDK` |

### Plugin yang Diaktifkan

```kotlin
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)   // <- tambahan untuk Compose
    `maven-publish`
}
```

### buildFeatures

```kotlin
buildFeatures {
    compose = true
    buildConfig = true
}

composeOptions {
    // versi akan mengikuti Compose BOM yang sudah ada di project
}
```

### Dependencies Inti

```kotlin
dependencies {
    api(project(DependencyGradle.FROGO_PATH_SDK))   // depend ke frogo-core-android

    // Jetpack Compose BOM
    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.material3)

    // Activity Compose
    api(libs.androidx.activity.compose)

    // Lifecycle / ViewModel Compose
    api(libs.androidx.lifecycle.viewmodel.compose)
    api(libs.androidx.lifecycle.runtime.compose)

    // Navigation Compose (opsional, bisa ditambah nanti)
    // api(libs.androidx.navigation.compose)

    debugApi(libs.androidx.compose.ui.tooling)
}
```

---

## 🗂️ Perubahan di `buildSrc`

### `ProjectSetting.kt`

Tambahkan konstanta baru:

```kotlin
// Layer baru: compose
private const val LAYER_COMPOSE = "compose"

const val MODULE_NAME_COMPOSE = "frogo-$LAYER_COMPOSE-android"

const val LIBRARY_NAME_COMPOSE = "compose"

const val PROJECT_LIB_ID_COMPOSE = "$BASE_PACAKGE_NAME.$LIBRARY_NAME_COMPOSE"
```

### `DependencyGradle.kt`

Tambahkan path dependency:

```kotlin
const val FROGO_PATH_COMPOSE = ":${ProjectSetting.MODULE_NAME_COMPOSE}"
```

---

## 📝 Perubahan di `settings.gradle.kts`

Tambahkan module baru ke dalam daftar `include`:

```kotlin
include(
    ":app",
    ":frogo-core",
    ":frogo-core-android",
    ":frogo-compose-android",   // <- TAMBAH INI
    ":frogo-ui-base",
    ":frogo-ui-recyclerview",
    ":frogo-ext-ads",
)
```

---

## 🗒️ Daftar File Base Class (Isi Awal Minimal / Placeholder)

Berikut deskripsi singkat masing-masing file yang akan dibuat dalam kondisi **kosong / minimal** dahulu:

### 1. `FrogoComposeActivity.kt`

- Extends: `ComponentActivity`
- Override `onCreate` → panggil `setContent {}`
- Hook kosong: `setupCompose()`, `setupViewModel()`, `setupDelegates()`
- Back press handling via `BackHandler` Compose

### 2. `FrogoComposeBottomSheet.kt`

- Berbasis `BottomSheetDialogFragment` (karena interop dengan Fragment masih umum)
- Atau bisa menggunakan pure Compose `ModalBottomSheet` di dalam `FrogoComposeActivity`
- **Keputusan:** Saat ini buat dua varian:
  - `FrogoComposeBottomSheetFragment` — extends `BottomSheetDialogFragment`, isi dengan `ComposeView`
  - Dokumentasikan opsi `ModalBottomSheet` sebagai alternatif murni Compose

### 3. `FrogoComposeViewModel.kt`

- Extends: `ViewModel()`
- Sama seperti `FrogoViewModel` — tambahkan hooks `onStart()`, `onClearDisposable()`
- Siap digunakan dengan `viewModel()` atau `hiltViewModel()` di Compose

### 4. `FrogoComposeScreen.kt` *(Opsional — fase berikutnya)*

- Interface atau abstract marker untuk menandai Composable function sebagai "Screen"
- Bisa berupa `interface` kosong atau annotation class `@FrogoScreen`

---

## 🔄 Perbandingan: `frogo-core-android` vs `frogo-compose-android`

| Aspek | `frogo-core-android` | `frogo-compose-android` |
|---|---|---|
| Package | `com.frogobox.sdk` | `com.frogobox.compose` |
| UI Toolkit | XML View + ViewBinding | Jetpack Compose |
| Base Activity | `FrogoActivity` / `FrogoBindActivity` | `FrogoComposeActivity` |
| Base Fragment | `FrogoFragment` / `FrogoBindFragment` | *(tidak ada — Compose menggantikan Fragment)* |
| Base BottomSheet | `FrogoBottomSheet` / `FrogoBindBottomSheet` | `FrogoComposeBottomSheetFragment` + `ModalBottomSheet` |
| Base ViewModel | `FrogoViewModel` | `FrogoComposeViewModel` |
| Build Plugin | `android.library` | `android.library` + `kotlin.compose` |

---

## ✅ Checklist Tugas (untuk AI Eksekutor)

### Phase 1 — Scaffolding Module (Prioritas Utama)

- [ ] Buat folder `frogo-compose-android/` di root project
- [ ] Buat `frogo-compose-android/.gitignore`
- [ ] Buat `frogo-compose-android/consumer-rules.pro` (kosong)
- [ ] Buat `frogo-compose-android/proguard-rules.pro` (copy dari modul lain)
- [ ] Buat `frogo-compose-android/build.gradle.kts` sesuai spec di atas
- [ ] Buat `frogo-compose-android/src/main/AndroidManifest.xml`
- [ ] Buat struktur package `src/main/java/com/frogobox/compose/view/`

### Phase 2 — BuildSrc Update

- [ ] Update `buildSrc/src/main/kotlin/ProjectSetting.kt` — tambah konstanta Compose
- [ ] Update `buildSrc/src/main/kotlin/DependencyGradle.kt` — tambah `FROGO_PATH_COMPOSE`

### Phase 3 — Settings Update

- [ ] Update `settings.gradle.kts` — include `:frogo-compose-android`

### Phase 4 — Base Classes (Placeholder)

- [ ] Buat `FrogoComposeActivity.kt` (minimal, dapat di-extend)
- [ ] Buat `FrogoComposeBottomSheetFragment.kt` (menggunakan ComposeView)
- [ ] Buat `FrogoComposeViewModel.kt`

### Phase 5 — Verifikasi

- [ ] Pastikan project dapat di-sync Gradle tanpa error
- [ ] Pastikan module muncul di Android Studio
- [ ] Pastikan tidak ada conflict namespace / package

---

## ⚠️ Catatan Penting untuk AI Eksekutor

1. **Jangan mengubah module `frogo-core-android`** — module Compose ini berdiri sendiri.
2. Dependency arah: `frogo-compose-android` → depends on → `frogo-core-android` (opsional), bukan sebaliknya.
3. Versi Compose BOM gunakan yang sudah ada di `libs.versions.toml` — jangan tambahkan versi baru tanpa cek.
4. Semua base class dibuat **abstract** agar tidak bisa diinstansiasi langsung.
5. Jika `libs.plugins.kotlin.compose` belum ada di `libs.versions.toml`, tambahkan terlebih dahulu.
6. File placeholder boleh berisi hanya deklarasi class + `TODO("Not yet implemented")` di body-nya.

---

## 📎 Referensi

- Module referensi: `frogo-core-android/build.gradle.kts`
- Base class referensi: `frogo-core-android/src/main/java/com/frogobox/sdk/view/`
  - `FrogoActivity.kt`
  - `FrogoBindActivity.kt`
  - `FrogoFragment.kt`
  - `FrogoBindFragment.kt`
  - `FrogoBottomSheet.kt`
  - `FrogoBindBottomSheet.kt`
  - `FrogoViewModel.kt`
- BuildSrc referensi: `buildSrc/src/main/kotlin/ProjectSetting.kt`
- Settings referensi: `settings.gradle.kts`
