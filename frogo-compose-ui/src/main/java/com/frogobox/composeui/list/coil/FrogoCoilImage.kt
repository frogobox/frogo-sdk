package com.frogobox.composeui.list.coil

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.draw.clip
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade

@Composable
fun FrogoCoilImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    placeholder: Painter? = null,
    error: Painter? = null,
    contentScale: ContentScale = ContentScale.Crop,
    crossfadeEnabled: Boolean = true,
    crossfadeDuration: Int = 300,
    shape: Shape = RectangleShape,
    onLoading: (() -> Unit)? = null,
    onSuccess: (() -> Unit)? = null,
    onError: (() -> Unit)? = null
) {
    val context = LocalContext.current
    val request = ImageRequest.Builder(context)
        .data(imageUrl)
        .crossfade(crossfadeEnabled)
        .apply {
            if (crossfadeEnabled) {
                crossfade(crossfadeDuration)
            }
        }
        .build()

    AsyncImage(
        model = request,
        contentDescription = contentDescription,
        modifier = modifier.clip(shape),
        placeholder = placeholder,
        error = error,
        contentScale = contentScale,
        onLoading = { onLoading?.invoke() },
        onSuccess = { onSuccess?.invoke() },
        onError = { onError?.invoke() }
    )
}
