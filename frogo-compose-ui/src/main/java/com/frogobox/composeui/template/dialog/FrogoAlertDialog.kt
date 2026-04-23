package com.frogobox.composeui.template.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun FrogoAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector? = null,
    confirmButtonText: String = "OK",
    dismissButtonText: String = "Cancel",
    modifier: Modifier = Modifier
) {
    AlertDialog(
        icon = {
            if (icon != null) {
                Icon(icon, contentDescription = "Dialog Icon")
            }
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(onClick = { onConfirmation() }) {
                Text(confirmButtonText)
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text(dismissButtonText)
            }
        },
        modifier = modifier
    )
}
