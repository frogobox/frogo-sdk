# Frogo Compose UI Consumer ProGuard Rules
# Preserve indicator classes instantiated via reflection in FrogoLoadingIndicatorView
-keep public class com.frogobox.composeui.loadingindicator.indicators.** extends com.frogobox.composeui.loadingindicator.Indicator {
    public <init>();
}
