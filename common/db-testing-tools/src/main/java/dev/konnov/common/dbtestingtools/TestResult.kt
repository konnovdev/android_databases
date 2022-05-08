package dev.konnov.common.dbtestingtools

data class TestResult(
    val timeInMillis: Long,
    val dataSetType: DataSetType,
    val numberOfEntries: Int,
    val testType: TestType
)

enum class DataSetType {
    REAL,
    STRING,
    MIXED
}

const val SIZE_10k = 10_000
const val SIZE_100k = 100_000
const val SIZE_1M = 1_000_000

enum class TestType {
    INSERT,
    LOAD_ALL,
    LOAD_BY_PARAM,
    UPDATE,
    DELETE
}