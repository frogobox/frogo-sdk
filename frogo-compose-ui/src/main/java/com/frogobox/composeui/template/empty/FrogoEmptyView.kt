package com.frogobox.composeui.template.empty

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.frogobox.composeui.R

/**
 * Created by Faisal Amir
 * Compose equivalent of frogo_empty_view.xml + frogo_container_empty_view.xml.
 *
 * Displays a centered empty state with an icon, title, and subtitle,
 * matching the original XML layout sizing and colors.
 *
 * @param modifier     The modifier to apply.
 * @param title        The title text (default: "No data found").
 * @param subtitle     The subtitle text (default: "Please insert data here !!!").
 * @param iconResId    Drawable resource for the empty state icon.
 * @param titleColor   Color for the title text.
 */
@Composable
fun FrogoEmptyView(
    modifier: Modifier = Modifier,
    title: String = "NO DATA FOUND",
    subtitle: String = "Please insert data here !!!",
    iconResId: Int = R.drawable.frogo_ic_empty_view,
    titleColor: Color = MaterialTheme.colorScheme.onSurface
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = title,
            modifier = Modifier.size(150.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = titleColor,
            textAlign = TextAlign.Center
        )

        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FrogoEmptyViewPreview() {
    FrogoEmptyView()
}
