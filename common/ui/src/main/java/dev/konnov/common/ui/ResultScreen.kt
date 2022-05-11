package dev.konnov.common.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.konnov.common.dbtestingtools.TestResult
import dev.konnov.common.dbtestingtools.fakeTestResults


@Preview(showBackground = true, widthDp = 320, heightDp = 700)
@Composable
private fun PreviewTestResultScreen() {
    TestResultScreen(fakeTestResults)
}

@Preview(showBackground = true, widthDp = 320, heightDp = 700)
@Composable
private fun PreviewScreenProgress() {
    Progress()
}

@Composable
fun Progress() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun TestResultScreen(testResults: List<TestResult>) {
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