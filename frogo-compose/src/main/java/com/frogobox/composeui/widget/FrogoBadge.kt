package com.frogobox.composeui.widget

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FrogoBadge(
    count: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    BadgedBox(
        badge = {
            if (count > 0) {
                Badge {
                    Text(text = count.toString())
                }
            }
        },
        modifier = modifier
    ) {
        content()
    }
}
