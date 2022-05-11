package dev.konnov.feature.room.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.konnov.common.ui.Progress
import dev.konnov.common.ui.TestResultScreen
import dev.konnov.feature.room.presentation.RoomViewModel
import  dev.konnov.feature.room.presentation.RoomViewState.Content
import  dev.konnov.feature.room.presentation.RoomViewState.InProgress

@Composable
fun RoomScreen(
    viewModel: RoomViewModel
) {
    LaunchedEffect(key1 = "key") {
        viewModel.testDbSpeed()
    }
    val state by viewModel.state.collectAsState()
    when (val screenState = state) {
        InProgress -> {
            Progress()
        }
        is Content -> {
            TestResultScreen(screenState.results)
        }
    }
}