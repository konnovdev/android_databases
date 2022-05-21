package dev.konnov.feature.sqldelight.ui

import androidx.compose.runtime.Composable
import dev.konnov.common.ui.ResultScreen
import dev.konnov.feature.sqldelight.presentation.SqlDelightViewModel

@Composable
fun SqlDelightScreen(
    viewModel: SqlDelightViewModel
) {
    ResultScreen(viewModel)
}