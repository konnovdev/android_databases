package dev.konnov.databasesandroid.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

// TODO make a good looking theme and move it to a separate module
@Composable
fun DatabasesAndroidTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        content = content
    )
}