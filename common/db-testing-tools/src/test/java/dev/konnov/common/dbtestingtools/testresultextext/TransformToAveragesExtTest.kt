package dev.konnov.common.dbtestingtools.testresultextext

import dev.konnov.common.dbtestingtools.domain.entity.DataSetType
import dev.konnov.common.dbtestingtools.domain.entity.OperationType
import dev.konnov.common.dbtestingtools.domain.entity.TestResult
import dev.konnov.common.dbtestingtools.transformToAverages
import junit.framework.TestCase.assertEquals
import org.junit.Test

internal class TransformToAveragesExtTest {

    private val testResults = listOf(
        TestResult(13232399, DataSetType.STRING, 10000, OperationType.INSERT),
        TestResult(13232599, DataSetType.STRING, 10000, OperationType.INSERT),
        TestResult(132352599, DataSetType.STRING, 100_000, OperationType.INSERT),
        TestResult(132352589, DataSetType.STRING, 100_000, OperationType.INSERT),

        TestResult(132325559, DataSetType.REAL, 10000, OperationType.INSERT),
        TestResult(132325579, DataSetType.REAL, 10000, OperationType.INSERT),
        TestResult(1323255579, DataSetType.REAL, 100_000, OperationType.INSERT),
        TestResult(1324355579, DataSetType.REAL, 100_000, OperationType.INSERT),

        TestResult(9245563, DataSetType.STRING, 10000, OperationType.LOAD_ALL),
        TestResult(9245263, DataSetType.STRING, 10000, OperationType.LOAD_ALL),
        TestResult(92452643, DataSetType.STRING, 100_000, OperationType.LOAD_ALL),
        TestResult(92451643, DataSetType.STRING, 100_000, OperationType.LOAD_ALL),

        TestResult(9245663, DataSetType.REAL, 10000, OperationType.LOAD_ALL),
        TestResult(9245663, DataSetType.REAL, 10000, OperationType.LOAD_ALL),
        TestResult(92453263, DataSetType.REAL, 100_000, OperationType.LOAD_ALL),
        TestResult(92451263, DataSetType.REAL, 100_000, OperationType.LOAD_ALL),

        TestResult(6534554, DataSetType.STRING, 10000, OperationType.LOAD_PARAM),
        TestResult(65345154, DataSetType.STRING, 10000, OperationType.LOAD_PARAM),
        TestResult(615345154, DataSetType.STRING, 100_000, OperationType.LOAD_PARAM),
        TestResult(655345154, DataSetType.STRING, 100_000, OperationType.LOAD_PARAM),

        TestResult(45345154, DataSetType.REAL, 10000, OperationType.LOAD_PARAM),
        TestResult(75345154, DataSetType.REAL, 10000, OperationType.LOAD_PARAM),
        TestResult(753452154, DataSetType.REAL, 100_000, OperationType.LOAD_PARAM),
        TestResult(753457154, DataSetType.REAL, 100_000, OperationType.LOAD_PARAM),

        TestResult(231233, DataSetType.STRING, 10000, OperationType.UPDATE),
        TestResult(235233, DataSetType.STRING, 10000, OperationType.UPDATE),
        TestResult(2354233, DataSetType.STRING, 100_000, OperationType.UPDATE),
        TestResult(2358233, DataSetType.STRING, 100_000, OperationType.UPDATE),

        TestResult(235533, DataSetType.REAL, 10000, OperationType.UPDATE),
        TestResult(235633, DataSetType.REAL, 10000, OperationType.UPDATE),
        TestResult(2356533, DataSetType.REAL, 100_000, OperationType.UPDATE),
        TestResult(2356333, DataSetType.REAL, 100_000, OperationType.UPDATE),

        TestResult(42122, DataSetType.STRING, 10000, OperationType.DELETE),
        TestResult(32122, DataSetType.STRING, 10000, OperationType.DELETE),
        TestResult(325122, DataSetType.STRING, 100_000, OperationType.DELETE),
        TestResult(321122, DataSetType.STRING, 100_000, OperationType.DELETE),

        TestResult(21122, DataSetType.REAL, 10000, OperationType.DELETE),
        TestResult(54122, DataSetType.REAL, 10000, OperationType.DELETE),
        TestResult(554122, DataSetType.REAL, 100_000, OperationType.DELETE),
        TestResult(554122, DataSetType.REAL, 100_000, OperationType.DELETE),
    )


    @Test
    fun testAverageCalculationsForNumberPairsOfDifferentKinds() {
        val processedTestResults = testResults.transformToAverages()
        val timeInMillisProcessed = processedTestResults.map { it.timeInMillis }

        for (i in 0 until testResults.size - 2 step 2) {
            val averageForNums = testResults.subList(i, i+2).map { it.timeInMillis }.average().toLong()
            assert(timeInMillisProcessed.contains(averageForNums))
        }
    }

    @Test
    fun testAverageCalculationForManyNumbersOfTheSameKind() {
        val num1 = 13232321249
        val num2 = 143411222L
        val num3 = 43424234234
        val num4 = 43424234234234
        val num5 = 39232313L
        val dataSetType = DataSetType.REAL
        val numOfEntries = 10
        val operationType = OperationType.INSERT
        val testResultsOfTheSameKind = listOf(
            TestResult(num1, dataSetType, numOfEntries, operationType),
            TestResult(num2, dataSetType, numOfEntries, operationType),
            TestResult(num3, dataSetType, numOfEntries, operationType),
            TestResult(num4, dataSetType, numOfEntries, operationType),
            TestResult(num5, dataSetType, numOfEntries, operationType),
        )

        val actualResults = testResultsOfTheSameKind.transformToAverages().map { it.timeInMillis }
        val expectedResult = listOf(num1, num2, num3, num4, num5).average().toLong()

        assertEquals(1, actualResults.size)
        assertEquals(expectedResult, actualResults.first())
    }
}