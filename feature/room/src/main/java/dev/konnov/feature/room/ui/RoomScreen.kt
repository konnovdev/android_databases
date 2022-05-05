package dev.konnov.feature.room

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.konnov.feature.room.presentation.RoomViewModel

@Preview(showBackground = true, widthDp = 320, heightDp = 700)
@Composable
private fun PreviewRoomScreen() {
    RoomMainScreen()
}

@Composable
fun RoomScreen(
    viewModel: RoomViewModel
) {
    RoomMainScreen()
    viewModel.testDbSpeed()
}

@Composable
private fun RoomMainScreen() {
    Text("Hello room")
}