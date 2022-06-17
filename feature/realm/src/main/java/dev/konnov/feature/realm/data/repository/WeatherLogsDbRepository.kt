package dev.konnov.feature.realm.data.repository

import dev.konnov.common.dataset.weatherlogs.data.model.WeatherLog
import dev.konnov.common.dbtestingtools.data.converter.TestResultConverter
import dev.konnov.common.dbtestingtools.data.datasource.DataSetDataSource
import dev.konnov.common.dbtestingtools.domain.entity.OperationType
import dev.konnov.common.dbtestingtools.domain.entity.TestResult
import dev.konnov.common.dbtestingtools.domain.repository.DbTestRepository
import dev.konnov.feature.realm.data.converter.WeatherDtoConverter
import dev.konnov.feature.realm.data.datasource.WeatherDbDataSource
import javax.inject.Inject

// TODO find how to remove this repository
/** The reason I had to make manually this repository and copy ALL the code from DbTestRepositoryImpl
 * is because Dagger Hilt cannot resolve a class with generic object that extends RealmObject:
 *
 * Example:
 * You are injecting DbTestRepositoryImpl<String, NewsReport, NewsReportDto>
 * where NewsReportDto extends RealmObject
 * Then you will get one of the following errors depending on how you're injecting:
    1) Either this one:

    error: ComponentProcessingStep was unable to process 'dev.konnov.databasesandroid.App_HiltComponents.SingletonC' because 'dev.konnov.feature.realm.data.model.WeatherLogDto' could not be resolved.

    Dependency trace:
    => element (CLASS): dev.konnov.feature.realm.data.repository.WeatherDbTestRepository
    => type (DECLARED superclass): dev.konnov.common.dbtestingtools.data.repository.DbTestRepositoryImpl<java.lang.Double,dev.konnov.common.dataset.weatherlogs.data.model.WeatherLog,dev.konnov.feature.realm.data.model.WeatherLogDto>
    => type (ERROR type argument): dev.konnov.feature.realm.data.model.WeatherLogDto

    If type 'dev.konnov.feature.realm.data.model.WeatherLogDto' is a generated type, check above for compilation errors that may have prevented the type from being generated. Otherwise, ensure that type 'dev.konnov.feature.realm.data.model.WeatherLogDto' is on your classpath.
    1 error
    ComponentProcessingStep was unable to process 'dev.konnov.databasesandroid.App_HiltComponents.SingletonC' because 'dev.konnov.feature.realm.data.model.WeatherLogDto' could not be resolved.


    2) Or this one:
    /Users/ilakonnov/Documents/uni/database_design/android_databases/feature/realm/build/tmp/kapt3/stubs/debug/dev/konnov/feature/realm/data/model/NewsReportDtoWrapper.java:8: error: cannot access NewsReportDto
    private final dev.konnov.feature.realm.data.model.NewsReportDto dto = null;
    ^
    bad class file: /Users/ilakonnov/Documents/uni/database_design/android_databases/feature/realm/build/tmp/kotlin-classes/debug/dev/konnov/feature/realm/data/model/NewsReportDto.class
    undeclared type variable: T
    Please remove or make sure it appears in the correct subdirectory of the classpath.
 *
 **/
@Deprecated("TODO find a way to make dagger hilt work with Realm objects")
class WeatherLogsDbRepository @Inject constructor(
    private val dataSetDataSource: DataSetDataSource<Double, WeatherLog>,
    private val dbDataSource: WeatherDbDataSource,
    private val testResultConverter: TestResultConverter,
    private val dtoConverter: WeatherDtoConverter
) : DbTestRepository {

    private val data = mutableListOf<WeatherLog>()

    override suspend fun insert(entitiesSize: Int): TestResult {
        dbDataSource.deleteAll()
        data.clear()
        data.addAll(dataSetDataSource.get(entitiesSize))
        val dtos = data.map(dtoConverter::convert)

        return testResultConverter.convert(
            data,
            OperationType.INSERT
        ) { dbDataSource.insert(dtos) }
    }

    override suspend fun loadAll(): TestResult =
        testResultConverter.convert(
            data,
            OperationType.LOAD_ALL
        ) { dbDataSource.loadAll() }

    override suspend fun loadByParameter(): TestResult =
        testResultConverter.convert(
            data,
            OperationType.LOAD_PARAM
        ) { dbDataSource.loadByParameter(dataSetDataSource.parameterToLoadBy) }

    override suspend fun update(): TestResult {
        val dtoToUpdate = dtoConverter.convert(dataSetDataSource.objectToInsertAsUpdate)

        return testResultConverter.convert(
            data,
            OperationType.UPDATE
        ) {
            dbDataSource.update(
                dataSetDataSource.oldParameterToUpdate,
                dtoToUpdate
            )
        }
    }

    override suspend fun delete(): TestResult =
        testResultConverter.convert(
            data,
            OperationType.DELETE
        ) {
            dbDataSource.delete(dataSetDataSource.parameterToDelete)
        }
}