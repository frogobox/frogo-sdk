package com.frogobox.composeui.list.basic

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
 * Created by Faisal Amir
 * Compose equivalents of frogo_rv_grid_type_1..7.xml
 *
 * Grid item styling mirrors FrogoLayoutGrid8dp:
 *  - match_parent width, 16dp margin end/bottom, 2dp elevation, 16dp padding, card_8dp bg
 *
 * Image types:
 *  - Types 1–3: Regular rectangular ImageView (full-width, 150dp height, centerCrop)
 *  - Types 4–6: CircleImageView (full-width, 150dp height, centerCrop)
 *  - Type 7: Regular rectangular ImageView (full-width, 150dp height, no bottom margin)
 */

// region Internal grid card wrapper

@Composable
private fun FrogoGridCardContainer(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val cardShape = RoundedCornerShape(8.dp)
    val widthModifier = Modifier
        .fillMaxWidth()
        .padding(end = 16.dp, bottom = 16.dp)

    val boxModifier = widthModifier
        .shadow(elevation = 2.dp, shape = cardShape)
        .clip(cardShape)
        .background(
            color = MaterialTheme.colorScheme.surface,
            shape = cardShape
        )
        .then(
            if (onClick != null) {
                Modifier.clickable { onClick() }
            } else {
                Modifier
            }
        )
        .padding(16.dp)
        .then(modifier)

    Box(modifier = boxModifier) {
        content()
    }
}

@Composable
private fun GridTitleText(text: String) {
    Text(
        text = text,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = MaterialTheme.colorScheme.onSurface
    )
}

@Composable
private fun GridSubtitleText(text: String) {
    Text(
        text = text,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontSize = 12.sp,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
private fun GridDescriptionText(text: String) {
    Text(
        text = text,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
        fontSize = 11.sp,
        color = MaterialTheme.colorScheme.onSurface
    )
}

// endregion

// region Type 1: Image + Title

/**
 * Equivalent of frogo_rv_grid_type_1.xml
 * Grid card with full-width 150dp image and title.
 */
@Composable
fun FrogoRvGridType1(
    title: String,
    image: Painter,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    FrogoGridCardContainer(onClick = onClick, modifier = modifier) {
        Column {
            Image(
                painter = image,
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            GridTitleText(text = title)
        }
    }
}

// endregion

// region Type 2: Image + Title + Subtitle

/**
 * Equivalent of frogo_rv_grid_type_2.xml
 * Grid card with full-width 150dp image, title, and subtitle.
 */
@Composable
fun FrogoRvGridType2(
    title: String,
    subtitle: String,
    image: Painter,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    FrogoGridCardContainer(onClick = onClick, modifier = modifier) {
        Column {
            Image(
                painter = image,
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            GridTitleText(text = title)
            GridSubtitleText(text = subtitle)
        }
    }
}

// endregion

// region Type 3: Image + Title + Subtitle + Description

/**
 * Equivalent of frogo_rv_grid_type_3.xml
 * Grid card with full-width 150dp image, title, subtitle, and description.
 */
@Composable
fun FrogoRvGridType3(
    title: String,
    subtitle: String,
    description: String,
    image: Painter,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    FrogoGridCardContainer(onClick = onClick, modifier = modifier) {
        Column {
            Image(
                painter = image,
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            GridTitleText(text = title)
            GridSubtitleText(text = subtitle)
            Spacer(modifier = Modifier.height(8.dp))
            GridDescriptionText(text = description)
        }
    }
}

// endregion

// region Type 4: Circle Image + Title

/**
 * Equivalent of frogo_rv_grid_type_4.xml
 * Grid card with circular 150dp image and title.
 */
@Composable
fun FrogoRvGridType4(
    title: String,
    image: Painter,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    FrogoGridCardContainer(onClick = onClick, modifier = modifier) {
        Column {
            Image(
                painter = image,
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            GridTitleText(text = title)
        }
    }
}

// endregion

// region Type 5: Circle Image + Title + Subtitle

/**
 * Equivalent of frogo_rv_grid_type_5.xml
 * Grid card with circular 150dp image, title, and subtitle.
 */
@Composable
fun FrogoRvGridType5(
    title: String,
    subtitle: String,
    image: Painter,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    FrogoGridCardContainer(onClick = onClick, modifier = modifier) {
        Column {
            Image(
                painter = image,
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            GridTitleText(text = title)
            GridSubtitleText(text = subtitle)
        }
    }
}

// endregion

// region Type 6: Circle Image + Title + Subtitle + Description

/**
 * Equivalent of frogo_rv_grid_type_6.xml
 * Grid card with circular 150dp image, title, subtitle, and description.
 */
@Composable
fun FrogoRvGridType6(
    title: String,
    subtitle: String,
    description: String,
    image: Painter,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    FrogoGridCardContainer(onClick = onClick, modifier = modifier) {
        Column {
            Image(
                painter = image,
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            GridTitleText(text = title)
            GridSubtitleText(text = subtitle)
            Spacer(modifier = Modifier.height(8.dp))
            GridDescriptionText(text = description)
        }
    }
}

// endregion

// region Type 7: Image only (no text)

/**
 * Equivalent of frogo_rv_grid_type_7.xml
 * Grid card with full-width 150dp image only (no text).
 */
@Composable
fun FrogoRvGridType7(
    image: Painter,
    contentDescription: String = "",
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    FrogoGridCardContainer(onClick = onClick, modifier = modifier) {
        Image(
            painter = image,
            contentDescription = contentDescription,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop
        )
    }
}

// endregion
