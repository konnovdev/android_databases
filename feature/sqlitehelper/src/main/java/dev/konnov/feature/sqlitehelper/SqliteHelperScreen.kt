package dev.konnov.feature.sqlitehelper

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true, widthDp = 320, heightDp = 700)
@Composable
private fun PreviewSqliteHelperScreen() {
    SqliteHelperScreen()
}

@Composable
fun SqliteHelperScreen() {
    Text("Hello SQLite helper")
}