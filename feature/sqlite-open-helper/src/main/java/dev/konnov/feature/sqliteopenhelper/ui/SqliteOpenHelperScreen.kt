package dev.konnov.feature.sqliteopenhelper.ui

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.konnov.feature.sqliteopenhelper.presentation.SqliteOpenHelperState.InProgress
import dev.konnov.feature.sqliteopenhelper.presentation.SqliteOpenHelperState.Content
import dev.konnov.feature.sqliteopenhelper.presentation.SqliteOpenHelperViewModel

@Preview(showBackground = true, widthDp = 320, heightDp = 700)
@Composable
private fun PreviewSqliteHelperScreen() {
    SqliteOpenHelperMainScreen("Hello sqlitehelper")
}

@Preview(showBackground = true, widthDp = 320, heightDp = 700)
@Composable
private fun PreviewSqliteHelperScreenProgress() {
    Progress()
}

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
            SqliteOpenHelperMainScreen(screenState.text)
        }
    }
}

@Composable
private fun SqliteOpenHelperMainScreen(text: String) {
    Box(Modifier.verticalScroll(rememberScrollState())) {
        Text(text)
    }
}

@Composable
private fun Progress() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}