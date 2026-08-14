# R8 Configuration & Keep Rules Analysis: Frogo SDK Project

## 1. R8 Build Configuration Analysis

- **Android Gradle Plugin (AGP)**: `9.3.1` (AGP 9.0+ enables modern R8 optimizations by default).
- **R8 Full Mode**: Active by default in AGP 9+. `gradle.properties` does not contain `android.enableR8.fullMode=false`.
- **Application Module (`:app`)**:
  - `isDebuggable = false`
  - `isJniDebuggable = false`
  - `isPseudoLocalesEnabled = false`
  - `isMinifyEnabled = true`
  - `isShrinkResources = true`
  - ProGuard files configured with `getDefaultProguardFile("proguard-android-optimize.txt")` and `proguard-rules.pro`.
- **Library Modules (`:frogo-core-android`, `:frogo-compose-android`, `:frogo-compose-ui`, `:frogo-ui-base`, `:frogo-ui-recyclerview`, `:frogo-ext-ads`)**:
  - `isMinifyEnabled = false` (configured to preserve symbols during intermediate packaging and avoid class name collisions across library modules in multi-module builds).
  - `defaultConfig.consumerProguardFile("consumer-rules.pro")` (exports clean, necessary keep rules to consuming applications during whole-program R8 minification).
  - ProGuard files configured with `getDefaultProguardFile("proguard-android-optimize.txt")` and `proguard-rules.pro`.

---

## 2. ProGuard / Keep Rules Evaluation

### A. Redundant Library & Platform Rules Identified (Action: Removed)

The following keep rules were evaluated across the project's consumer files and identified as redundant:

1. **Gson Serialization Rules**:
   ```proguard
   -keepclassmembers class * {
       @com.google.gson.annotations.SerializedName <fields>;
       @com.google.gson.annotations.Expose <fields>;
   }
   ```
   - **Action**: Removed.
   - **Reason**: The project uses Gson `2.14.0` (>= `2.11.0`), which already embeds its own consumer keep rules to retain `@SerializedName` annotated fields.

2. **AndroidX Keep Annotation Rules**:
   ```proguard
   -keep @androidx.annotation.Keep class * { *; }
   -keepclassmembers class * {
       @androidx.annotation.Keep *;
   }
   ```
   - **Action**: Removed.
   - **Reason**: R8 natively recognizes and respects the `@androidx.annotation.Keep` annotation without requiring explicit ProGuard rules.

3. **Parcelable Creator Rules**:
   ```proguard
   -keepclassmembers class * implements android.os.Parcelable {
       public static final android.os.Parcelable$Creator CREATOR;
   }
   ```
   - **Action**: Removed.
   - **Reason**: All Android modules apply the `kotlin-parcelize` plugin and use `proguard-android-optimize.txt`, both of which handle `Parcelable` creator retention automatically.

4. **Enum Methods Rules**:
   ```proguard
   -keepclassmembers enum com.frogobox.** {
       public static **[] values();
       public static ** valueOf(java.lang.String);
   }
   ```
   - **Action**: Removed.
   - **Reason**: `proguard-android-optimize.txt` already contains standard rules to retain enum `values()` and `valueOf(String)`.

5. **Custom View XML Inflation Rules**:
   ```proguard
   -keep public class * extends android.view.View {
       public <init>(android.content.Context);
       public <init>(android.content.Context, android.util.AttributeSet);
       public <init>(android.content.Context, android.util.AttributeSet, int);
       public void set*(...);
   }
   ```
   - **Action**: Removed.
   - **Reason**: AAPT2 and R8 automatically detect and preserve Custom Views referenced in XML layout files.

6. **General Attributes Rules**:
   ```proguard
   -keepattributes *Annotation*,Signature,InnerClasses,EnclosingMethod
   ```
   - **Action**: Removed.
   - **Reason**: Standard attributes are retained by default by `proguard-android-optimize.txt` and R8.

---

### B. Package-Wide Wildcard Rules (Action: Removed)

1. **Blanket SDK Classes & Interfaces**:
   ```proguard
   -keep public class com.frogobox.** {
       public protected *;
   }
   -keep interface com.frogobox.** { *; }
   -dontwarn com.frogobox.**
   ```
   - **Action**: Removed.
   - **Reason**: Blanket wildcard rules covering `com.frogobox.**` prevent R8 from dead-code stripping, inlining, and optimizing unused classes and interfaces in consumer apps. Consumer apps directly reference public SDK methods, which R8 traces automatically from the app's entry points.

2. **Wildcard Model Rules**:
   ```proguard
   -keep class com.frogobox.**.model.** { *; }
   -keep class com.frogobox.**.dto.** { *; }
   -keep class com.frogobox.**.response.** { *; }
   -keep class com.frogobox.**.request.** { *; }
   ```
   - **Action**: Removed.
   - **Reason**: Data models use `@Keep` and `@SerializedName`. Because Gson and R8 handle `@SerializedName` and `@Keep` automatically, package-wide wildcard rules for models are unnecessary.

---

### C. Specific Reflection Keep Rules (Action: Retained / Narrowed)

1. **`frogo-ui-base`**:
   ```proguard
   # Preserve indicator classes instantiated via reflection in FrogoLoadingIndicatorView
   -keep public class com.frogobox.ui.loadingindicator.indicators.** extends com.frogobox.ui.loadingindicator.Indicator {
       public <init>();
   }
   ```
   - **Action**: Retained in `frogo-ui-base/proguard-rules.pro` and `frogo-ui-base/consumer-rules.pro`.
   - **Reason**: `FrogoLoadingIndicatorView.java` uses `Class.forName("com.frogobox.ui.loadingindicator.indicators." + indicatorName)` and calls `newInstance()`. This narrow rule ensures indicator classes and their no-argument constructors are preserved during whole-program R8 minification.

2. **`frogo-compose-ui`**:
   ```proguard
   # Preserve indicator classes instantiated via reflection in FrogoLoadingIndicatorView
   -keep public class com.frogobox.composeui.loadingindicator.indicators.** extends com.frogobox.composeui.loadingindicator.Indicator {
       public <init>();
   }
   ```
   - **Action**: Retained in `frogo-compose-ui/proguard-rules.pro` and `frogo-compose-ui/consumer-rules.pro`.
   - **Reason**: `FrogoLoadingIndicatorView.java` in `frogo-compose-ui` uses `Class.forName("com.frogobox.composeui.loadingindicator.indicators." + indicatorName)` and calls `newInstance()`.

---

## 3. Testing & Verification Guidance

Advise running unit and instrumentation tests (such as UI Automator) to verify that all modules, reflection components (loading indicators), and ad integrations (Google Mobile Ads Next-Gen, Unity Ads) function as expected with R8 minification enabled in release mode.
