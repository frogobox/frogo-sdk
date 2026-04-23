# 📋 Issue Planning: Pembuatan Module `frogo-compose-ui`

## 📌 Ringkasan

Buat module library Android baru bernama **`frogo-compose-ui`** sebagai padanan dari module UI (seperti `frogo-ui-base`), namun khusus menampung resource dan komponen UI berbasis **Jetpack Compose**. Package name utama adalah `com.frogobox.composeui`.

---

## 🎯 Tujuan

- Menyediakan resource Compose seperti base composable UI, themes, styles, typography, dan komponen UI reusable lainnya.
- Module ini **kosong saat pertama dibuat** — hanya scaffolding, file konfigurasi, dan struktur package yang disiapkan terlebih dahulu.
- Dokumentasi ini dibuat agar AI agen (eksekutor) dapat menjalankan langkah-langkah setup dengan tepat dan efisien.

---

## 📂 Struktur Module Yang Akan Dibuat

```text
frogo-compose-ui/
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
                    └── composeui/
                        ├── theme/               (placeholder)
                        │   ├── Color.kt
                        │   ├── Theme.kt
                        │   └── Type.kt
                        └── widget/              (placeholder)
                            └── FrogoButton.kt   (placeholder)
```

> **Catatan:** File-file di dalam `theme/` dan `widget/` dibuat sebagai file kosong atau file minimal (placeholder). Implementasi penuh akan dilakukan pada tahap selanjutnya.

---

## ⚙️ Konfigurasi Gradle (`build.gradle.kts`)

Konfigurasi mirip dengan module Compose sebelumnya, dengan penyesuaian pada penamaan dan ID:

| Item | Value |
|---|---|
| `namespace` | `com.frogobox.composeui` |
| `groupId` (publish) | `com.frogobox.composeui` |
| `artifactId` (publish) | `frogo-compose-ui` |
| `compileSdk` | `ProjectSetting.PROJECT_COMPILE_SDK` |
| `minSdk` | `ProjectSetting.PROJECT_MIN_SDK` |

### Plugin yang Diaktifkan

```kotlin
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
    `maven-publish`
}
```

### buildFeatures

```kotlin
buildFeatures {
    compose = true
    buildConfig = true
}
```

### Dependencies Inti

```kotlin
dependencies {
    api(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    
    // Bergantung ke frogo-compose-android jika diperlukan base compose class
    api(project(DependencyGradle.FROGO_PATH_COMPOSE)) 
    
    // Jetpack Compose BOM
    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.material3)

    debugApi(libs.androidx.compose.ui.tooling)
}
```

### Publishing Configuration

Jangan lupa tambahkan blok `afterEvaluate` untuk setup `maven-publish` seperti pada module lain (menggunakan `ProjectSetting.PROJECT_VERSION_NAME` dsb).

---

## 🗂️ Perubahan di `buildSrc`

### `ProjectSetting.kt`

Tambahkan konstanta baru (misalnya berdekatan dengan `MODULE_NAME_COMPOSE` atau `MODULE_NAME_CORE_UI`):

```kotlin
const val MODULE_NAME_COMPOSE_UI = "frogo-$LAYER_COMPOSE-ui"

const val LIBRARY_NAME_COMPOSE_UI = "composeui"

const val PROJECT_LIB_ID_COMPOSE_UI = "$BASE_PACAKGE_NAME.$LIBRARY_NAME_COMPOSE_UI"
```

### `DependencyGradle.kt`

Tambahkan path dependency:

```kotlin
const val FROGO_PATH_COMPOSE_UI = ":${ProjectSetting.MODULE_NAME_COMPOSE_UI}"
```

---

## 📝 Perubahan di `settings.gradle.kts`

Tambahkan module baru ke dalam daftar `include`:

```kotlin
include(
    // ... module lainnya ...
    ":frogo-compose-android",
    ":frogo-compose-ui",   // <- TAMBAH INI
    ":frogo-ui-base",
    // ...
)
```

---

## ✅ Checklist Tugas (untuk AI Eksekutor)

### Phase 1 — Scaffolding Module
- [ ] Buat folder `frogo-compose-ui/` di root project.
- [ ] Buat `frogo-compose-ui/.gitignore` (isinya minimal `/build`).
- [ ] Buat `frogo-compose-ui/consumer-rules.pro` (bisa kosong).
- [ ] Buat `frogo-compose-ui/proguard-rules.pro` (copy isi standar dari module lain).
- [ ] Buat `frogo-compose-ui/build.gradle.kts` sesuai spesifikasi di atas dan tambahkan blok `publishing`.
- [ ] Buat `frogo-compose-ui/src/main/AndroidManifest.xml` (minimalist `<manifest package="com.frogobox.composeui" />` atau tanpa atribut package karena sudah diset di `namespace` gradle).
- [ ] Buat struktur direktori package `src/main/java/com/frogobox/composeui/`.

### Phase 2 — Update Konstanta BuildSrc
- [ ] Tambahkan konfigurasi `MODULE_NAME_COMPOSE_UI`, `LIBRARY_NAME_COMPOSE_UI`, `PROJECT_LIB_ID_COMPOSE_UI` di `buildSrc/src/main/kotlin/ProjectSetting.kt`.
- [ ] Tambahkan `FROGO_PATH_COMPOSE_UI` di `buildSrc/src/main/kotlin/DependencyGradle.kt`.

### Phase 3 — Update Settings
- [ ] Tambahkan `include(":frogo-compose-ui")` di file `settings.gradle.kts`.

### Phase 4 — File Placeholder Compose
- [ ] Buat folder `theme/` dan `widget/` di dalam package `com.frogobox.composeui`.
- [ ] Buat minimal satu file placeholder Kotlin kosong seperti `Theme.kt` atau biarkan package kosong siap diisi nanti.

### Phase 5 — Sinkronisasi dan Verifikasi
- [ ] Lakukan Gradle Sync dan pastikan build berjalan sukses tanpa error.
- [ ] Pastikan module dikenali di Android Studio dan tidak ada konflik penamaan.

---

## ⚠️ Catatan Tambahan
- Gunakan versi library Compose dari `libs.versions.toml` (melalui Compose BOM).
- Modul ini tidak mengganggu modul yang sudah ada. Silakan eksekusi penambahan modul dan file scaffolding secara bertahap.
