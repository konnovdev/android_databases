package dev.konnov.feature.sqldelight.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.konnov.feature.sqldelight.presentation.SqlDelightViewModel

@Preview(showBackground = true, widthDp = 320, heightDp = 700)
@Composable
private fun PreviewSqlDelightScreen() {
    SqlDelightScreen()
}

@Composable
fun SqlDelightScreen(
    viewModel: SqlDelightViewModel
) {
    SqlDelightScreen()
    viewModel.testDbSpeed()
}

@Composable
private fun SqlDelightScreen() {
    Text("Hello SqlDelight")
}