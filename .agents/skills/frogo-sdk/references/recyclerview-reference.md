# Frogo UI RecyclerView — Full API Reference (v3.0.8)

Package: `com.frogobox.recycler`  
Artifact: `com.github.frogobox.frogo-sdk:frogo-ui-recyclerview:3.0.8`

---

## 1. Core Widgets

| Widget | Purpose |
| :--- | :--- |
| `FrogoRecyclerView` | High-performance RecyclerView with built-in adapter builder pattern |
| `FrogoProgressRecyclerView` | RecyclerView with integrated center ProgressBar loading state |
| `FrogoShimmerRecyclerView` | RecyclerView with integrated shimmer skeleton loading placeholder |

---

## 2. Builder API Usage (`injector()`)

The `injector<T>()` fluent API eliminates boilerplate adapters and viewholders:

```kotlin
package com.example.app.ui

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.app.databinding.ItemUserBinding
import com.frogobox.recycler.core.IFrogoViewHolder
import com.frogobox.recycler.widget.FrogoRecyclerView

class UserActivity : AppCompatActivity() {

    private fun setupRecyclerView(rv: FrogoRecyclerView, users: List<User>) {
        rv.injector<User>()
            .addData(users)
            .addCallback(object : IFrogoViewHolder<User> {
                override fun setupInitComponent(view: View, data: User, position: Int) {
                    val binding = ItemUserBinding.bind(view)
                    binding.tvName.text = data.name
                    binding.tvEmail.text = data.email
                }
            })
            .createLayoutLinearVertical(dividerItem = false)
            .build()
    }
}
```

### Layout Managers Supported in Builder:
- `.createLayoutLinearVertical(dividerItem: Boolean)`
- `.createLayoutLinearHorizontal(dividerItem: Boolean)`
- `.createLayoutGrid(spanCount: Int)`
- `.createLayoutStaggeredGrid(spanCount: Int)`
