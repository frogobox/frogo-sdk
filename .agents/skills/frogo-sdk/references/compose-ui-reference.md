# Frogo Compose UI — Full API Reference (v3.0.8)

Package: `com.frogobox.composeui`  
Artifact: `com.github.frogobox.frogo-sdk:frogo-compose-ui:3.0.8`  
Dependencies:
- `androidx.compose:compose-bom:2026.09.00` (Material Design 3)
- `io.coil-kt.coil3:coil-compose:3.6.2` & `io.coil-kt.coil3:coil-network-okhttp:3.6.2`
- `com.github.bumptech.glide:compose:1.0.0-beta10`

---

## 1. Base Widgets (`com.frogobox.composeui.widget.*`)

All widgets are `@Composable` functions built on Material Design 3 components and tokens.

### FrogoButton & FrogoOutlinedButton
```kotlin
import com.frogobox.composeui.widget.button.FrogoButton
import com.frogobox.composeui.widget.button.FrogoOutlinedButton

@Composable
fun FrogoButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: ImageVector? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors()
)

@Composable
fun FrogoOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: ImageVector? = null,
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors()
)
```

### FrogoTextField & FrogoOutlinedTextField
```kotlin
import com.frogobox.composeui.widget.textfield.FrogoTextField
import com.frogobox.composeui.widget.textfield.FrogoOutlinedTextField

@Composable
fun FrogoTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    isError: Boolean = false,
    singleLine: Boolean = true
)

@Composable
fun FrogoOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    isError: Boolean = false,
    singleLine: Boolean = true
)
```

### Cards, Selection Controls, Indicators
```kotlin
import com.frogobox.composeui.widget.card.FrogoCard
import com.frogobox.composeui.widget.card.FrogoElevatedCard
import com.frogobox.composeui.widget.checkbox.FrogoCheckbox
import com.frogobox.composeui.widget.radiobutton.FrogoRadioButton
import com.frogobox.composeui.widget.switch.FrogoSwitch
import com.frogobox.composeui.widget.chip.FrogoChip
import com.frogobox.composeui.widget.chip.FrogoFilterChip
import com.frogobox.composeui.widget.badge.FrogoBadge
import com.frogobox.composeui.widget.avatar.FrogoAvatar
import com.frogobox.composeui.widget.divider.FrogoDivider
import com.frogobox.composeui.widget.spacer.FrogoSpacer
import com.frogobox.composeui.widget.icon.FrogoIcon
import com.frogobox.composeui.widget.icon.FrogoIconButton
import com.frogobox.composeui.widget.image.FrogoImage
import com.frogobox.composeui.widget.fab.FrogoFloatingActionButton
import com.frogobox.composeui.widget.progress.FrogoCircularProgress
import com.frogobox.composeui.widget.progress.FrogoLinearProgress
import com.frogobox.composeui.widget.searchbar.FrogoSearchBar
```

---

## 2. Templates (`com.frogobox.composeui.template.*`)

### App Bars (`com.frogobox.composeui.template.appbar.*`)
```kotlin
import com.frogobox.composeui.template.appbar.FrogoTopAppBar
import com.frogobox.composeui.template.appbar.FrogoCenterTopAppBar
import com.frogobox.composeui.template.appbar.FrogoMediumTopAppBar
import com.frogobox.composeui.template.appbar.FrogoLargeTopAppBar
import com.frogobox.composeui.template.appbar.FrogoSearchTopAppBar
import com.frogobox.composeui.template.appbar.FrogoBottomAppBar

// Example: FrogoTopAppBar
FrogoTopAppBar(
    title = "Screen Title",
    navigationIcon = { /* icon */ },
    actions = { /* action buttons */ }
)
```

### Bottom Sheets (`com.frogobox.composeui.template.bottomsheet.*`)
```kotlin
import com.frogobox.composeui.template.bottomsheet.FrogoBottomSheet
import com.frogobox.composeui.template.bottomsheet.FrogoListBottomSheet
import com.frogobox.composeui.template.bottomsheet.FrogoMenuBottomSheet
```

### Dialogs (`com.frogobox.composeui.template.dialog.*`)
```kotlin
import com.frogobox.composeui.template.dialog.FrogoAlertDialog
import com.frogobox.composeui.template.dialog.FrogoConfirmDialog
import com.frogobox.composeui.template.dialog.FrogoImageDialog
import com.frogobox.composeui.template.dialog.FrogoInputDialog
import com.frogobox.composeui.template.dialog.FrogoLoadingDialog
```

### Navigation & Scaffolds (`com.frogobox.composeui.template.*`)
```kotlin
import com.frogobox.composeui.template.navigation.FrogoNavigationBar
import com.frogobox.composeui.template.navigation.FrogoNavigationBarItem
import com.frogobox.composeui.template.navigation.FrogoNavigationDrawer
import com.frogobox.composeui.template.navigation.FrogoNavigationRail
import com.frogobox.composeui.template.scaffold.FrogoScaffold
import com.frogobox.composeui.template.scaffold.FrogoScaffoldWithFab
import com.frogobox.composeui.template.shimmer.FrogoShimmerEffect
import com.frogobox.composeui.template.snackbar.FrogoSnackbar
import com.frogobox.composeui.template.tab.FrogoTabRow
import com.frogobox.composeui.template.empty.FrogoEmptyState
```

---

## 3. List Components (`com.frogobox.composeui.list.*`)

### Basic Lists (`com.frogobox.composeui.list.basic.*`)
```kotlin
import com.frogobox.composeui.list.basic.FrogoLazyColumn
import com.frogobox.composeui.list.basic.FrogoLazyRow
import com.frogobox.composeui.list.basic.FrogoLazyVerticalGrid
import com.frogobox.composeui.list.basic.FrogoLazyVerticalStaggeredGrid
import com.frogobox.composeui.list.basic.FrogoListItem

FrogoLazyColumn(
    data = items,
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(16.dp),
    emptyContent = { FrogoEmptyState(title = "Empty List") }
) { index, item ->
    FrogoListItem(
        headlineText = item.title,
        supportingText = item.description,
        onClick = { /* on click */ }
    )
}
```

### Coil 3 Image Lists (`com.frogobox.composeui.list.coil.*`)
> [!NOTE]
> Powered by **Coil 3** (`io.coil-kt.coil3:coil-compose:3.6.2`). The underlying engine uses `coil3.compose.AsyncImage`.
```kotlin
import com.frogobox.composeui.list.coil.FrogoCoilImage
import com.frogobox.composeui.list.coil.FrogoCoilLazyColumn
import com.frogobox.composeui.list.coil.FrogoCoilLazyRow
import com.frogobox.composeui.list.coil.FrogoCoilLazyVerticalGrid
import com.frogobox.composeui.list.coil.FrogoCoilListItem

FrogoCoilLazyColumn(data = photoList) { index, photo ->
    FrogoCoilListItem(
        imageUrl = photo.url,
        headlineText = photo.title,
        supportingText = photo.description,
        onClick = { /* on click */ }
    )
}
```

### Glide Compose Image Lists (`com.frogobox.composeui.list.glide.*`)
```kotlin
import com.frogobox.composeui.list.glide.FrogoGlideImage
import com.frogobox.composeui.list.glide.FrogoGlideLazyColumn
import com.frogobox.composeui.list.glide.FrogoGlideListItem
```

---

## 4. Animations & Transitions (`com.frogobox.composeui.animation.*`)

### Attention & Entrance Animations
```kotlin
import com.frogobox.composeui.animation.FrogoAnimationComposeType
import com.frogobox.composeui.animation.frogoAnimationCompose

Text(
    text = "Attention",
    modifier = Modifier.frogoAnimationCompose(
        type = FrogoAnimationComposeType.Bounce, // Bounce, Flash, Pulse, Rubberband, Shake, Swing, Tada, Wobble
        trigger = Unit,
        durationMillis = 800,
        repeat = true
    )
)
```

### Compose Navigation Transition Specs
`FrogoSingleAnimationCompose` provides `EnterTransition` / `ExitTransition` specs:
```kotlin
import com.frogobox.composeui.animation.FrogoSingleAnimationCompose

// In Compose Navigation NavHost enterTransition / exitTransition:
composable(
    route = "details",
    enterTransition = { FrogoSingleAnimationCompose.slideLeftEnter() },
    exitTransition = { FrogoSingleAnimationCompose.slideLeftExit() }
)
```

---

## 5. Interactive Fireworks Particle System (`com.frogobox.composeui.fireworks.*`)

Canvas-based high-performance particle explosion system:
```kotlin
import com.frogobox.composeui.fireworks.FrogoFireworksCompose
import com.frogobox.composeui.fireworks.rememberFrogoFireworksStateCompose
import com.frogobox.composeui.ext.frogoClickWithFireworksCompose

val fireworksState = rememberFrogoFireworksStateCompose()

Box(modifier = Modifier.fillMaxSize()) {
    Button(
        onClick = {},
        modifier = Modifier.frogoClickWithFireworksCompose(fireworksState) {
            // Your action when tapped
        }
    ) {
        Text("Explode Fireworks")
    }

    FrogoFireworksCompose(state = fireworksState, modifier = Modifier.fillMaxSize())
}
```

---

## 6. Lightweight Canvas Loading Indicators (`com.frogobox.composeui.loadingindicator.*`)

```kotlin
import com.frogobox.composeui.loadingindicator.FrogoLoadingIndicatorCompose

FrogoLoadingIndicatorCompose(
    indicatorName = "Pacman", // Supported: Pacman, BallPulse, BallClipRotate, BallScale, LineScale
    color = MaterialTheme.colorScheme.primary,
    size = 48.dp
)
```
