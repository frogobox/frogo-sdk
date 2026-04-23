package com.frogobox.composeui.template.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FrogoInputDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: (String) -> Unit,
    dialogTitle: String,
    dialogText: String,
    modifier: Modifier = Modifier,
    initialValue: String = "",
    placeholder: String = ""
) {
    var inputValue by remember { mutableStateOf(initialValue) }

    AlertDialog(
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = dialogText)
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = inputValue,
                    onValueChange = { inputValue = it },
                    placeholder = { Text(placeholder) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(onClick = { onConfirmation(inputValue) }) {
                Text("Submit")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text("Cancel")
            }
        },
        modifier = modifier
    )
}
