package dev.konnov.feature.sqliteopenhelper.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.konnov.feature.sqliteopenhelper.presentation.SqliteOpenHelperViewModel

@Preview(showBackground = true, widthDp = 320, heightDp = 700)
@Composable
private fun PreviewSqliteHelperScreen() {
    SqliteOpenHelperMainScreen()
}

@Composable
fun SqliteOpenHelperScreen(
    viewModel: SqliteOpenHelperViewModel
) {
    viewModel.testDbSpeed() // TODO do in background
    SqliteOpenHelperMainScreen()
}

@Composable
private fun SqliteOpenHelperMainScreen() {
    Text("Hello SQLiteOpenHelper")
}