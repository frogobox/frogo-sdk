# Frogo UI RecyclerView — Full API Reference

Package: `com.frogobox.recycler`

## Widgets (`widget/`)

### FrogoRecyclerView
Enhanced RecyclerView with a built-in generic adapter system.

```kotlin
class FrogoRecyclerView : RecyclerView {
    // Create an injector for type T
    fun <T> injector(): IFrogoRecyclerView<T>
}
```

### IFrogoRecyclerView Interface
```kotlin
interface IFrogoRecyclerView<T> {
    fun addData(listData: List<T>): IFrogoRecyclerView<T>
    fun addCallback(callback: IFrogoViewHolder<T>): IFrogoRecyclerView<T>
    
    // Layout Managers
    fun createLayoutLinearVertical(reverseLayout: Boolean): IFrogoRecyclerView<T>
    fun createLayoutLinearHorizontal(reverseLayout: Boolean): IFrogoRecyclerView<T>
    fun createLayoutGrid(spanCount: Int): IFrogoRecyclerView<T>
    fun createLayoutStaggeredGrid(spanCount: Int): IFrogoRecyclerView<T>
    
    fun build(): IFrogoRecyclerView<T>
}
```

#### Usage Example:

```kotlin
// In Activity/Fragment
binding.frogoRecyclerView
    .injector<Article>()
    .addData(articleList)
    .addCallback(object : IFrogoViewHolder<Article> {
        override fun setupInitComponent(view: View, data: Article, position: Int) {
            val title = view.findViewById<TextView>(R.id.tv_title)
            val desc = view.findViewById<TextView>(R.id.tv_desc)
            title.text = data.title
            desc.text = data.description
        }
    })
    .createLayoutLinearVertical(false)
    .build()
```

#### With Custom Layout:

```kotlin
binding.frogoRecyclerView
    .injector<MyModel>()
    .addCustomView(R.layout.item_my_model)
    .addData(dataList)
    .addCallback(object : IFrogoViewHolder<MyModel> {
        override fun setupInitComponent(view: View, data: MyModel, position: Int) {
            // bind views
        }
    })
    .createLayoutLinearVertical(false)
    .build()
```

---

### FrogoProgressRecyclerView
RecyclerView with integrated progress indicator (ProgressBar overlay).

```kotlin
class FrogoProgressRecyclerView : FrameLayout {
    fun <T> injector(): IFrogoRecyclerView<T>
    fun showProgress()
    fun hideProgress()
}
```

### IFrogoProgressRecyclerView Interface
```kotlin
interface IFrogoProgressRecyclerView {
    fun showProgress()
    fun hideProgress()
}
```

#### Usage Example:

```kotlin
// Show loading state
binding.frogoProgressRv.showProgress()

// Load data
viewModel.articles.observe(this) { articles ->
    binding.frogoProgressRv.hideProgress()
    binding.frogoProgressRv
        .injector<Article>()
        .addData(articles)
        .addCallback(myCallback)
        .createLayoutLinearVertical(false)
        .build()
}
```

---

### FrogoShimmerRecyclerView
RecyclerView with shimmer placeholder animation during loading.

```kotlin
class FrogoShimmerRecyclerView : FrameLayout {
    fun <T> injector(): IFrogoRecyclerView<T>
    fun showShimmer()
    fun hideShimmer()
}
```

### IFrogoShimmerRecyclerView Interface
```kotlin
interface IFrogoShimmerRecyclerView {
    fun showShimmer()
    fun hideShimmer()
    fun defineShimmerView(shimmerViewId: Int): IFrogoShimmerRecyclerView
}
```

#### Usage Example:

```kotlin
// Show shimmer loading state
binding.frogoShimmerRv
    .defineShimmerView(R.layout.shimmer_item_article)
    .showShimmer()

// Load data
viewModel.articles.observe(this) { articles ->
    binding.frogoShimmerRv.hideShimmer()
    binding.frogoShimmerRv
        .injector<Article>()
        .addData(articles)
        .addCallback(myCallback)
        .createLayoutLinearVertical(false)
        .build()
}
```

---

### FrogoStyleComponent
Styling helper for RecyclerView item decorations and spacing.

```kotlin
class FrogoStyleComponent {
    fun addItemDecoration(decoration: RecyclerView.ItemDecoration)
    fun setHasFixedSize(hasFixedSize: Boolean)
}
```

---

## Core Classes (`core/`)

Internal adapter and ViewHolder implementations used by the widget classes.

## Extensions (`ext/`)

RecyclerView extension functions for simplified setup.

## Shimmer (`shimmer/`)

Shimmer effect implementation for loading states.

---

## XML Layout Usage

### FrogoRecyclerView
```xml
<com.frogobox.recycler.widget.FrogoRecyclerView
    android:id="@+id/frogoRecyclerView"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

### FrogoProgressRecyclerView
```xml
<com.frogobox.recycler.widget.FrogoProgressRecyclerView
    android:id="@+id/frogoProgressRv"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

### FrogoShimmerRecyclerView
```xml
<com.frogobox.recycler.widget.FrogoShimmerRecyclerView
    android:id="@+id/frogoShimmerRv"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```
