package com.frogobox.composeui.list.glide

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.frogobox.composeui.list.basic.FrogoLazyRow

@Composable
fun <T> FrogoGlideLazyRow(
    data: List<T>,
    modifier: Modifier = Modifier.fillMaxWidth(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    key: ((T) -> Any)? = null,
    emptyContent: @Composable () -> Unit = {},
    itemContent: @Composable (index: Int, item: T) -> Unit
) {
    FrogoLazyRow(
        data = data,
        modifier = modifier,
        contentPadding = contentPadding,
        horizontalArrangement = horizontalArrangement,
        key = key,
        emptyContent = emptyContent,
        itemContent = itemContent
    )
}
