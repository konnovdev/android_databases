package dev.konnov.feature.sqliteopenhelper.presentation

sealed class SqliteOpenHelperState {

    object InProgress: SqliteOpenHelperState()

    data class Content(val text: String): SqliteOpenHelperState()
}