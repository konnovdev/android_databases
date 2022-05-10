package dev.konnov.feature.sqliteopenhelper.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.konnov.common.dbtestingtools.TestResult
import dev.konnov.common.dbtestingtools.fakeTestResults
import dev.konnov.feature.sqliteopenhelper.presentation.SqliteOpenHelperState.InProgress
import dev.konnov.feature.sqliteopenhelper.presentation.SqliteOpenHelperState.Content
import dev.konnov.feature.sqliteopenhelper.presentation.SqliteOpenHelperViewModel

@Preview(showBackground = true, widthDp = 320, heightDp = 700)
@Composable
private fun PreviewSqliteHelperScreen() {
    SqliteOpenHelperMainScreen(fakeTestResults)
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
            SqliteOpenHelperMainScreen(screenState.results)
        }
    }
}

@Composable
private fun SqliteOpenHelperMainScreen(testResults: List<TestResult>) {
    Column(Modifier.verticalScroll(rememberScrollState()).padding(8.dp)) {
        Text("Test results: ")
        testResults.forEach {
            Text("dataSetType = ${it.dataSetType}")
            Text("numberOfEntries = ${it.numberOfEntries}")
            Text("operationType = ${it.operationType}")
            Text("average timeInMillis = ${it.timeInMillis}")
            Divider(Modifier.padding(top = 8.dp, bottom = 8.dp), color = Color.Blue, thickness = 1.dp)
        }
    }
}

@Composable
private fun Progress() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}