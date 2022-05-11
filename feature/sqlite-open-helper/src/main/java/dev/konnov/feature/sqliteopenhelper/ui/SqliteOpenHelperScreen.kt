package dev.konnov.feature.sqliteopenhelper.ui

import androidx.compose.runtime.*
import dev.konnov.common.ui.Progress
import dev.konnov.common.ui.TestResultScreen
import dev.konnov.feature.sqliteopenhelper.presentation.SqliteOpenHelperState.InProgress
import dev.konnov.feature.sqliteopenhelper.presentation.SqliteOpenHelperState.Content
import dev.konnov.feature.sqliteopenhelper.presentation.SqliteOpenHelperViewModel

@Composable
fun SqliteOpenHelperScreen(
    viewModel: SqliteOpenHelperViewModel
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