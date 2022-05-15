package dev.konnov.feature.realm.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.konnov.common.dbtestingtools.domain.usecase.TestSpeedUseCase
import dev.konnov.common.mvvm.TestDbViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class RealmDbViewModel @Inject constructor(
    @Named("Realm_usecase")
    testSpeedUseCase: TestSpeedUseCase
) : TestDbViewModel(testSpeedUseCase)
