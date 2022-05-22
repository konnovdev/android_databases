package dev.konnov.feature.datastore.ui

import androidx.compose.runtime.Composable
import dev.konnov.common.ui.ResultScreen
import dev.konnov.feature.datastore.presentation.DataStoreViewModel

@Composable
fun DataStoreScreen(
    viewModel: DataStoreViewModel
)  {
    ResultScreen(viewModel)
}