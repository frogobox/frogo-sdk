# Frogo Core Android — Full API Reference

Package: `com.frogobox.sdk`

## Application

### FrogoApplication
Base Application class with crash handling via CAOC (CustomActivityOnCrash).

```kotlin
abstract class FrogoApplication : Application() {
    open fun onCreateExt() {}
    open fun isDebugMode(): Boolean = true
    open fun setupCAOC() { /* auto-configured */ }
    open fun customErrorActivity(): Class<out Activity> = FrogoCustomCrashActivity::class.java
}
```

**Usage:**
```kotlin
class MyApp : FrogoApplication() {
    override fun onCreateExt() {
        // Your init logic (DI, analytics, etc.)
    }
    override fun isDebugMode(): Boolean = BuildConfig.DEBUG
}
```

---

## View Base Classes (`view/`)

### FrogoActivity
Full-featured base Activity with:
- Toolbar management
- Permission request handling
- Navigation helpers (Activity/Fragment launching)
- Dialog utilities
- Toast/Snackbar shortcuts

### FrogoBindActivity<VB : ViewBinding>
Activity with ViewBinding support. Auto-inflates the binding.

```kotlin
class MyActivity : FrogoBindActivity<ActivityMyBinding>() {
    override fun setupViewBinding(): ActivityMyBinding =
        ActivityMyBinding.inflate(layoutInflater)
    
    override fun onCreateExt(savedInstanceState: Bundle?) {
        binding.textView.text = "Hello"
    }
}
```

### FrogoFragment
Base Fragment with lifecycle helpers.

### FrogoBindFragment<VB : ViewBinding>
Fragment with ViewBinding support.

```kotlin
class MyFragment : FrogoBindFragment<FragmentMyBinding>() {
    override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMyBinding =
        FragmentMyBinding.inflate(inflater, container, false)
    
    override fun onViewCreatedExt(savedInstanceState: Bundle?) {
        binding.textView.text = "Hello"
    }
}
```

### FrogoBindBottomSheet<VB : ViewBinding>
BottomSheetDialogFragment with ViewBinding.

### FrogoViewModel
Base ViewModel with CoroutineScope.

---

## Extension Functions (`ext/`)

### FrogoContextExt.kt
Context-level utilities:
- `showToast(message)`
- `copyToClipboard(text)`
- `openUrl(url)`
- `shareText(text)`
- `openPlayStore(packageName)`
- `getScreenWidth()` / `getScreenHeight()`
- `hideKeyboard(view)`
- `showKeyboard(view)`

### FrogoContextActivityExt.kt
Activity launching helpers:
- `startActivity<T>()`
- `startActivityForResult<T>(requestCode)`
- `startActivityWithData<T>(key, value)`

### FrogoContextFragmentExt.kt
Fragment transaction helpers.

### FrogoActivityExt.kt
Activity-specific utilities.

### FrogoFragmentExt.kt
Fragment-specific utilities.

### FrogoImageViewExt.kt
Glide-powered image loading:
- `ImageView.loadImage(url)`
- `ImageView.loadImageCircle(url)`
- `ImageView.loadImageRounded(url, radius)`
- `ImageView.loadImageRes(resId)`

### FrogoViewExt.kt
View utilities:
- `View.visible()` / `View.gone()` / `View.invisible()`
- `View.setOnSingleClickListener { }`

### FrogoTextViewExt.kt
TextView utilities.

### FrogoViewPager2Ext.kt
ViewPager2 setup helpers.

### FrogoWebViewExt.kt
WebView configuration:
- `WebView.loadUrlExt(url)`
- `WebView.setupWebView()`

### FrogoStringExt.kt
String utilities:
- `String.toCapitalize()`
- `String.toJsonPrettyPrint()`

### FrogoIntExt.kt
Integer utilities.

### FrogoJsonExt.kt
JSON serialization with Gson:
- `String.fromJson<T>()`
- `Any.toJson()`

### FrogoRetrofitExt.kt
Retrofit response handling.

### FrogoRxJavaExt.kt
RxJava3 scheduling and composition helpers:
- `Observable.applySchedulers()`
- `Flowable.applySchedulers()`

### FrogoAny.kt
General `Any` type extensions.

---

## Delegate Classes (`delegate/`)

Delegation pattern for cleaner code organization.

## Logging (`log/`)

Built-in logging utilities.

## Notification (`notification/`)

Notification builder helpers.

## Licensing (`licensing/`)

License checking utilities.

## Piracy Checker (`piracychecker/`)

App piracy detection.

## Source (`source/`)

Data source abstractions.

## Utilities (`util/`)

General utility classes.
