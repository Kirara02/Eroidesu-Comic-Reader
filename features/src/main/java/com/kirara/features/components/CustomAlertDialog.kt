package com.kirara.features.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ConfirmationDialog(
    showDialog: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmAction: () -> Unit,
    title: String,
    message: String,
    confirmButtonText: String = "OK",
    dismissButtonText: String = "Batal"
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = { Text(title) },
            text = { Text(message) },
            confirmButton = {
                Button(
                    onClick = {
                        onConfirmAction()
                        onDismissRequest()
                    }
                ) {
                    Text(confirmButtonText)
                }
            },
            dismissButton = {
                Button(
                    onClick = onDismissRequest
                ) {
                    Text(dismissButtonText)
                }
            }
        )
    }
}
