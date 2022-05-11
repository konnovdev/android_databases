package dev.konnov.feature.realm.presentation

import dev.konnov.common.dbtestingtools.TestResult

sealed class RealmViewState {

    object InProgress: RealmViewState()

    data class Content(val results: List<TestResult>): RealmViewState()
}
