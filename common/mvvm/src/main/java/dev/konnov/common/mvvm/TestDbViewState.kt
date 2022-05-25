package dev.konnov.common.mvvm

import dev.konnov.common.dbtestingtools.DbInfo
import dev.konnov.common.dbtestingtools.domain.entity.TestResult

sealed class TestDbViewState {

    object InProgress: TestDbViewState()

    data class Content(val dbInfo: DbInfo, val results: List<TestResult>): TestDbViewState()
}