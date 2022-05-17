package dev.konnov.common.dbtestingtools.data.converter

import dev.konnov.common.dbtestingtools.domain.entity.DataSetType
import dev.konnov.common.dbtestingtools.domain.entity.OperationType
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.math.BigDecimal

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

    @Test(expected = NoSuchElementException::class)
    fun `empty list of entities EXPECT exceptio`() {
        testResultConverter.convert(listOf<Int>(), OperationType.LOAD_ALL, {})
    }

    @Test
    fun timeInMillisAreNotZero() {
        val testResult =
            testResultConverter.convert(listOf(1, 2), OperationType.LOAD_ALL, operation = {
                difficultMathOperation()
            })

        assert(testResult.timeInMillis > 5)
    }

    private fun difficultMathOperation() {
        var sum = BigDecimal(0)
        repeat(1000000) { index ->
            sum += BigDecimal(
                Math.pow(9.132, 99.123124) / Math.exp(434.123) / Math.log10(
                    13111213.3
                ) * Math.pow(91.132, 11.232) * index
            )
        }
    }

    private data class TestEntity1(val firstParam: String, val secondParam: String)

    private data class TestEntity2(val firstParam: Double, val secondParam: Double)
}