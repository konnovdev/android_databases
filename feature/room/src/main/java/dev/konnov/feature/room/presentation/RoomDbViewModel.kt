package dev.konnov.feature.room.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.konnov.common.dbtestingtools.DbInfo
import dev.konnov.common.dbtestingtools.domain.usecase.TestSpeedUseCase
import dev.konnov.common.mvvm.TestDbViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class RoomDbViewModel @Inject constructor(
    @Named("Room_usecase")
    testSpeedUseCase: TestSpeedUseCase
) : TestDbViewModel(testSpeedUseCase) {

    override val dbInfo = DbInfo("Room")
}
