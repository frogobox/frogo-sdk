package com.frogobox.composeui.template.bottomsheet

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> FrogoListBottomSheet(
    onDismissRequest: () -> Unit,
    items: List<T>,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    itemContent: @Composable (index: Int, item: T) -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        sheetState = sheetState
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            itemsIndexed(items) { index, item ->
                itemContent(index, item)
            }
        }
    }
}
