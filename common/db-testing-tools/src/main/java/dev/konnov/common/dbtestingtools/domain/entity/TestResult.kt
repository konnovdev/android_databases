package dev.konnov.common.dbtestingtools.domain.entity

data class TestResult(
    val timeInMillis: Long,
    val dataSetType: DataSetType,
    val numberOfEntries: Int,
    val operationType: OperationType
)

enum class DataSetType {
    REAL,
    STRING
}

const val SIZE_1k = 1_000
const val SIZE_10k = 10_000
const val SIZE_100k = 100_000
const val SIZE_1M = 1_000_000

enum class OperationType {
    INSERT,
    LOAD_ALL,
    LOAD_PARAM,
    UPDATE,
    DELETE
}

val fakeTestResults = listOf(
    TestResult(13232399, DataSetType.STRING, 10_000, OperationType.INSERT),
    TestResult(1323232199, DataSetType.STRING, 100_000, OperationType.INSERT),
    TestResult(9245563, DataSetType.STRING, 10_000, OperationType.LOAD_ALL),
    TestResult(924556323, DataSetType.STRING, 100_000, OperationType.LOAD_ALL),
    TestResult(6534554, DataSetType.STRING, 10_000, OperationType.LOAD_PARAM),
    TestResult(653441554, DataSetType.STRING, 100_000, OperationType.LOAD_PARAM),
    TestResult(231233, DataSetType.STRING, 10_000, OperationType.UPDATE),
    TestResult(234321233, DataSetType.STRING, 100_000, OperationType.UPDATE),
    TestResult(42122, DataSetType.STRING, 10_000, OperationType.DELETE),
    TestResult(42432122, DataSetType.STRING, 100_000, OperationType.DELETE),
    TestResult(13200099, DataSetType.REAL, 10_000, OperationType.INSERT),
    TestResult(1004320099, DataSetType.REAL, 100_000, OperationType.INSERT),
    TestResult(92450003, DataSetType.REAL, 10_000, OperationType.LOAD_ALL),
    TestResult(90043205563, DataSetType.REAL, 100_000, OperationType.LOAD_ALL),
    TestResult(6534000, DataSetType.REAL, 10_000, OperationType.LOAD_PARAM),
    TestResult(6504320004, DataSetType.REAL, 100_000, OperationType.LOAD_PARAM),
    TestResult(200003, DataSetType.REAL, 10_000, OperationType.UPDATE),
    TestResult(23004320, DataSetType.REAL, 100_000, OperationType.UPDATE),
    TestResult(420000, DataSetType.REAL, 10_000, OperationType.DELETE),
    TestResult(42532000, DataSetType.REAL, 100_000, OperationType.DELETE),
)
