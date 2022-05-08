package dev.konnov.feature.sqliteopenhelper.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.konnov.common.dataset.newsreports.NewsReport
import dev.konnov.common.dataset.newsreports.NewsReportDataGenerator
import dev.konnov.common.dataset.newsreports.Title
import dev.konnov.common.dataset.weatherlogs.Temperature
import dev.konnov.feature.sqliteopenhelper.data.WeatherRepository
import dev.konnov.common.dataset.weatherlogs.WeatherLog
import dev.konnov.common.dataset.weatherlogs.WeatherLogDataGenerator
import dev.konnov.common.dbtestingtools.SIZE_100k
import dev.konnov.common.dbtestingtools.SIZE_10k
import dev.konnov.feature.sqliteopenhelper.data.NewsReportRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.StringBuilder
import javax.inject.Inject

@HiltViewModel
class SqliteOpenHelperViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val newsReportRepository: NewsReportRepository
) : ViewModel() {

    private val _state = MutableStateFlow<SqliteOpenHelperState>(SqliteOpenHelperState.InProgress)
    val state: StateFlow<SqliteOpenHelperState> = _state

    fun testDbSpeed() {
        viewModelScope.launch {
            // TODO create an entity class here that will be returned on UI and displayed
            val dbTestSpeedResultOutput = StringBuilder("SQLiteOpenHelper test for WeatherLog entity.\n")
            withContext(IO) {
                for (i in 0..TEST_ITERATIONS) {
                    dbTestSpeedResultOutput.append(testOnWeather(WeatherLogDataGenerator.getEntities(SIZE_10k)))
                    dbTestSpeedResultOutput.append(testOnNews(NewsReportDataGenerator.getEntities(SIZE_10k)))
                }

                for (i in 0..TEST_ITERATIONS) {
                    dbTestSpeedResultOutput.append(testOnWeather(WeatherLogDataGenerator.getEntities(SIZE_100k)))
                    dbTestSpeedResultOutput.append(testOnNews(NewsReportDataGenerator.getEntities(SIZE_100k)))
                }
            }
            _state.value = SqliteOpenHelperState.Content(dbTestSpeedResultOutput.toString())
        }
    }

    private fun testOnWeather(entries: List<WeatherLog>): String {
        val dbTestSpeedResultOutput =
            StringBuilder("Test for ${entries.size} entries.\n")

        val oldTemperature = Temperature(13.0)
        val newTemperature = Temperature(14.0)
        val weatherLogToInsert = WeatherLog(newTemperature, 3123.1, 33.0)

        val insertResult = weatherRepository.insert(entries)
        val loadEverythingResult = weatherRepository.loadEverything()
        val updateResult = weatherRepository.update(oldTemperature, weatherLogToInsert)
        val loadByParameterResult = weatherRepository.loadByParameter(newTemperature)
        val deleteResult = weatherRepository.delete(newTemperature)

        with(dbTestSpeedResultOutput) {
            append("insertResult: $insertResult\n")
            append("loadEverythingResult: $loadEverythingResult\n")
            append("updateResult: $updateResult\n")
            append("loadByParameterResult: $loadByParameterResult\n")
            append("deleteResult: $deleteResult\n\n")
        }
        return dbTestSpeedResultOutput.toString()
    }

    private fun testOnNews(entries: List<NewsReport>): String {
        val dbTestSpeedResultOutput =
            StringBuilder("Test for ${entries.size} entries.\n")

        val oldTitle = Title("Morning in Las Vegas")
        val newTitle = Title("Some news title")
        val newsReportsToInsert = NewsReport(newTitle, "Some news description")

        val insertResult = newsReportRepository.insert(entries)
        val loadEverythingResult = newsReportRepository.loadEverything()
        val updateResult = newsReportRepository.update(oldTitle, newsReportsToInsert)
        val loadByParameterResult = newsReportRepository.loadByParameter(newTitle)
        val deleteResult = newsReportRepository.delete(newTitle)

        with(dbTestSpeedResultOutput) {
            append("insertResult: $insertResult\n")
            append("loadEverythingResult: $loadEverythingResult\n")
            append("updateResult: $updateResult\n")
            append("loadByParameterResult: $loadByParameterResult\n")
            append("deleteResult: $deleteResult\n\n")
        }
        return dbTestSpeedResultOutput.toString()
    }

    private companion object {
        const val TEST_ITERATIONS = 5
    }
}