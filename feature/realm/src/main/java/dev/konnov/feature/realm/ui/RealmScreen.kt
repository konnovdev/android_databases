package dev.konnov.feature.realm.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.konnov.common.ui.Progress
import dev.konnov.common.ui.TestResultScreen
import dev.konnov.feature.realm.presentation.RealmViewModel
import dev.konnov.common.mvvm.TestDBViewState.Content
import dev.konnov.common.mvvm.TestDBViewState.InProgress

@Composable
fun RealmScreen(
    viewModel: RealmViewModel
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