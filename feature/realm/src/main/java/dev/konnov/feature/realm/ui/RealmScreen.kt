package dev.konnov.feature.realm.ui

import androidx.compose.runtime.Composable
import dev.konnov.feature.realm.presentation.RealmDbViewModel
import dev.konnov.common.ui.ResultScreen

@Composable
fun RealmScreen(
    viewModel: RealmDbViewModel
) {
    ResultScreen(viewModel)
}