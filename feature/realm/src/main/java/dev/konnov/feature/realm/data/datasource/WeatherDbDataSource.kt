package dev.konnov.feature.realm.data.datasource

import dev.konnov.common.dbtestingtools.data.datasource.DbDataSource
import dev.konnov.feature.realm.data.model.WeatherLogDto
import io.realm.Realm
import io.realm.query
import javax.inject.Inject

class WeatherDbDataSource @Inject constructor(
    private val realm: Realm
) : DbDataSource<Double, WeatherLogDto> {

    override suspend fun insert(items: List<WeatherLogDto>) {
        items.map {
            WeatherLogDto().apply {
                this.temperature = it.temperature
                this.humidity = it.humidity
                this.pressure = it.pressure
            }
        }.also { dtos ->
            realm.write {
                dtos.forEach(::copyToRealm)
            }
        }
    }

    override suspend fun loadAll() {
        realm.query<WeatherLogDto>().find()
    }

    override suspend fun loadByParameter(param: Double) {
        realm.query<WeatherLogDto>("temperature == $0", param).find()
    }

    override suspend fun update(param: Double, objectToInsert: WeatherLogDto) {
        realm.query<WeatherLogDto>("temperature == $0", param)
            .find()
            .forEach { oldWeatherLogDto ->
                realm.write {
                    findLatest(oldWeatherLogDto)?.apply {
                        temperature = objectToInsert.temperature
                        humidity = objectToInsert.humidity
                        pressure = objectToInsert.pressure
                    }
                }
            }
    }

    override suspend fun delete(param: Double) {
        realm.write {
            val query = this.query<WeatherLogDto>("temperature == $0", param)
            delete(query)
        }
    }

    override suspend fun deleteAll() {
        realm.write {
            val query = this.query<WeatherLogDto>()
            delete(query)
        }
    }
}
