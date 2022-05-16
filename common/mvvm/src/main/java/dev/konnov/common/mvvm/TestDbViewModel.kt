package dev.konnov.common.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.konnov.common.dbtestingtools.domain.entity.SIZE_100k
import dev.konnov.common.dbtestingtools.domain.entity.SIZE_10k
import dev.konnov.common.dbtestingtools.domain.usecase.TestSpeedUseCase
import dev.konnov.common.mvvm.TestDbViewState.Content
import dev.konnov.common.mvvm.TestDbViewState.InProgress
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class TestDbViewModel(private val testSpeedUseCase: TestSpeedUseCase) : ViewModel() {

    private val _state = MutableStateFlow<TestDbViewState>(InProgress)
    val state: StateFlow<TestDbViewState> = _state

    val testSizes = listOf(SIZE_10k, SIZE_100k)


    fun testDbSpeed() {
        viewModelScope.launch {
            val testResults = testSpeedUseCase(TEST_ITERATIONS, testSizes)
            _state.value = Content(testResults)
        }
    }

    private companion object {
        const val TEST_ITERATIONS = 5
    }
}