package dev.konnov.feature.sharedpreferences.ui

import androidx.compose.runtime.Composable
import dev.konnov.common.ui.ResultScreen
import dev.konnov.feature.sharedpreferences.presentation.SharedPreferencesViewModel

@Composable
fun SharedPreferenceScreen(
    viewModel: SharedPreferencesViewModel
)  {
    ResultScreen(viewModel)
}