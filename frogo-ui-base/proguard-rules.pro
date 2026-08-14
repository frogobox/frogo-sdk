# Frogo UI Base ProGuard Rules
# Preserve indicator classes instantiated via reflection in FrogoLoadingIndicatorView
-keep public class com.frogobox.ui.loadingindicator.indicators.** extends com.frogobox.ui.loadingindicator.Indicator {
    public <init>();
}