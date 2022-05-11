package dev.konnov.feature.room.presentation

import dev.konnov.common.dbtestingtools.TestResult

sealed class RoomViewState {

    object InProgress: RoomViewState()

    data class Content(val results: List<TestResult>): RoomViewState()
}