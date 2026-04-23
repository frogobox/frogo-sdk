package com.frogobox.composeui.template.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FrogoConfirmDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    modifier: Modifier = Modifier
) {
    AlertDialog(
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
                Text("Yes")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text("No")
            }
        },
        modifier = modifier
    )
}
