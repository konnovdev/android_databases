package dev.konnov.common.dbtestingtools.data.converter

import dev.konnov.common.dbtestingtools.domain.entity.DataSetType
import dev.konnov.common.dbtestingtools.domain.entity.OperationType
import dev.konnov.common.dbtestingtools.domain.entity.TestResult
import javax.inject.Inject

class TestResultConverter @Inject constructor() {

    suspend inline fun <reified T : Any> convert(
        entities: List<T>,
        operationType: OperationType,
        operation: suspend () -> Unit // TODO походу можно удалить suspend
    ): TestResult {
        val startTimestamp = System.currentTimeMillis()

        operation()

        val endTimestamp = System.currentTimeMillis()
        val timeTaken = endTimestamp - startTimestamp

        val datasetType = if (
            entities.first()::class.java.declaredFields.first().type.equals(String::class.java) // TODO check if this works
        ) {
            DataSetType.STRING
        } else {
            DataSetType.REAL
        }

        return TestResult(timeTaken, datasetType, entities.size, operationType)
    }
}
