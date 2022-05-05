package dev.konnov.feature.sqldelight

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

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