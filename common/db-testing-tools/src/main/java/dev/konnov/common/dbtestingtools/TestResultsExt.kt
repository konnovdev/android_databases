package dev.konnov.common.dbtestingtools

import dev.konnov.common.dbtestingtools.domain.entity.TestResult
import java.util.*

fun List<TestResult>.transformToAverages(): List<TestResult> =
    this.groupBy {
        it.dataSetType
    }
        .flatMap {
            it.value.groupBy {
                it.numberOfEntries
            }.flatMap {
                it.value.groupBy {
                    it.operationType
                }.map {
                    val averageTimeInMillis = it.value.map { it.timeInMillis }.average().toLong()
                    val dataSetType = it.value.first().dataSetType
                    val operationTypes = it.value.first().operationType
                    val numOfEntries = it.value.first().numberOfEntries
                    TestResult(averageTimeInMillis, dataSetType, numOfEntries, operationTypes)
                }
            }
        }

fun List<TestResult>.toListOfRows(): List<List<String>> {

    val rows = mutableListOf<List<String>>()

    this.groupBy { testResult ->
        testResult.numberOfEntries
    }
        .map {
            it.value.groupBy { testResult ->
                testResult.operationType
            }.onEach {
                rows.add(
                    listOf(
                        "${it.key.toString().lowercase(Locale.ENGLISH)} ${it.value.first().numberOfEntries.shorten()}"
                    ) +
                            (it.value.map {
                                listOf(it.timeInMillis.toString())
                            }.flatten())
                )
            }
        }

    return rows
}

internal fun Int.shorten(): String =
    when {
        this in 1_000..999_999 -> this.toString().dropLast(3) + "k"
        this in 1_000_000..999_999_999 -> this.toString().dropLast(6) + "m"
        else -> this.toString()
    }