package dev.konnov.common.dbtestingtools

fun List<TestResult>.group() =
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