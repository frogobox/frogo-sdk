# Frogo Compose Consumer ProGuard Rules
# Jetpack Compose and R8 automatically optimize and preserve Composable functions and models.

# Preserve indicator classes instantiated via reflection in FrogoLoadingIndicatorView
-keep public class com.frogobox.composeui.loadingindicator.indicators.** extends com.frogobox.composeui.loadingindicator.Indicator {
    public <init>();
}
