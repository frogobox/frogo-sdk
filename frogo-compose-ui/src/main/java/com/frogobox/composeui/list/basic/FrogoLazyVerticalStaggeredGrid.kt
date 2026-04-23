package com.frogobox.composeui.list.basic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> FrogoLazyVerticalStaggeredGrid(
    data: List<T>,
    spanCount: Int,
    modifier: Modifier = Modifier.fillMaxSize(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    verticalItemSpacing: androidx.compose.ui.unit.Dp = 0.dp,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    key: ((T) -> Any)? = null,
    emptyContent: @Composable () -> Unit = {},
    itemContent: @Composable (index: Int, item: T) -> Unit
) {
    if (data.isEmpty()) {
        emptyContent()
    } else {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(spanCount),
            modifier = modifier,
            contentPadding = contentPadding,
            verticalItemSpacing = verticalItemSpacing,
            horizontalArrangement = horizontalArrangement
        ) {
            itemsIndexed(
                items = data,
                key = if (key != null) { index, item -> key(item) } else null
            ) { index, item ->
                itemContent(index, item)
            }
        }
    }
}
