package dev.konnov.feature.sqliteopenhelper.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.konnov.feature.sqliteopenhelper.data.WeatherRepository
import dev.konnov.common.dataset.weatherlogs.WeatherLog
import dev.konnov.common.dataset.weatherlogs.WeatherLogDataGenerator
import dev.konnov.common.dbtestingtools.SIZE_100k
import dev.konnov.common.dbtestingtools.SIZE_10k
import dev.konnov.common.dbtestingtools.SIZE_1M
import java.lang.StringBuilder
import javax.inject.Inject

@HiltViewModel
class SqliteOpenHelperViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    fun testDbSpeed() {
        for (i in 0..10) {
            test(WeatherLogDataGenerator.getEntities(SIZE_10k))
        }

        for (i in 0..10) {
            test(WeatherLogDataGenerator.getEntities(SIZE_100k))
        }

        for (i in 0..10) {
            test(WeatherLogDataGenerator.getEntities(SIZE_1M))
        }
    }

    private fun test(entries: List<WeatherLog>) {
        val dbTestSpeedResultOutput =
            StringBuilder("SQLiteOpenHelper test for WeatherLog entity. ${entries.size} entries.\n")

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
        Log.v("notag", dbTestSpeedResultOutput.toString()) // TODO show and save logs
    }
}