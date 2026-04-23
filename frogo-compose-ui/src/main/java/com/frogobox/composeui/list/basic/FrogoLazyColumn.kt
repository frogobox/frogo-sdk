package com.frogobox.composeui.list.basic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> FrogoLazyColumn(
    data: List<T>,
    modifier: Modifier = Modifier.fillMaxSize(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    key: ((T) -> Any)? = null,
    emptyContent: @Composable () -> Unit = {},
    itemContent: @Composable (index: Int, item: T) -> Unit
) {
    if (data.isEmpty()) {
        emptyContent()
    } else {
        LazyColumn(
            modifier = modifier,
            contentPadding = contentPadding,
            verticalArrangement = verticalArrangement
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
