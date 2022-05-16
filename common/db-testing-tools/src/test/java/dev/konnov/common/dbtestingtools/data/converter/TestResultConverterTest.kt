package dev.konnov.common.dbtestingtools.data.converter

import dev.konnov.common.dbtestingtools.domain.entity.DataSetType
import dev.konnov.common.dbtestingtools.domain.entity.OperationType
import junit.framework.TestCase.assertEquals
import org.junit.Test

internal class TestResultConverterTest {

    private val testResultConverter = TestResultConverter()

    @Test
    fun convertStringItems() {
        val itemsList = mutableListOf<TestEntity1>()
        val size = 100
        val operationType = OperationType.LOAD_ALL
        repeat(size) { itemsList.add(TestEntity1("param1", "param2")) }

        val result = testResultConverter.convert(itemsList, operationType, operation = {})

        assertEquals(DataSetType.STRING, result.dataSetType)
        assertEquals(size, result.numberOfEntries)
        assertEquals(operationType, result.operationType)
    }

    @Test
    fun convertFloatItems() {
        val itemsList = mutableListOf<TestEntity2>()
        val size = 100
        val operationType = OperationType.UPDATE
        repeat(size) { itemsList.add(TestEntity2(123.0, 12.0)) }

        val result = testResultConverter.convert(itemsList, operationType, operation = {})

        assertEquals(DataSetType.REAL, result.dataSetType)
        assertEquals(size, result.numberOfEntries)
        assertEquals(operationType, result.operationType)
    }

    private data class TestEntity1(val firstParam: String, val secondParam: String)

    private data class TestEntity2(val firstParam: Double, val secondParam: Double)
}