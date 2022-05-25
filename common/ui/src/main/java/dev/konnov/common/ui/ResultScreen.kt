package dev.konnov.common.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.konnov.common.dbtestingtools.DbInfo
import dev.konnov.common.dbtestingtools.domain.entity.TestResult
import dev.konnov.common.dbtestingtools.domain.entity.fakeTestResults
import dev.konnov.common.dbtestingtools.shorten
import dev.konnov.common.dbtestingtools.toListOfRows
import dev.konnov.common.mvvm.TestDbViewModel
import dev.konnov.common.mvvm.TestDbViewState.Content
import dev.konnov.common.mvvm.TestDbViewState.InProgress

@Preview(showBackground = true, widthDp = 320, heightDp = 700)
@Composable
private fun PreviewTestTableScreen() {
    TestTableScreen(DbInfo("Room"), fakeTestResults)
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
fun ResultScreen(
    viewModel: TestDbViewModel
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
            TestTableScreen(screenState.dbInfo, screenState.results)
        }
    }
}

// TODO rewrite it in more readable way
@Composable
fun TestTableScreen(dbInfo: DbInfo, testResults: List<TestResult>) {
    val headers =
        testResults
            .groupBy { it.numberOfEntries }
            .map {
                it.key to it.value.groupBy { it.dataSetType }.keys.toList()
            }.toMap()


    val contentRows = testResults.toListOfRows()

    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .padding(8.dp)
    ) {
        Text(
            text = dbInfo.dbName,
            Modifier.padding(bottom = 24.dp).fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = Typography().h6,
            color = Color.Gray
        )
        headers
            .entries
            .forEach { numberOfEntriesToDataSetTypesMap ->
                Row(Modifier.fillMaxWidth()) {
                    Text(
                        text = numberOfEntriesToDataSetTypesMap.key.shorten(),
                        Modifier
                            .width(74.dp),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )

                    numberOfEntriesToDataSetTypesMap.value.forEach {
                        Text(
                            text = it.toString(),
                            Modifier
                                .width(90.dp)
                                .padding(bottom = 8.dp),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                contentRows
                    .filter {
                        it.numberOfEntries == numberOfEntriesToDataSetTypesMap.key
                    }
                    .forEach {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState())
                        ) {
                            it.items.forEachIndexed { index, rowText ->
                                if (index == 0) {
                                    Text(
                                        text = rowText,
                                        Modifier
                                            .width(74.dp)
                                            .padding(vertical = 4.dp),
                                        textAlign = TextAlign.Center,
                                        fontStyle = FontStyle.Italic
                                    )
                                } else {
                                    Text(
                                        text = rowText,
                                        Modifier
                                            .width(90.dp)
                                            .padding(vertical = 4.dp),
                                        textAlign = TextAlign.Center
                                    )
                                }

                            }
                        }
                    }

                Spacer(
                    Modifier
                        .fillMaxWidth()
                        .height(32.dp)
                )
            }
    }
}