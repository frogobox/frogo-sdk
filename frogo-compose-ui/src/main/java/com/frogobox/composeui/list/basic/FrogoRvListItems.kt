package com.frogobox.composeui.list.basic

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
 * Compose equivalents of frogo_rv_list_type_1..12.xml
 *
 * Each composable mirrors the exact structure and styling of its XML counterpart:
 *  - FrogoLayoutList8dp:   match_parent width, 16dp margin start/end/bottom, 2dp elevation, 16dp padding, card_8dp bg
 *  - FrogoLayoutListWrap8dp: wrap_content width, same margins/elevation/padding/bg
 *  - frogoTvListTitle:     maxLines=1, bold, 16sp, primaryDark color
 *  - FrogoTvListSubTitle:  maxLines=1, 12sp
 *  - FrogoTvListDescription: maxLines=3, 11sp, text color
 */

// endregion

// region Composable helper for list card wrapper

@Composable
private fun FrogoListCardContainer(
    modifier: Modifier = Modifier,
    fillWidth: Boolean = true,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val cardShape = RoundedCornerShape(8.dp)
    val widthModifier = if (fillWidth) {
        Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    } else {
        Modifier
            .wrapContentWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    }

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
private fun TitleText(text: String) {
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
private fun SubtitleText(text: String) {
    Text(
        text = text,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontSize = 12.sp,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
private fun DescriptionText(text: String) {
    Text(
        text = text,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
        fontSize = 11.sp,
        color = MaterialTheme.colorScheme.onSurface
    )
}

// endregion

// region Type 1: Title only

/**
 * Equivalent of frogo_rv_list_type_1.xml
 * Card with title text only.
 */
@Composable
fun FrogoRvListType1(
    title: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    FrogoListCardContainer(onClick = onClick, modifier = modifier) {
        TitleText(text = title)
    }
}

// endregion

// region Type 2: Title + Subtitle

/**
 * Equivalent of frogo_rv_list_type_2.xml
 * Card with title and subtitle.
 */
@Composable
fun FrogoRvListType2(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    FrogoListCardContainer(onClick = onClick, modifier = modifier) {
        Column {
            TitleText(text = title)
            SubtitleText(text = subtitle)
        }
    }
}

// endregion

// region Type 3: Title + Subtitle + Description

/**
 * Equivalent of frogo_rv_list_type_3.xml
 * Card with title, subtitle, and description.
 */
@Composable
fun FrogoRvListType3(
    title: String,
    subtitle: String,
    description: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    FrogoListCardContainer(onClick = onClick, modifier = modifier) {
        Column {
            TitleText(text = title)
            SubtitleText(text = subtitle)
            Spacer(modifier = Modifier.height(8.dp))
            DescriptionText(text = description)
        }
    }
}

// endregion

// region Type 4: Image (48dp square) + Title

/**
 * Equivalent of frogo_rv_list_type_4.xml
 * Card with 48dp square image and title.
 */
@Composable
fun FrogoRvListType4(
    title: String,
    image: Painter,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    FrogoListCardContainer(onClick = onClick, modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = image,
                contentDescription = title,
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            TitleText(text = title)
        }
    }
}

// endregion

// region Type 5: Image (48dp square) + Title + Subtitle

/**
 * Equivalent of frogo_rv_list_type_5.xml
 * Card with 48dp square image, title, and subtitle.
 */
@Composable
fun FrogoRvListType5(
    title: String,
    subtitle: String,
    image: Painter,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    FrogoListCardContainer(onClick = onClick, modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = image,
                contentDescription = title,
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                TitleText(text = title)
                SubtitleText(text = subtitle)
            }
        }
    }
}

// endregion

// region Type 6: Image (72x96dp) + Title + Subtitle + Description

/**
 * Equivalent of frogo_rv_list_type_6.xml
 * Card with 72x96dp rectangular image, title, subtitle, and description.
 */
@Composable
fun FrogoRvListType6(
    title: String,
    subtitle: String,
    description: String,
    image: Painter,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    FrogoListCardContainer(onClick = onClick, modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = image,
                contentDescription = title,
                modifier = Modifier
                    .size(width = 72.dp, height = 96.dp)
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                TitleText(text = title)
                SubtitleText(text = subtitle)
                Spacer(modifier = Modifier.height(8.dp))
                DescriptionText(text = description)
            }
        }
    }
}

// endregion

// region Type 7: Circle Image (48dp) + Title

/**
 * Equivalent of frogo_rv_list_type_7.xml
 * Card with 48dp circular image and title.
 */
@Composable
fun FrogoRvListType7(
    title: String,
    image: Painter,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    FrogoListCardContainer(onClick = onClick, modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = image,
                contentDescription = title,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            TitleText(text = title)
        }
    }
}

// endregion

// region Type 8: Circle Image (48dp) + Title + Subtitle

/**
 * Equivalent of frogo_rv_list_type_8.xml
 * Card with 48dp circular image, title, and subtitle.
 */
@Composable
fun FrogoRvListType8(
    title: String,
    subtitle: String,
    image: Painter,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    FrogoListCardContainer(onClick = onClick, modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = image,
                contentDescription = title,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                TitleText(text = title)
                SubtitleText(text = subtitle)
            }
        }
    }
}

// endregion

// region Type 9: Full-width Image (150dp) + Title

/**
 * Equivalent of frogo_rv_list_type_9.xml
 * Card with full-width 150dp tall image on top, title below.
 */
@Composable
fun FrogoRvListType9(
    title: String,
    image: Painter,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    FrogoListCardContainer(onClick = onClick, modifier = modifier) {
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
            TitleText(text = title)
        }
    }
}

// endregion

// region Type 10: Full-width Image (150dp) + Title + Subtitle

/**
 * Equivalent of frogo_rv_list_type_10.xml
 * Card with full-width 150dp tall image on top, title and subtitle below.
 */
@Composable
fun FrogoRvListType10(
    title: String,
    subtitle: String,
    image: Painter,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    FrogoListCardContainer(onClick = onClick, modifier = modifier) {
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
            TitleText(text = title)
            SubtitleText(text = subtitle)
        }
    }
}

// endregion

// region Type 11: Full-width Image (150dp) + Title + Subtitle + Description

/**
 * Equivalent of frogo_rv_list_type_11.xml
 * Card with full-width 150dp tall image on top, title, subtitle, and description below.
 */
@Composable
fun FrogoRvListType11(
    title: String,
    subtitle: String,
    description: String,
    image: Painter,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    FrogoListCardContainer(onClick = onClick, modifier = modifier) {
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
            TitleText(text = title)
            SubtitleText(text = subtitle)
            Spacer(modifier = Modifier.height(8.dp))
            DescriptionText(text = description)
        }
    }
}

// endregion

// region Type 12: Title only (wrap_content width)

/**
 * Equivalent of frogo_rv_list_type_12.xml
 * Card (wrap content width) with title text only.
 */
@Composable
fun FrogoRvListType12(
    title: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    FrogoListCardContainer(fillWidth = false, onClick = onClick, modifier = modifier) {
        TitleText(text = title)
    }
}

// endregion
