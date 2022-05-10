package dev.konnov.common.dbtestingtools

data class TestResult(
    val timeInMillis: Long,
    val dataSetType: DataSetType,
    val numberOfEntries: Int,
    val operationType: OperationType
)

enum class DataSetType {
    REAL,
    STRING,
    MIXED
}

const val SIZE_10k = 10_000
const val SIZE_100k = 100_000
const val SIZE_1M = 1_000_000

enum class OperationType {
    INSERT,
    LOAD_ALL,
    LOAD_BY_PARAM,
    UPDATE,
    DELETE
}

val fakeTestResults = listOf(
    TestResult(13232399, DataSetType.STRING, 10000, OperationType.INSERT),
    TestResult(9245563, DataSetType.STRING, 10000, OperationType.LOAD_ALL),
    TestResult(6534554, DataSetType.STRING, 10000, OperationType.LOAD_BY_PARAM),
    TestResult(231233, DataSetType.STRING, 10000, OperationType.UPDATE),
    TestResult(42122, DataSetType.STRING, 10000, OperationType.DELETE),
)
