package dev.konnov.feature.objectbox.data.datasource

import dev.konnov.common.dbtestingtools.data.datasource.DbDataSource
import dev.konnov.feature.objectbox.data.model.WeatherLogDto
import dev.konnov.feature.objectbox.data.model.WeatherLogDto_
import io.objectbox.Box
import javax.inject.Inject

class WeatherDbDataSource @Inject constructor(
    private val box: Box<WeatherLogDto>
) : DbDataSource<Double, WeatherLogDto> {

    override suspend fun insert(items: List<WeatherLogDto>) {
        box.put(items)
    }

    override suspend fun loadAll() {
        box.all
    }

    override suspend fun loadByParameter(param: Double) {
        box
            .query()
            .between(WeatherLogDto_.temperature, param, TOLERANCE_FOR_DOUBLE)
            .build()
            .find()
    }

    override suspend fun update(param: Double, objectToInsert: WeatherLogDto) {
        box
            .query()
            .between(WeatherLogDto_.temperature, param, TOLERANCE_FOR_DOUBLE)
            .build()
            .find()
            .also {
                box.put(it)
            }
    }

    override suspend fun delete(param: Double) {
        box
            .query()
            .equal(WeatherLogDto_.temperature, param, TOLERANCE_FOR_DOUBLE)
            .build()
            .remove()
    }

    override suspend fun deleteAll() {
        box.removeAll()
    }

    private companion object {
        const val TOLERANCE_FOR_DOUBLE = 0.001
    }
}