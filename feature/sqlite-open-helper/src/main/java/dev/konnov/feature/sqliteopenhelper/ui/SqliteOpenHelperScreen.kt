package dev.konnov.feature.sqliteopenhelper.ui

import androidx.compose.runtime.*
import dev.konnov.common.ui.ResultScreen
import dev.konnov.feature.sqliteopenhelper.presentation.SqliteOpenHelperViewModel

@Composable
fun SqliteOpenHelperScreen(
    viewModel: SqliteOpenHelperViewModel
) {
    ResultScreen(viewModel)
}