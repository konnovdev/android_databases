package dev.konnov.feature.room.ui

import androidx.compose.runtime.Composable
import dev.konnov.feature.room.presentation.RoomDbViewModel
import dev.konnov.common.ui.ResultScreen

@Composable
fun RoomScreen(
    viewModel: RoomDbViewModel
) {
    ResultScreen(viewModel)
}