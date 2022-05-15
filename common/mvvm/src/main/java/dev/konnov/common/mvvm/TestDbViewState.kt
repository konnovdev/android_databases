package dev.konnov.common.mvvm

import dev.konnov.common.dbtestingtools.domain.entity.TestResult

sealed class TestDBViewState {

    object InProgress: TestDBViewState()

    data class Content(val results: List<TestResult>): TestDBViewState()
}
