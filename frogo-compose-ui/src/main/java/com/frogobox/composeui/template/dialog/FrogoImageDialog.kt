package com.frogobox.composeui.template.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale

@Composable
fun FrogoImageDialog(
    onDismissRequest: () -> Unit,
    imagePainter: Painter,
    dialogTitle: String? = null,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        title = dialogTitle?.let { { Text(text = it) } },
        text = {
            Image(
                painter = imagePainter,
                contentDescription = dialogTitle,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentScale = ContentScale.Fit
            )
        },
        confirmButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text("Close")
            }
        },
        modifier = modifier
    )
}
