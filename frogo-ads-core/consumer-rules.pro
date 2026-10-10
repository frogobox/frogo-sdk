# Frogo Ads Core Consumer ProGuard Rules
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}
-keep class com.frogobox.ads.model.** { *; }
-keep class com.frogobox.ads.source.** { *; }
-keep class com.frogobox.ads.callback.** { *; }
-keep class com.frogobox.ads.util.** { *; }
