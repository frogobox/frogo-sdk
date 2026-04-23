package com.frogobox.composeui.list.coil

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun FrogoCoilListItem(
    imageUrl: String,
    headlineText: String,
    modifier: Modifier = Modifier,
    supportingText: String? = null,
    overlineText: String? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    placeholder: Painter? = null,
    error: Painter? = null,
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
        leadingContent = {
            FrogoCoilImage(
                imageUrl = imageUrl,
                contentDescription = headlineText,
                modifier = Modifier.size(56.dp),
                placeholder = placeholder,
                error = error,
                shape = RoundedCornerShape(8.dp)
            )
        },
        trailingContent = trailingContent,
        colors = colors
    )
}
