package dev.konnov.feature.realm.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.konnov.common.dataset.newsreports.NewsReport
import dev.konnov.common.dataset.newsreports.NewsReportDataGenerator
import dev.konnov.common.dataset.newsreports.Title
import dev.konnov.common.dataset.weatherlogs.WeatherLogDataGenerator
import dev.konnov.common.dbtestingtools.SIZE_100k
import dev.konnov.common.dbtestingtools.SIZE_10k
import dev.konnov.common.dbtestingtools.TestResult
import dev.konnov.common.dbtestingtools.group
import dev.konnov.feature.realm.data.repository.NewsReportRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import dev.konnov.feature.realm.presentation.RealmViewState.Content
import dev.konnov.feature.realm.presentation.RealmViewState.InProgress
import javax.inject.Inject

@HiltViewModel
class RealmViewModel @Inject constructor(
    private val newsReportRepository: NewsReportRepository
) : ViewModel() {

    private val _state = MutableStateFlow<RealmViewState>(InProgress)
    val state: StateFlow<RealmViewState> = _state

    fun testDbSpeed() {
        viewModelScope.launch {
// TODO create an entity class here that will be returned on UI and displayed
            val results = mutableListOf<TestResult>()
            for (i in 0..TEST_ITERATIONS) {
//                    results += testOnWeather(WeatherLogDataGenerator.getEntities(SIZE_10k)) // TODO add weather tests
                results += testOnNews(NewsReportDataGenerator.getEntities(SIZE_10k))
//                    results += testOnWeather(WeatherLogDataGenerator.getEntities(SIZE_100k))
                results += testOnNews(NewsReportDataGenerator.getEntities(SIZE_100k))
            }
            _state.value = Content(results.group())
        }
    }

    private suspend fun testOnNews(entries: List<NewsReport>): List<TestResult> {
        val oldTitle = Title("Morning in Las Vegas")
        val newTitle = Title("Some news title")
        val newsReportsToInsert = NewsReport(newTitle, "Some news description")

        val insertResult = newsReportRepository.insert(entries)
        val loadEverythingResult = newsReportRepository.loadEverything()
        val updateResult = newsReportRepository.update(oldTitle, newsReportsToInsert)
        val loadByParameterResult = newsReportRepository.loadByParameter(newTitle)
        val deleteResult = newsReportRepository.delete(newTitle)

        return listOf(
            insertResult,
            loadEverythingResult,
            updateResult,
            loadByParameterResult,
            deleteResult
        )
    }

    private companion object {
        const val TEST_ITERATIONS = 5
    }
}