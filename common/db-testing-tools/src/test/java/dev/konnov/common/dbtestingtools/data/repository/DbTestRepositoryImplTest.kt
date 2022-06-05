package dev.konnov.common.dbtestingtools.data.repository

import dev.konnov.common.dbtestingtools.data.converter.DtoConverter
import dev.konnov.common.dbtestingtools.data.converter.TestResultConverter
import dev.konnov.common.dbtestingtools.data.datasource.DataSetDataSource
import dev.konnov.common.dbtestingtools.data.datasource.DbDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

@RunWith(MockitoJUnitRunner::class)
class DbTestRepositoryImplTest {

    private val dataSetDataSource: DataSetDataSource<Param, Entity> = mock()

    private val dbDataSource: DbDataSource<Param, DTO> = mock()

    private val testResultConverter = TestResultConverter()

    private val dtoConverter: DtoConverter<Entity, DTO> = mock()

    private val entitiesSize = 1000

    private val entities = listOf(Entity("value"))

    private val dtos = listOf(DTO("value"))

    private val param = Param("value")

    private val repository = DbTestRepositoryImpl(
        dataSetDataSource = dataSetDataSource,
        dbDataSource = dbDataSource,
        testResultConverter = testResultConverter,
        dtoConverter = dtoConverter
    )

    @Test
    fun `insert data EXPECT delete all data and insert`() = runTest {
        whenever(dtoConverter.convert(entities.first())).thenReturn(dtos.first())
        whenever(dataSetDataSource.get(entitiesSize)).thenReturn(entities)

        repository.insert(entitiesSize)

        verify(dbDataSource).deleteAll()
        verify(dataSetDataSource).get(entitiesSize)
        verify(dtoConverter).convert(entities.first())
        verify(dbDataSource).insert(dtos)
    }

    @Test
    fun `load all data EXPECT load all`() = runTest {
        fillInternalDataList()

        repository.loadAll()

        verify(dbDataSource).loadAll()
    }

    @Test
    fun `load by parameter EXPECT load by parameter`() = runTest {
        whenever(dataSetDataSource.parameterToLoadBy).thenReturn(param)
        fillInternalDataList()

        repository.loadByParameter()

        verify(dbDataSource).loadByParameter(param)
    }

    @Test
    fun `update EXPECT update by param`() = runTest {
        whenever(dtoConverter.convert(entities.first())).thenReturn(dtos.first())
        whenever(dataSetDataSource.objectToInsertAsUpdate).thenReturn(entities.first())
        whenever(dataSetDataSource.oldParameterToUpdate).thenReturn(param)
        fillInternalDataList()

        repository.update()

        verify(dbDataSource).update(param, dtos.first())
    }

    @Test
    fun `delete EXPECT delete by param`() = runTest {
        whenever(dataSetDataSource.parameterToDelete).thenReturn(param)
        fillInternalDataList()

        repository.delete()

        verify(dbDataSource).delete(param)
    }

    private fun fillInternalDataList() {
        val data = repository.getPrivateProperty<DbTestRepositoryImpl<Param, Entity, DTO>, MutableList<Entity>>("data")
        data.addAll(entities)
    }

    private inline fun <reified T : Any, R> T.getPrivateProperty(name: String): R =
        T::class
            .memberProperties
            .firstOrNull { it.name == name }
            ?.apply { isAccessible = true }
            ?.get(this) as R


    private data class Param(val value: String)
    private data class DTO(val value: String)
    private data class Entity(val value: String)
}