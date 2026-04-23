package com.frogobox.appcomposeui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.frogobox.composeui.list.basic.FrogoLazyColumn
import com.frogobox.composeui.list.basic.FrogoListItem
import com.frogobox.composeui.widget.FrogoSpacerHeight

@Composable
fun ListScreen() {
    val sampleData = List(20) { "Item #$it" }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Basic List Example",
            modifier = Modifier.padding(16.dp)
        )
        FrogoSpacerHeight(8.dp)

        FrogoLazyColumn(
            data = sampleData,
            modifier = Modifier.fillMaxSize(),
            itemContent = { index, item ->
                FrogoListItem(
                    headlineText = item,
                    supportingText = "This is the supporting text for $item",
                    onClick = { /* TODO Handle click */ }
                )
            }
        )
    }
}
