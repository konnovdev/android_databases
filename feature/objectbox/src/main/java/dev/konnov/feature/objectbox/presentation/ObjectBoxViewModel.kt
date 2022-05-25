package dev.konnov.feature.objectbox.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.konnov.common.dbtestingtools.DbInfo
import dev.konnov.common.dbtestingtools.domain.usecase.TestSpeedUseCase
import dev.konnov.common.mvvm.TestDbViewModel
import javax.inject.Inject
import javax.inject.Named


@HiltViewModel
class ObjectBoxViewModel @Inject constructor(
    @Named("Objectbox_usecase")
    testSpeedUseCase: TestSpeedUseCase
): TestDbViewModel(testSpeedUseCase) {

    override val dbInfo = DbInfo("Object Box")

    // todo перевести работу на io тред
    override val testIterations = 1
}