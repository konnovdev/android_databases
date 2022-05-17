package dev.konnov.feature.realm.data.model

import io.realm.RealmObject

data class NewsReportDtoWrapper(val dto: NewsReportDto)

class NewsReportDto : RealmObject {
    var title: String = ""
    var description: String = ""
}


// TODO see if wrapper could be removed
/** The reason for using a wrapper is this exception:
 *
error: ComponentProcessingStep was unable to process 'dev.konnov.databasesandroid.App_HiltComponents.SingletonC' because 'dev.konnov.feature.realm.data.model.WeatherLogDto' could not be resolved.

Dependency trace:
=> element (CLASS): dev.konnov.feature.realm.data.repository.WeatherDbTestRepository
=> type (DECLARED superclass): dev.konnov.common.dbtestingtools.data.repository.DbTestRepositoryImpl<java.lang.Double,dev.konnov.common.dataset.weatherlogs.data.model.WeatherLog,dev.konnov.feature.realm.data.model.WeatherLogDto>
=> type (ERROR type argument): dev.konnov.feature.realm.data.model.WeatherLogDto

If type 'dev.konnov.feature.realm.data.model.WeatherLogDto' is a generated type, check above for compilation errors that may have prevented the type from being generated. Otherwise, ensure that type 'dev.konnov.feature.realm.data.model.WeatherLogDto' is on your classpath.
1 error
ComponentProcessingStep was unable to process 'dev.konnov.databasesandroid.App_HiltComponents.SingletonC' because 'dev.konnov.feature.realm.data.model.WeatherLogDto' could not be resolved.
 *
 */