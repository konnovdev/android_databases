package dev.konnov.feature.sqlitehelper

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true, widthDp = 320, heightDp = 700)
@Composable
private fun PreviewSqliteHelperScreen() {
    SqliteHelperMainScreen()
}

@Composable
fun SqliteHelperScreen(
    viewModel: SqliteHelperViewModel
) {
    viewModel.testDbSpeed()
    SqliteHelperMainScreen()
}

@Composable
private fun SqliteHelperMainScreen() {
    Text("Hello SQLite helper")
}