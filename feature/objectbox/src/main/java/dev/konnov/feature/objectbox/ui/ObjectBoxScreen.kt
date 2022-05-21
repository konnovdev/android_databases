package dev.konnov.feature.objectbox.ui

import androidx.compose.runtime.Composable
import dev.konnov.common.ui.ResultScreen
import dev.konnov.feature.objectbox.presentation.ObjectBoxViewModel

@Composable
fun ObjectBoxScreen(
    viewModel: ObjectBoxViewModel
)  {
    ResultScreen(viewModel)
}