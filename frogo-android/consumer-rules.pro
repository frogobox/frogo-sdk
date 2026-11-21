# Frogo Android Consumer ProGuard Rules
# R8 and consumer dependencies (Gson, Kotlin Parcelize, Room, AndroidX) provide built-in keep rules.

# Preserve indicator classes instantiated via reflection in FrogoLoadingIndicatorView
-keep public class com.frogobox.ui.loadingindicator.indicators.** extends com.frogobox.ui.loadingindicator.Indicator {
    public <init>();
}
