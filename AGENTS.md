# Agent Project Guidelines & Android Skills Enforcement

## Instruksi Penggunaan Skill Android

Setiap kali mengembangkan, memodifikasi, merombak (*refactoring*), mengoptimalkan, atau menguji proyek Android **piano-tiles** ini, Agent **WAJIB** secara aktif membaca dan menerapkan petunjuk dari **Skills Agent Android** yang relevan sebelum melakukan perubahan kode.

Jika skill yang dibutuhkan **belum terpasang / tidak ditemukan** di sistem lokal, Agent WAJIB mengunduh/menginstalnya terlebih dahulu menggunakan perintah CLI:

```bash
npx agentic-awesome-skills --antigravity --skills <nama-skill>
```

---

### 1. UI, Desain & Arsitektur Android (Compose, XML & Game Design)

- **`edge-to-edge`**: Penerapan tata letak UI adaptif edge-to-edge (penanganan status bar, navigation bar, dan IME/keyboard insets).
- **`migrate-xml-views-to-jetpack-compose`**: Alur kerja terstruktur migrasi layout dari legacy XML View ke Jetpack Compose.
- **`jetpack-compose-m3`**: Praktik terbaik Material Design 3 (M3) pada Jetpack Compose.
- **`android-jetpack-compose-expert`**: Pengoptimalan performa, state management, dan arsitektur UI Compose tingkat lanjut.
- **`game-development`**: Prinsip *game loop*, kontrol input sentuhan (*touch responsiveness*), kelancaran *frame rate* (60/120 FPS), dan *state machine* game (Start, Pause, Playing, Game Over).
- **`mobile-design` & `ui-ux-designer`**: Desain antarmuka mobile, feedback visual sentuhan tile, dan tata letak game yang intuitif.
- **`high-end-visual-design`**: Desain visual premium untuk efek animasi, warna tile, dan visualisasi skor.
- **`wear-compose-m3`**: Pengembangan UI Jetpack Compose M3 untuk perangkat Wear OS.
- **`display-glasses-with-jetpack-compose-glimmer`**: Tampilan UI Jetpack Compose untuk perangkat Smart Glasses / HMD.

### 2. Navigasi, Fitur & Monetisasi

- **`navigation-3`**: Manajemen navigasi Jetpack Navigation 3, deep link, multiple backstack, dan scene transitions.
- **`play-billing-library-version-upgrade`**: Pembaruan dan penanganan Google Play Billing Library (in-app purchase / langganan).
- **`camerax` & `camera1-to-camerax`**: Implementasi API Kamera Android modern (CameraX) dan migrasi dari API Kamera legacy.
- **`apollo-kotlin`**: Integrasi GraphQL Client berbasis Kotlin & Android.

### 3. Build, CLI & Keamanan Android

- **`android-cli`**: Otomasi perintah Android CLI, pengujian, deployment, manajemen emulator, dan diagnosa lingkungan.
- **`agp-9-upgrade`**: Panduan & aturan migrasi Android Gradle Plugin (AGP) ke versi 9.
- **`r8-analyzer`**: Analisis file build dan Proguard/R8 keep rules untuk ukuran APK/AAB optimal.
- **`android-dev`**: Standar & panduan umum pengembangan aplikasi Android modern.
- **`android-intent-security`**: Keamanan Intent, pemindaian celah komunikasi antar-komponen Android.
- **`mobile-security-coder`**: Keamanan kode aplikasi mobile, enkripsi data lokal, dan perlindungan *High Score* dari tamper/cheat.

### 4. Performa, Refactoring, Asinkron & Testing

- **`kotlin-coroutines-expert`**: Praktik terbaik Coroutines, Flow, dan operasi asinkron tanpa memblokir Main UI Thread.
- **`diagnose-android-overheating`**: Deteksi & pencegahan kebocoran memori (*memory leak*) serta pemakaian CPU berlebih saat animasi berjalan.
- **`performance-optimization` & `performance-profiling`**: Pengoptimalan latensi *tap* agar suara berbunyi tanpa delay dan eliminasi *frame drop* (jank).
- **`codebase-cleanup-refactor-clean`**: Pembersihan & penyederhanaan struktur kode controller/game engine.
- **`systematic-debugging`**: Penanganan bug, crash, atau masalah lag animasi secara terstruktur.
- **`test-driven-development`**: Penerapan TDD pada logika kalkulasi skor, tile spawner, dan game loop.
- **`android-ui-journey-testing`**: Otomasi pengujian alur perjalanan pengguna (user journey) pada UI Android.
- **`android_ui_verification`**: Verifikasi visual dan pengujian tampilan komponen UI Android di berbagai ukuran layar.
- **`appium-skill`**: Pengujian otomatisasi mobile lintas platform berbasis Appium.
- **`java-pro`**: Pemrograman Java untuk integrasi modul legacy Android.

### 5. Superpowers & Ponytail Optimization Skills

- **`using-superpowers`**: WAJIB digunakan setiap ada perintah `/using-superpowers` atau di awal tugas untuk menentukan alur kerja (*process skills* seperti `brainstorming`, `systematic-debugging`, `test-driven-development`, `writing-plans`, `verification-before-completion`) sebelum mengeksekusi tindakan atau mengubah kode.
- **`ponytail`**: WAJIB digunakan setiap ada perintah `/ponytail` (serta varian `ponytail-audit`, `ponytail-review`, `ponytail-gain`, `ponytail-help`). Terapkan prinsip YAGNI/lazy senior dev: utamakan solusi paling sederhana, hapus over-engineering, hindari dependen/abstraksi tidak perlu, dan berikan komentar *ceiling/upgrade path* saat menyederhanakan kode.

---

## Prosedur Eksekusi Agent

1. **Identifikasi Skill**: Sebelum merespons perintah, menulis, atau merombak kode, tentukan skill Android, Game Development, Superpowers, atau Ponytail yang relevan dengan tugas.
2. **Cek & Install Skill jika Belum Ada**: Periksa apakah skill tersebut tersedia di lingkungan lokal. Jika **TIDAK** ditemukan, jalankan perintah instalasi terlebih dahulu:

   ```bash
   npx agentic-awesome-skills --antigravity --skills <nama-skill>
   ```

3. **Baca Dokumentasi (`SKILL.md`)**: Gunakan tool `view_file` untuk membaca petunjuk teknis pada file `SKILL.md` dari skill relevan.
4. **Eksekusi Sesuai Panduan**: Terapkan standar coding, arsitektur, dan instruksi dari skill secara konsisten.
