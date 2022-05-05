package dev.konnov.feature.room

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.konnov.feature.room.presentation.ObjectBoxViewModel

@Preview(showBackground = true, widthDp = 320, heightDp = 700)
@Composable
private fun PreviewObjectBoxScreen() {
    ObjectBoxMainScreen()
}

@Composable
fun ObjectBoxScreen(
    viewModel: ObjectBoxViewModel
) {
    ObjectBoxMainScreen()
    viewModel.testDbSpeed()
}

@Composable
private fun ObjectBoxMainScreen() {
    Text("Hello ObjectBox")
}