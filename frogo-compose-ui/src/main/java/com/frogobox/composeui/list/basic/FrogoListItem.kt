package com.frogobox.composeui.list.basic

import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FrogoListItem(
    headlineText: String,
    modifier: Modifier = Modifier,
    supportingText: String? = null,
    overlineText: String? = null,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
    colors: ListItemColors = ListItemDefaults.colors()
) {
    val clickableModifier = if (onClick != null) {
        Modifier.clickable { onClick() }
    } else {
        Modifier
    }

    ListItem(
        headlineContent = { Text(text = headlineText) },
        modifier = modifier.then(clickableModifier),
        supportingContent = supportingText?.let { { Text(text = it) } },
        overlineContent = overlineText?.let { { Text(text = it) } },
        leadingContent = leadingContent,
        trailingContent = trailingContent,
        colors = colors
    )
}
