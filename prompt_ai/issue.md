# 📋 Issue Planning: Frogo Compose UI — Widget, Template & List Components

## 📌 Ringkasan

Membangun koleksi lengkap **reusable Compose UI components** untuk library **`frogo-compose-ui`**.
Semua composable menggunakan prefix **`Frogo`** sesuai konvensi penamaan library.

Target output path:
```
frogo-compose-ui/src/main/java/com/frogobox/composeui/
```

---

## 🎯 Tujuan

1. **Widget Compose** — Komponen dasar yang sering dipakai programmer saat membangun UI dengan Compose (Button, TextField, Card, Image, Icon, Switch, Chip, Badge, Divider, dll).
2. **Template UI Compose** — Template siap pakai seperti Dialog, Bottom Sheet, App Bar, Scaffold, Navigation Drawer, Tab Layout, Snackbar, dll.
3. **Template List Compose** — Template list/grid menggunakan `LazyColumn`/`LazyRow`/`LazyVerticalGrid` dengan integrasi image loading library: **Coil**, **Glide**, dan tanpa image loader (basic).

---

## 📂 Struktur Folder Yang Akan Dibuat

```text
frogo-compose-ui/src/main/java/com/frogobox/composeui/
├── theme/                          (sudah ada)
│   ├── Color.kt
│   ├── Theme.kt
│   └── Type.kt
│
├── widget/                         (komponen dasar)
│   ├── FrogoButton.kt              (sudah ada, akan di-enhance)
│   ├── FrogoOutlinedButton.kt
│   ├── FrogoIconButton.kt
│   ├── FrogoFloatingActionButton.kt
│   ├── FrogoTextField.kt
│   ├── FrogoOutlinedTextField.kt
│   ├── FrogoSearchBar.kt
│   ├── FrogoCard.kt
│   ├── FrogoElevatedCard.kt
│   ├── FrogoImage.kt
│   ├── FrogoIcon.kt
│   ├── FrogoSwitch.kt
│   ├── FrogoCheckbox.kt
│   ├── FrogoRadioButton.kt
│   ├── FrogoChip.kt
│   ├── FrogoFilterChip.kt
│   ├── FrogoBadge.kt
│   ├── FrogoDivider.kt
│   ├── FrogoCircularProgress.kt
│   ├── FrogoLinearProgress.kt
│   ├── FrogoAvatar.kt
│   └── FrogoSpacer.kt
│
├── template/                       (template UI siap pakai)
│   ├── dialog/
│   │   ├── FrogoAlertDialog.kt
│   │   ├── FrogoConfirmDialog.kt
│   │   ├── FrogoInputDialog.kt
│   │   ├── FrogoLoadingDialog.kt
│   │   └── FrogoImageDialog.kt
│   │
│   ├── bottomsheet/
│   │   ├── FrogoBottomSheet.kt
│   │   ├── FrogoMenuBottomSheet.kt
│   │   └── FrogoListBottomSheet.kt
│   │
│   ├── appbar/
│   │   ├── FrogoTopAppBar.kt
│   │   ├── FrogoCenterTopAppBar.kt
│   │   ├── FrogoLargeTopAppBar.kt
│   │   ├── FrogoMediumTopAppBar.kt
│   │   ├── FrogoBottomAppBar.kt
│   │   └── FrogoSearchTopAppBar.kt
│   │
│   ├── scaffold/
│   │   ├── FrogoScaffold.kt
│   │   └── FrogoScaffoldWithFab.kt
│   │
│   ├── navigation/
│   │   ├── FrogoNavigationBar.kt
│   │   ├── FrogoNavigationRail.kt
│   │   └── FrogoNavigationDrawer.kt
│   │
│   ├── tab/
│   │   ├── FrogoTabRow.kt
│   │   └── FrogoScrollableTabRow.kt
│   │
│   ├── snackbar/
│   │   └── FrogoSnackbar.kt
│   │
│   ├── shimmer/
│   │   ├── FrogoShimmerEffect.kt
│   │   └── FrogoShimmerItem.kt
│   │
│   └── empty/
│       └── FrogoEmptyState.kt
│
└── list/                           (template list compose)
    ├── basic/
    │   ├── FrogoLazyColumn.kt
    │   ├── FrogoLazyRow.kt
    │   ├── FrogoLazyVerticalGrid.kt
    │   ├── FrogoLazyVerticalStaggeredGrid.kt
    │   └── FrogoListItem.kt
    │
    ├── coil/
    │   ├── FrogoCoilImage.kt
    │   ├── FrogoCoilListItem.kt
    │   ├── FrogoCoilLazyColumn.kt
    │   ├── FrogoCoilLazyRow.kt
    │   └── FrogoCoilLazyVerticalGrid.kt
    │
    └── glide/
        ├── FrogoGlideImage.kt
        ├── FrogoGlideListItem.kt
        ├── FrogoGlideLazyColumn.kt
        ├── FrogoGlideLazyRow.kt
        └── FrogoGlideLazyVerticalGrid.kt
```

---

## 🔧 Phase 1 — Widget Compose (Komponen Dasar)

Komponen dasar yang **paling sering dipakai** developer saat membangun UI Compose.

### 1.1 Button Variants

| File | Deskripsi |
|---|---|
| `FrogoButton.kt` | ✅ Sudah ada — Enhance: tambahkan parameter `enabled`, `icon`, `colors` |
| `FrogoOutlinedButton.kt` | Outlined button dengan border |
| `FrogoIconButton.kt` | Icon-only button (toolbar actions, dsb) |
| `FrogoFloatingActionButton.kt` | FAB standar + Extended FAB |

### 1.2 Text Input

| File | Deskripsi |
|---|---|
| `FrogoTextField.kt` | Filled text field dengan label, placeholder, error state |
| `FrogoOutlinedTextField.kt` | Outlined text field, mendukung leading/trailing icon |
| `FrogoSearchBar.kt` | Search bar composable dengan clear button dan query callback |

### 1.3 Card

| File | Deskripsi |
|---|---|
| `FrogoCard.kt` | Card dasar Material3 dengan customizable content slot |
| `FrogoElevatedCard.kt` | Elevated card dengan shadow |

### 1.4 Media & Icon

| File | Deskripsi |
|---|---|
| `FrogoImage.kt` | Image composable wrapper (resource / bitmap / painter) |
| `FrogoIcon.kt` | Icon wrapper dengan tint, size, dan content description |
| `FrogoAvatar.kt` | Avatar circle image (dengan placeholder & initial text) |

### 1.5 Selection Controls

| File | Deskripsi |
|---|---|
| `FrogoSwitch.kt` | Switch on/off |
| `FrogoCheckbox.kt` | Checkbox dengan optional label text |
| `FrogoRadioButton.kt` | Radio button dengan optional label text |

### 1.6 Chips & Badge

| File | Deskripsi |
|---|---|
| `FrogoChip.kt` | Assist/Suggestion chip |
| `FrogoFilterChip.kt` | Filter chip dengan selected state |
| `FrogoBadge.kt` | Badge counter (notification badge) |

### 1.7 Progress & Divider

| File | Deskripsi |
|---|---|
| `FrogoCircularProgress.kt` | Circular progress indicator (determinate & indeterminate) |
| `FrogoLinearProgress.kt` | Linear progress bar |
| `FrogoDivider.kt` | Horizontal/Vertical divider |
| `FrogoSpacer.kt` | Spacer helper dengan ukuran preset (small, medium, large) |

### Checklist Phase 1
- [ ] Enhance `FrogoButton.kt` (tambah parameter `enabled`, `icon`, `colors`)
- [ ] Buat `FrogoOutlinedButton.kt`
- [ ] Buat `FrogoIconButton.kt`
- [ ] Buat `FrogoFloatingActionButton.kt`
- [ ] Buat `FrogoTextField.kt`
- [ ] Buat `FrogoOutlinedTextField.kt`
- [ ] Buat `FrogoSearchBar.kt`
- [ ] Buat `FrogoCard.kt`
- [ ] Buat `FrogoElevatedCard.kt`
- [ ] Buat `FrogoImage.kt`
- [ ] Buat `FrogoIcon.kt`
- [ ] Buat `FrogoAvatar.kt`
- [ ] Buat `FrogoSwitch.kt`
- [ ] Buat `FrogoCheckbox.kt`
- [ ] Buat `FrogoRadioButton.kt`
- [ ] Buat `FrogoChip.kt`
- [ ] Buat `FrogoFilterChip.kt`
- [ ] Buat `FrogoBadge.kt`
- [ ] Buat `FrogoCircularProgress.kt`
- [ ] Buat `FrogoLinearProgress.kt`
- [ ] Buat `FrogoDivider.kt`
- [ ] Buat `FrogoSpacer.kt`

---

## 🔧 Phase 2 — Template UI Compose

Template composable **siap pakai** yang menggabungkan beberapa komponen menjadi satu kesatuan UI.

### 2.1 Dialog Templates

| File | Deskripsi |
|---|---|
| `FrogoAlertDialog.kt` | Alert dialog standar (title, message, confirm/dismiss button) |
| `FrogoConfirmDialog.kt` | Confirmation dialog (Yes/No pattern) |
| `FrogoInputDialog.kt` | Dialog berisi text field untuk input dari user |
| `FrogoLoadingDialog.kt` | Dialog dengan circular progress (non-dismissable) |
| `FrogoImageDialog.kt` | Dialog yang menampilkan preview image full |

### 2.2 Bottom Sheet Templates

| File | Deskripsi |
|---|---|
| `FrogoBottomSheet.kt` | Modal bottom sheet dasar dengan content slot |
| `FrogoMenuBottomSheet.kt` | Bottom sheet berisi list menu (icon + label) |
| `FrogoListBottomSheet.kt` | Bottom sheet berisi scrollable list items |

### 2.3 App Bar Templates

| File | Deskripsi |
|---|---|
| `FrogoTopAppBar.kt` | Top app bar standar (title, navigation icon, actions) |
| `FrogoCenterTopAppBar.kt` | Center-aligned top app bar |
| `FrogoLargeTopAppBar.kt` | Large collapsing top app bar |
| `FrogoMediumTopAppBar.kt` | Medium collapsing top app bar |
| `FrogoBottomAppBar.kt` | Bottom app bar (dengan FAB slot) |
| `FrogoSearchTopAppBar.kt` | Top app bar dengan mode search terintegrasi |

### 2.4 Scaffold Templates

| File | Deskripsi |
|---|---|
| `FrogoScaffold.kt` | Scaffold lengkap (top bar + bottom bar + FAB + content) |
| `FrogoScaffoldWithFab.kt` | Scaffold khusus dengan floating action button bawaan |

### 2.5 Navigation Templates

| File | Deskripsi |
|---|---|
| `FrogoNavigationBar.kt` | Bottom navigation bar (Material3 NavigationBar) |
| `FrogoNavigationRail.kt` | Navigation rail untuk tablet/large screen |
| `FrogoNavigationDrawer.kt` | Modal navigation drawer |

### 2.6 Tab Templates

| File | Deskripsi |
|---|---|
| `FrogoTabRow.kt` | Fixed tab row (TabRow + Tab items) |
| `FrogoScrollableTabRow.kt` | Scrollable tab row untuk banyak tab |

### 2.7 Snackbar

| File | Deskripsi |
|---|---|
| `FrogoSnackbar.kt` | Snackbar wrapper dengan action button |

### 2.8 Shimmer / Loading State

| File | Deskripsi |
|---|---|
| `FrogoShimmerEffect.kt` | Shimmer animation modifier/effect |
| `FrogoShimmerItem.kt` | Shimmer placeholder item (card/list item shape) |

### 2.9 Empty State

| File | Deskripsi |
|---|---|
| `FrogoEmptyState.kt` | Empty state UI (icon, title, subtitle, action button) |

### Checklist Phase 2
- [ ] Buat folder `template/dialog/`
- [ ] Buat `FrogoAlertDialog.kt`
- [ ] Buat `FrogoConfirmDialog.kt`
- [ ] Buat `FrogoInputDialog.kt`
- [ ] Buat `FrogoLoadingDialog.kt`
- [ ] Buat `FrogoImageDialog.kt`
- [ ] Buat folder `template/bottomsheet/`
- [ ] Buat `FrogoBottomSheet.kt`
- [ ] Buat `FrogoMenuBottomSheet.kt`
- [ ] Buat `FrogoListBottomSheet.kt`
- [ ] Buat folder `template/appbar/`
- [ ] Buat `FrogoTopAppBar.kt`
- [ ] Buat `FrogoCenterTopAppBar.kt`
- [ ] Buat `FrogoLargeTopAppBar.kt`
- [ ] Buat `FrogoMediumTopAppBar.kt`
- [ ] Buat `FrogoBottomAppBar.kt`
- [ ] Buat `FrogoSearchTopAppBar.kt`
- [ ] Buat folder `template/scaffold/`
- [ ] Buat `FrogoScaffold.kt`
- [ ] Buat `FrogoScaffoldWithFab.kt`
- [ ] Buat folder `template/navigation/`
- [ ] Buat `FrogoNavigationBar.kt`
- [ ] Buat `FrogoNavigationRail.kt`
- [ ] Buat `FrogoNavigationDrawer.kt`
- [ ] Buat folder `template/tab/`
- [ ] Buat `FrogoTabRow.kt`
- [ ] Buat `FrogoScrollableTabRow.kt`
- [ ] Buat folder `template/snackbar/`
- [ ] Buat `FrogoSnackbar.kt`
- [ ] Buat folder `template/shimmer/`
- [ ] Buat `FrogoShimmerEffect.kt`
- [ ] Buat `FrogoShimmerItem.kt`
- [ ] Buat folder `template/empty/`
- [ ] Buat `FrogoEmptyState.kt`

---

## 🔧 Phase 3 — Template List Compose

Template list/grid Compose yang mendukung berbagai image loading library.

### 3.1 Basic List (Tanpa Image Loader)

Menggunakan composable standar tanpa library image loader eksternal.

| File | Deskripsi |
|---|---|
| `FrogoLazyColumn.kt` | Generic lazy column wrapper — menerima list data `<T>` + composable item renderer |
| `FrogoLazyRow.kt` | Generic lazy row wrapper — horizontal scrollable list |
| `FrogoLazyVerticalGrid.kt` | Grid layout wrapper — configurable column count |
| `FrogoLazyVerticalStaggeredGrid.kt` | Staggered grid layout (Pinterest-style) |
| `FrogoListItem.kt` | List item composable standar (leading icon, title, subtitle, trailing) |

#### Spesifikasi `FrogoLazyColumn`
```kotlin
@Composable
fun <T> FrogoLazyColumn(
    data: List<T>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    key: ((T) -> Any)? = null,
    emptyContent: @Composable () -> Unit = {},
    itemContent: @Composable (index: Int, item: T) -> Unit
)
```

### 3.2 Coil Integration

Menggunakan **Coil Compose** (`io.coil-kt:coil-compose`) untuk image loading.

| File | Deskripsi |
|---|---|
| `FrogoCoilImage.kt` | Image composable menggunakan Coil `AsyncImage` (placeholder, error, crossfade) |
| `FrogoCoilListItem.kt` | List item dengan Coil image (leading thumbnail) |
| `FrogoCoilLazyColumn.kt` | Lazy column yang item-nya menggunakan Coil image |
| `FrogoCoilLazyRow.kt` | Lazy row horizontal cards dengan Coil image |
| `FrogoCoilLazyVerticalGrid.kt` | Grid gallery menggunakan Coil image |

#### Spesifikasi `FrogoCoilImage`
```kotlin
@Composable
fun FrogoCoilImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    placeholder: Painter? = null,
    error: Painter? = null,
    contentScale: ContentScale = ContentScale.Crop,
    crossfadeEnabled: Boolean = true,
    crossfadeDuration: Int = 300,
    shape: Shape = RectangleShape,
    onLoading: (() -> Unit)? = null,
    onSuccess: (() -> Unit)? = null,
    onError: (() -> Unit)? = null
)
```

### 3.3 Glide Integration

Menggunakan **Glide Compose** (`com.github.bumptech.glide:compose`) untuk image loading.

| File | Deskripsi |
|---|---|
| `FrogoGlideImage.kt` | Image composable menggunakan Glide `GlideImage` (placeholder, error, transition) |
| `FrogoGlideListItem.kt` | List item dengan Glide image (leading thumbnail) |
| `FrogoGlideLazyColumn.kt` | Lazy column yang item-nya menggunakan Glide image |
| `FrogoGlideLazyRow.kt` | Lazy row horizontal cards dengan Glide image |
| `FrogoGlideLazyVerticalGrid.kt` | Grid gallery menggunakan Glide image |

#### Spesifikasi `FrogoGlideImage`
```kotlin
@Composable
fun FrogoGlideImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    placeholder: Painter? = null,
    error: Painter? = null,
    contentScale: ContentScale = ContentScale.Crop,
    shape: Shape = RectangleShape,
    requestBuilderTransform: RequestBuilderTransform<Drawable>? = null
)
```

### Checklist Phase 3
- [ ] Buat folder `list/basic/`
- [ ] Buat `FrogoLazyColumn.kt`
- [ ] Buat `FrogoLazyRow.kt`
- [ ] Buat `FrogoLazyVerticalGrid.kt`
- [ ] Buat `FrogoLazyVerticalStaggeredGrid.kt`
- [ ] Buat `FrogoListItem.kt`
- [ ] Buat folder `list/coil/`
- [ ] Buat `FrogoCoilImage.kt`
- [ ] Buat `FrogoCoilListItem.kt`
- [ ] Buat `FrogoCoilLazyColumn.kt`
- [ ] Buat `FrogoCoilLazyRow.kt`
- [ ] Buat `FrogoCoilLazyVerticalGrid.kt`
- [ ] Buat folder `list/glide/`
- [ ] Buat `FrogoGlideImage.kt`
- [ ] Buat `FrogoGlideListItem.kt`
- [ ] Buat `FrogoGlideLazyColumn.kt`
- [ ] Buat `FrogoGlideLazyRow.kt`
- [ ] Buat `FrogoGlideLazyVerticalGrid.kt`

---

## 🔧 Phase 4 — Update Dependencies (`build.gradle.kts` & `libs.versions.toml`)

### Tambahkan di `libs.versions.toml`

```toml
[versions]
coil = "3.1.0"
glideCompose = "1.0.0-beta01"

[libraries]
coil-compose = { group = "io.coil-kt.coil3", name = "coil-compose", version.ref = "coil" }
coil-network-okhttp = { group = "io.coil-kt.coil3", name = "coil-network-okhttp", version.ref = "coil" }
glide-compose = { group = "com.github.bumptech.glide", name = "compose", version.ref = "glideCompose" }
```

### Tambahkan di `frogo-compose-ui/build.gradle.kts`

```kotlin
dependencies {
    // ... existing dependencies ...

    // Coil Compose (untuk list/coil package)
    api(libs.coil.compose)
    api(libs.coil.network.okhttp)

    // Glide Compose (untuk list/glide package)
    api(libs.glide.compose)

    // Compose Foundation (untuk LazyVerticalStaggeredGrid)
    api(libs.androidx.compose.foundation)
}
```

### Tambahkan library foundation di `libs.versions.toml`

```toml
[libraries]
androidx-compose-foundation = { group = "androidx.compose.foundation", name = "foundation" }
```

### Checklist Phase 4
- [ ] Tambah versi `coil` dan `glideCompose` di `libs.versions.toml`
- [ ] Tambah library `coil-compose`, `coil-network-okhttp`, `glide-compose` di `libs.versions.toml`
- [ ] Tambah library `androidx-compose-foundation` di `libs.versions.toml`
- [ ] Update dependencies di `frogo-compose-ui/build.gradle.kts`

---

## 🔧 Phase 5 — Sinkronisasi & Verifikasi

### Checklist Phase 5
- [ ] Gradle Sync berhasil tanpa error
- [ ] Semua file dapat dikenali oleh IDE (tidak ada unresolved reference)
- [ ] Package structure sesuai dengan rencana di atas
- [ ] Semua composable menggunakan prefix `Frogo`
- [ ] Semua composable memiliki `@Preview` function
- [ ] Semua composable mendukung parameter `Modifier` sebagai best practice

---

## 📏 Konvensi & Best Practices

### Naming Convention
- Semua composable **WAJIB** berawalan `Frogo` (contoh: `FrogoButton`, `FrogoAlertDialog`, `FrogoCoilImage`)
- Nama file = Nama composable utama (contoh: `FrogoButton.kt` berisi `@Composable fun FrogoButton(...)`)

### Parameter Convention
- Selalu sediakan `modifier: Modifier = Modifier` sebagai parameter
- Gunakan default value yang masuk akal
- Gunakan `@Composable` lambda untuk content slot

### Preview
- Setiap composable **harus** memiliki `@Preview` composable function
- Format preview: `FrogoXxxPreview()` (contoh: `FrogoButtonPreview()`)

### Package Name
- Base package: `com.frogobox.composeui`
- Widget: `com.frogobox.composeui.widget`
- Template: `com.frogobox.composeui.template.<category>`
- List: `com.frogobox.composeui.list.<loader>`

---

## 📊 Ringkasan Total Files

| Kategori | Jumlah File |
|---|---|
| Widget (Phase 1) | 22 file |
| Template UI (Phase 2) | 22 file |
| Template List (Phase 3) | 17 file |
| **Total** | **61 file baru** |

---

## ⚠️ Catatan Penting

1. **Backward Compatibility**: File `FrogoButton.kt` yang sudah ada akan di-enhance, bukan di-replace. Parameter lama tetap compatible.
2. **Compose BOM**: Semua Compose dependencies menggunakan BOM dari `libs.versions.toml` untuk konsistensi versi.
3. **Image Loader Optional**: Package `list/coil/` dan `list/glide/` bersifat opsional. Pengguna library bisa memilih mana yang ingin dipakai.
4. **Material3**: Semua komponen menggunakan Material3 design system.
5. **Eksekusi Bertahap**: Disarankan mengeksekusi per-phase agar mudah di-review dan di-debug.
