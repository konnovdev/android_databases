package dev.konnov.common.dbtestingtools

data class TestResult(val timeInMillis: Long, val dataSetType: DataSetType, val numberOfEntries: Int)

enum class DataSetType {
    REAL,
    STRING,
    MIXED
}

const val SIZE_10k = 10_000
const val SIZE_100k = 100_000
const val SIZE_1M = 1_000_000