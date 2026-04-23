package com.frogobox.composeui.list.basic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> FrogoLazyRow(
    data: List<T>,
    modifier: Modifier = Modifier.fillMaxWidth(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    key: ((T) -> Any)? = null,
    emptyContent: @Composable () -> Unit = {},
    itemContent: @Composable (index: Int, item: T) -> Unit
) {
    if (data.isEmpty()) {
        emptyContent()
    } else {
        LazyRow(
            modifier = modifier,
            contentPadding = contentPadding,
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
