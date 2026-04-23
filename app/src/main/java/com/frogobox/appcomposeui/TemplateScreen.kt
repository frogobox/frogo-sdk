package com.frogobox.appcomposeui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.frogobox.composeui.template.dialog.FrogoAlertDialog
import com.frogobox.composeui.template.dialog.FrogoConfirmDialog
import com.frogobox.composeui.template.dialog.FrogoInputDialog
import com.frogobox.composeui.widget.FrogoButton
import com.frogobox.composeui.widget.FrogoSpacerHeight

@Composable
fun TemplateScreen() {
    var showAlertDialog by remember { mutableStateOf(false) }
    var showConfirmDialog by remember { mutableStateOf(false) }
    var showInputDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        FrogoButton(text = "Show Alert Dialog", onClick = { showAlertDialog = true })
        FrogoSpacerHeight()
        FrogoButton(text = "Show Confirm Dialog", onClick = { showConfirmDialog = true })
        FrogoSpacerHeight()
        FrogoButton(text = "Show Input Dialog", onClick = { showInputDialog = true })

        if (showAlertDialog) {
            FrogoAlertDialog(
                onDismissRequest = { showAlertDialog = false },
                onConfirmation = { showAlertDialog = false },
                dialogTitle = "Alert",
                dialogText = "This is an alert dialog."
            )
        }

        if (showConfirmDialog) {
            FrogoConfirmDialog(
                onDismissRequest = { showConfirmDialog = false },
                onConfirmation = { showConfirmDialog = false },
                dialogTitle = "Confirm",
                dialogText = "Are you sure you want to proceed?"
            )
        }

        if (showInputDialog) {
            FrogoInputDialog(
                onDismissRequest = { showInputDialog = false },
                onConfirmation = { input -> 
                    /* Handle input */
                    showInputDialog = false 
                },
                dialogTitle = "Input Required",
                dialogText = "Please enter your name:",
                placeholder = "John Doe"
            )
        }
    }
}
