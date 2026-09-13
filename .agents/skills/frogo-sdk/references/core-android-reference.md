# Frogo Core Android — Full API Reference (v3.0.8)

Package: `com.frogobox.sdk`  
Artifact: `com.github.frogobox.frogo-sdk:frogo-core-android:3.0.8`

---

## 1. Application Class (`FrogoApplication`)

Base Application class integrating Crash Handling via CustomActivityOnCrash (CAOC):

```kotlin
package com.example.app

import com.frogobox.sdk.FrogoApplication

class MyApp : FrogoApplication() {
    override fun onCreateExt() {
        // App-wide initialization (DI, logging, analytics)
    }

    override fun isDebugMode(): Boolean = BuildConfig.DEBUG
}
```

---

## 2. Base Activities & Fragments (`com.frogobox.sdk.ui.*` / `com.frogobox.sdk.view.*`)

### ViewBinding Base Classes
- `FrogoBindActivity<VB : ViewBinding>`: Auto-inflates ViewBinding and provides lifecycle hooks.
- `FrogoBindFragment<VB : ViewBinding>`: ViewBinding support for Fragments.
- `FrogoBindBottomSheet<VB : ViewBinding>`: ViewBinding for BottomSheetDialogFragment.

```kotlin
package com.example.app.ui

import android.os.Bundle
import com.example.app.databinding.ActivityMainBinding
import com.frogobox.sdk.view.FrogoBindActivity

class MainActivity : FrogoBindActivity<ActivityMainBinding>() {

    override fun setupViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onCreateExt(savedInstanceState: Bundle?) {
        // Access views cleanly via binding
        binding.tvTitle.text = "Welcome to Frogo SDK"
    }
}
```

---

## 3. UDF / MVI State ViewModel (`FrogoStateViewModel`)

Symmetrical to `FrogoComposeStateViewModel`, designed for XML View architectures:

```kotlin
package com.example.app.ui

import com.frogobox.sdk.viewmodel.FrogoStateViewModel

data class UserListState(val isLoading: Boolean = false, val users: List<String> = emptyList())
sealed interface UserListEffect {
    data class ShowToast(val message: String) : UserListEffect
}

class UserListViewModel : FrogoStateViewModel<UserListState, UserListEffect>(UserListState()) {

    fun fetchUsers() {
        updateState { copy(isLoading = true) }
        // Background fetch...
        updateState { copy(isLoading = false, users = listOf("Alice", "Bob")) }
        emitEffect(UserListEffect.ShowToast("Users loaded"))
    }
}
```

---

## 4. Extension Functions (`com.frogobox.sdk.ext.*`)

Frogo Core Android provides 16 extension categories:

| Target | Extension File | Key Capabilities |
| :--- | :--- | :--- |
| `Activity` | `FrogoActivityExt.kt` | Intent launchers, fullscreen mode, keyboard toggle |
| `Context` | `FrogoContextExt.kt` | `showToast()`, `getColorExt()`, `getDrawableExt()` |
| `Fragment` | `FrogoFragmentExt.kt` | Fragment navigation, parent activity shortcuts |
| `ImageView` | `FrogoImageViewExt.kt` | `loadImage(url)`, placeholder/error configuration (Glide) |
| `TextView` | `FrogoTextViewExt.kt` | HTML text, gradient text, strike-through |
| `View` | `FrogoViewExt.kt` | `show()`, `hide()`, `invisible()`, debounce click listener |
| `Coroutines` | `FrogoCoroutinesExt.kt` | Dispatchers helpers, safe launches |
| `JSON` | `FrogoJsonExt.kt` | `toJson()`, `fromJson<T>()` with Gson |
| `String` | `FrogoStringExt.kt` | Validation, hashing, date parsing |
