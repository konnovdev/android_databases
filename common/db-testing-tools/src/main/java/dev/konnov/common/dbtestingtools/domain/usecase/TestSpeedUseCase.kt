package dev.konnov.common.dbtestingtools.domain.usecase

import dev.konnov.common.dbtestingtools.domain.entity.TestResult
import dev.konnov.common.dbtestingtools.domain.repository.DbTestRepository
import dev.konnov.common.dbtestingtools.transformToAverages
import javax.inject.Inject

class TestSpeedUseCase @Inject constructor(
    private vararg val repositories: DbTestRepository
) {

    suspend operator fun invoke(testIterations: Int, testSizes: List<Int>): List<TestResult> {
        val results = mutableListOf<TestResult>()

        repositories.forEach { testSpeedRepository ->
            for (i in 0 until testIterations) {
                testSizes.forEach { testSize ->
                    results += testSpeedRepository.test(testSize)
                }
            }
        }
        return results.transformToAverages()
    }

    private suspend fun DbTestRepository.test(entriesSize: Int): List<TestResult> {
        val insertResults = insert(entriesSize)
        val loadEverythingResult = loadAll()
        val updateResult = update()
        val loadByParameterResult = loadByParameter()
        val deleteResult = delete()

        return listOf(
            insertResults,
            loadEverythingResult,
            updateResult,
            loadByParameterResult,
            deleteResult
        )
    }
}
