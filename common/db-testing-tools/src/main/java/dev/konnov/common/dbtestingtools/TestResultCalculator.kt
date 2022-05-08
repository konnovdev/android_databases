package dev.konnov.common.dbtestingtools

import javax.inject.Inject

class TestResultCalculator @Inject constructor() {

    fun getResult(
        datasetType: DataSetType,
        operationType: OperationType,
        operation: () -> DataSetSize
    ): TestResult {
        val startTimestamp = System.currentTimeMillis()

        val dataSetSize = operation()

        val endTimestamp = System.currentTimeMillis()
        val timeTaken = endTimestamp - startTimestamp

        return TestResult(timeTaken, datasetType, dataSetSize.value, operationType)
    }

}

@JvmInline
value class DataSetSize(val value: Int)