package com.frogobox.composeui.list.glide

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.frogobox.composeui.list.basic.FrogoLazyVerticalGrid

@Composable
fun <T> FrogoGlideLazyVerticalGrid(
    data: List<T>,
    spanCount: Int,
    modifier: Modifier = Modifier.fillMaxSize(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    key: ((T) -> Any)? = null,
    emptyContent: @Composable () -> Unit = {},
    itemContent: @Composable (index: Int, item: T) -> Unit
) {
    FrogoLazyVerticalGrid(
        data = data,
        spanCount = spanCount,
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = verticalArrangement,
        horizontalArrangement = horizontalArrangement,
        key = key,
        emptyContent = emptyContent,
        itemContent = itemContent
    )
}
