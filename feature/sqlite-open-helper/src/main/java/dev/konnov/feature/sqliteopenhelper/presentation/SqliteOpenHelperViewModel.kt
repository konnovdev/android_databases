package dev.konnov.feature.sqliteopenhelper.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.konnov.feature.sqliteopenhelper.data.WeatherRepository
import dev.konnov.common.dataset.weatherlogs.WeatherLog
import dev.konnov.common.dataset.weatherlogs.WeatherLogDataGenerator
import dev.konnov.common.dbtestingtools.SIZE_100k
import dev.konnov.common.dbtestingtools.SIZE_10k
import dev.konnov.common.dbtestingtools.SIZE_1M
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.StringBuilder
import javax.inject.Inject

@HiltViewModel
class SqliteOpenHelperViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _state = MutableStateFlow<SqliteOpenHelperState>(SqliteOpenHelperState.InProgress)
    val state: StateFlow<SqliteOpenHelperState> = _state

    fun testDbSpeed() {
        viewModelScope.launch {
            val dbTestSpeedResultOutput = StringBuilder("SQLiteOpenHelper test for WeatherLog entity.\n")
            withContext(IO) {
                for (i in 0..TEST_ITERATIONS) {
                    dbTestSpeedResultOutput.append(test(WeatherLogDataGenerator.getEntities(SIZE_10k)))
                }

                for (i in 0..TEST_ITERATIONS) {
                    dbTestSpeedResultOutput.append(test(WeatherLogDataGenerator.getEntities(SIZE_100k)))
                }

                for (i in 0..TEST_ITERATIONS) {
                    dbTestSpeedResultOutput.append(test(WeatherLogDataGenerator.getEntities(SIZE_1M)))
                }
            }
            _state.value = SqliteOpenHelperState.Content(dbTestSpeedResultOutput.toString())
        }
    }

    private fun test(entries: List<WeatherLog>): String {
        val dbTestSpeedResultOutput =
            StringBuilder("Test for ${entries.size} entries.\n")

        val weatherLogToInsert = WeatherLog(14.0, 3123.1, 33.0)

        val insertResult = weatherRepository.insert(entries)
        val loadEverythingResult = weatherRepository.loadEverything()
        val updateResult = weatherRepository.update(13.0, weatherLogToInsert)
        val loadByParameterResult = weatherRepository.loadByParameter(14.0)

        with(dbTestSpeedResultOutput) {
            append("insertResult: $insertResult\n")
            append("loadEverythingResult: $loadEverythingResult\n")
            append("updateResult: $updateResult\n")
            append("loadByParameterResult: $loadByParameterResult\n\n")
        }
        return dbTestSpeedResultOutput.toString()
    }

    private companion object {
        const val TEST_ITERATIONS = 10
    }
}