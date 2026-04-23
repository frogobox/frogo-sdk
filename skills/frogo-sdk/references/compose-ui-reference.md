# Frogo Compose UI — Full API Reference

Package: `com.frogobox.composeui`

## Base Widgets (`widget/`)

All widgets are `@Composable` functions following Material Design 3.

### FrogoButton
```kotlin
@Composable
fun FrogoButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: ImageVector? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors()
)
```

### FrogoOutlinedButton
```kotlin
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

### FrogoTextField
```kotlin
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
```

### FrogoOutlinedTextField
```kotlin
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

### FrogoCard / FrogoElevatedCard
```kotlin
@Composable
fun FrogoCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
)

@Composable
fun FrogoElevatedCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
)
```

### FrogoCheckbox
```kotlin
@Composable
fun FrogoCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    enabled: Boolean = true
)
```

### FrogoRadioButton
```kotlin
@Composable
fun FrogoRadioButton(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    enabled: Boolean = true
)
```

### FrogoSwitch
```kotlin
@Composable
fun FrogoSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
)
```

### FrogoChip / FrogoFilterChip
```kotlin
@Composable
fun FrogoChip(label: String, modifier: Modifier = Modifier)

@Composable
fun FrogoFilterChip(
    selected: Boolean,
    onClick: () -> Unit,
    label: String,
    modifier: Modifier = Modifier
)
```

### FrogoBadge
```kotlin
@Composable
fun FrogoBadge(count: Int, modifier: Modifier = Modifier)
```

### FrogoAvatar
```kotlin
@Composable
fun FrogoAvatar(
    imageUrl: String? = null,
    initials: String? = null,
    modifier: Modifier = Modifier,
    size: Dp = 40.dp
)
```

### FrogoDivider / FrogoSpacer
```kotlin
@Composable
fun FrogoDivider(modifier: Modifier = Modifier, thickness: Dp = 1.dp)

@Composable
fun FrogoSpacer(width: Dp = 0.dp, height: Dp = 0.dp)
```

### FrogoIcon / FrogoIconButton
```kotlin
@Composable
fun FrogoIcon(
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier
)

@Composable
fun FrogoIconButton(
    onClick: () -> Unit,
    icon: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier
)
```

### FrogoImage
```kotlin
@Composable
fun FrogoImage(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
)
```

### FrogoFloatingActionButton
```kotlin
@Composable
fun FrogoFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    text: String? = null
)
```

### FrogoCircularProgress / FrogoLinearProgress
```kotlin
@Composable
fun FrogoCircularProgress(modifier: Modifier = Modifier, progress: Float? = null)

@Composable
fun FrogoLinearProgress(modifier: Modifier = Modifier, progress: Float? = null)
```

### FrogoSearchBar
```kotlin
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FrogoSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    active: Boolean,
    onActiveChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth(),
    placeholder: String = "Search...",
    content: @Composable () -> Unit
)
```

---

## Templates (`template/`)

### App Bars (`template/appbar/`)

```kotlin
// Standard Top App Bar
FrogoTopAppBar(title: String, navigationIcon: @Composable (() -> Unit)? = null, actions: @Composable RowScope.() -> Unit = {})

// Center-aligned Top App Bar
FrogoCenterTopAppBar(title: String, ...)

// Medium Top App Bar (scrollable)
FrogoMediumTopAppBar(title: String, scrollBehavior: TopAppBarScrollBehavior? = null, ...)

// Large Top App Bar (scrollable)
FrogoLargeTopAppBar(title: String, scrollBehavior: TopAppBarScrollBehavior? = null, ...)

// Search-integrated Top App Bar
FrogoSearchTopAppBar(query: String, onQueryChange: (String) -> Unit, onSearch: (String) -> Unit, ...)

// Bottom App Bar
FrogoBottomAppBar(content: @Composable RowScope.() -> Unit)
```

### Bottom Sheets (`template/bottomsheet/`)

```kotlin
// Basic Modal Bottom Sheet
FrogoBottomSheet(onDismissRequest: () -> Unit, ...)

// List-based Bottom Sheet
FrogoListBottomSheet(items: List<String>, onItemClick: (Int, String) -> Unit, ...)

// Menu Bottom Sheet with icons
FrogoMenuBottomSheet(menuItems: List<FrogoMenuItem>, onItemClick: (FrogoMenuItem) -> Unit, ...)
```

### Dialogs (`template/dialog/`)

```kotlin
// Alert Dialog
FrogoAlertDialog(onDismissRequest, onConfirmation, dialogTitle, dialogText, ...)

// Confirm Dialog
FrogoConfirmDialog(onDismissRequest, onConfirmation, dialogTitle, dialogText, ...)

// Image Dialog
FrogoImageDialog(onDismissRequest, painter, contentDescription, ...)

// Input Dialog
FrogoInputDialog(onDismissRequest, onConfirmation, dialogTitle, label, value, onValueChange, ...)

// Loading Dialog
FrogoLoadingDialog(onDismissRequest, dialogTitle, ...)
```

### Navigation (`template/navigation/`)

```kotlin
// Bottom Navigation Bar
FrogoNavigationBar(modifier, content: @Composable RowScope.() -> Unit)

// Navigation Bar Item
RowScope.FrogoNavigationBarItem(selected, onClick, icon, label, ...)

// Navigation Drawer
FrogoNavigationDrawer(drawerState, drawerContent, content)

// Navigation Rail
FrogoNavigationRail(content: @Composable ColumnScope.() -> Unit)
```

### Scaffolds (`template/scaffold/`)

```kotlin
// Basic Scaffold
FrogoScaffold(modifier, topBar, bottomBar, snackbarHost, floatingActionButton, floatingActionButtonPosition, content: (PaddingValues) -> Unit)

// Scaffold with pre-configured FAB
FrogoScaffoldWithFab(...)
```

### Other Templates

```kotlin
// Shimmer loading effect
FrogoShimmerEffect(modifier)
FrogoShimmerItem(modifier)

// Snackbar
FrogoSnackbar(snackbarHostState, message, actionLabel, ...)

// Tab Rows
FrogoTabRow(selectedTabIndex, tabs: List<String>, onTabSelected: (Int) -> Unit)
FrogoScrollableTabRow(selectedTabIndex, tabs: List<String>, onTabSelected: (Int) -> Unit)

// Empty State
FrogoEmptyState(title, description, icon, actionButtonText, onActionClick)
```

---

## List Components (`list/`)

### Basic Lists (`list/basic/`)

```kotlin
// Vertical scrolling list
fun <T> FrogoLazyColumn(
    data: List<T>,
    modifier: Modifier = Modifier.fillMaxSize(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    key: ((T) -> Any)? = null,
    emptyContent: @Composable () -> Unit = {},
    itemContent: @Composable (index: Int, item: T) -> Unit
)

// Horizontal scrolling list
fun <T> FrogoLazyRow(data, modifier, contentPadding, key, emptyContent, itemContent)

// Vertical grid
fun <T> FrogoLazyVerticalGrid(data, columns: Int, modifier, contentPadding, key, emptyContent, itemContent)

// Staggered grid
fun <T> FrogoLazyVerticalStaggeredGrid(data, columns: Int, modifier, contentPadding, key, emptyContent, itemContent)

// Standard list item
FrogoListItem(headlineText, supportingText, overlineText, leadingContent, trailingContent, onClick, colors)
```

### Coil Image Lists (`list/coil/`)

```kotlin
FrogoCoilImage(imageUrl, contentDescription, modifier, placeholder, error, shape)
FrogoCoilLazyColumn(data, modifier, contentPadding, key, emptyContent, itemContent)
FrogoCoilLazyRow(data, modifier, contentPadding, key, emptyContent, itemContent)
FrogoCoilLazyVerticalGrid(data, columns, modifier, contentPadding, key, emptyContent, itemContent)
FrogoCoilListItem(imageUrl, headlineText, supportingText, overlineText, trailingContent, placeholder, error, onClick, colors)
```

### Glide Image Lists (`list/glide/`)

```kotlin
FrogoGlideImage(imageUrl, contentDescription, modifier, placeholder, error, shape)
FrogoGlideLazyColumn(data, modifier, contentPadding, key, emptyContent, itemContent)
FrogoGlideLazyRow(data, modifier, contentPadding, key, emptyContent, itemContent)
FrogoGlideLazyVerticalGrid(data, columns, modifier, contentPadding, key, emptyContent, itemContent)
FrogoGlideListItem(imageUrl, headlineText, supportingText, overlineText, trailingContent, placeholder, error, onClick, colors)
```
