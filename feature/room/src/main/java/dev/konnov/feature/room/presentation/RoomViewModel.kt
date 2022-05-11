package dev.konnov.feature.room.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.konnov.common.dataset.weatherlogs.WeatherLogDataGenerator
import dev.konnov.common.dbtestingtools.SIZE_100k
import dev.konnov.feature.room.data.WeatherDataRepository
import dev.konnov.feature.room.presentation.RoomViewState.InProgress
import dev.konnov.feature.room.presentation.RoomViewState.Content
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val weatherDataRepository: WeatherDataRepository
) : ViewModel() {

    private val _state = MutableStateFlow<RoomViewState>(InProgress)
    val state: StateFlow<RoomViewState> = _state

    fun testDbSpeed() {
        testRoomWorks()
    }

    // TODO delete this, it's just for test
    private fun testRoomWorks() {
        viewModelScope.launch {
            val testResult = weatherDataRepository.insert(WeatherLogDataGenerator.getEntities(SIZE_100k))
            _state.value = Content(listOf(testResult))
        }
    }
}