package com.frogobox.composeui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun FrogoAvatar(
    painter: Painter? = null,
    initialText: String = "?",
    modifier: Modifier = Modifier,
    size: Dp = 40.dp,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        if (painter != null) {
            Image(
                painter = painter,
                contentDescription = "Avatar",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } else {
            Text(
                text = initialText.take(1).uppercase(),
                color = contentColor,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

/**
 * Avatar Composable powered by Coil 3 for network image loading with automatic fallback to text initials.
 *
 * @param imageUrl The URL of the avatar image.
 * @param modifier The layout modifier.
 * @param initialText Fallback text/initial to display if the image is blank or fails to load.
 * @param contentDescription Accessibility description.
 * @param size The diameter of the circular avatar.
 * @param placeholder Optional placeholder painter while loading.
 * @param error Optional error painter if image load fails.
 * @param backgroundColor Background color behind the image or initial text.
 * @param contentColor Color for the fallback initial text.
 */
@Composable
fun FrogoCoilAvatar(
    imageUrl: String,
    modifier: Modifier = Modifier,
    initialText: String = "?",
    contentDescription: String? = "Avatar",
    size: Dp = 40.dp,
    placeholder: Painter? = null,
    error: Painter? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer
) {
    var isError by remember(imageUrl) {
        mutableStateOf(imageUrl.isBlank())
    }

    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        if (!isError && imageUrl.isNotBlank()) {
            com.frogobox.composeui.list.coil.FrogoCoilImage(
                imageUrl = imageUrl,
                contentDescription = contentDescription,
                modifier = Modifier.fillMaxSize(),
                placeholder = placeholder,
                error = error,
                contentScale = ContentScale.Crop,
                shape = CircleShape,
                onError = { isError = true }
            )
        } else {
            Text(
                text = initialText.take(1).uppercase(),
                color = contentColor,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

