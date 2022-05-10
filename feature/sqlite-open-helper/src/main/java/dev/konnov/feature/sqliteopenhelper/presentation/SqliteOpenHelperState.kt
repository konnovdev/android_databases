package dev.konnov.feature.sqliteopenhelper.presentation

import dev.konnov.common.dbtestingtools.TestResult

sealed class SqliteOpenHelperState {

    object InProgress: SqliteOpenHelperState()

    data class Content(val results: List<TestResult>): SqliteOpenHelperState()
}