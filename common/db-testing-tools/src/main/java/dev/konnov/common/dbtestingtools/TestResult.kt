package dev.konnov.common.dbtestingtools

data class TestResult(val timeInMillis: Long, val dataSetType: DataSetType, val numberOfEntries: Int)

enum class DataSetType {
    REAL,
    STRING,
    MIXED
}