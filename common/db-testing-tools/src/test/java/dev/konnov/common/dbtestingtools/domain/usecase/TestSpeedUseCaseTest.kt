package dev.konnov.common.dbtestingtools.domain.usecase

import dev.konnov.common.dbtestingtools.domain.entity.DataSetType
import dev.konnov.common.dbtestingtools.domain.entity.OperationType
import dev.konnov.common.dbtestingtools.domain.entity.TestResult
import dev.konnov.common.dbtestingtools.domain.repository.DbTestRepository
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.inOrder
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class TestSpeedUseCaseTest {

    private val testSizes = listOf(100, 1000)

    private val dbTestRepositoryOne: DbTestRepository = mock()
    private val dbTestRepositoryTwo: DbTestRepository = mock()
    private val repositories = listOf(dbTestRepositoryOne, dbTestRepositoryTwo)

    private val mockTestResult = TestResult(0, DataSetType.REAL, 0, OperationType.INSERT)

    private val testSpeedUseCase = TestSpeedUseCase(*repositories.toTypedArray())

    @Test
    fun testRepositoryMethodsInvokedInOrder() = runTest {
        forEach { testSize, repository ->
            whenever(repository.insert(testSize)).thenReturn(mockTestResult)
            whenever(repository.loadAll()).thenReturn(mockTestResult)
            whenever(repository.loadByParameter()).thenReturn(mockTestResult)
            whenever(repository.update()).thenReturn(mockTestResult)
            whenever(repository.delete()).thenReturn(mockTestResult)
        }

        testSpeedUseCase(TEST_ITERATIONS, testSizes)

        val invocationTimes = TEST_ITERATIONS * testSizes.size
        val inOrder = inOrder(dbTestRepositoryOne, dbTestRepositoryTwo)
        forEach { testSize, repository ->
            inOrder.verify(repository).insert(testSize)
        }
        repositories.forEach { repository ->
            verify(repository, times(invocationTimes)).loadAll()
            verify(repository, times(invocationTimes)).loadByParameter()
            verify(repository, times(invocationTimes)).loadByParameter()
            verify(repository, times(invocationTimes)).update()
            verify(repository, times(invocationTimes)).delete()
        }
//        verifyNoInteractions(dbTestRepositoryOne, dbTestRepositoryTwo) TODO add verify no interactions (for some reason it's giving an error)
    }

    private inline fun forEach(action: (Int, DbTestRepository) -> Unit) {
        repositories.forEach { repository ->
            testSizes.forEach { testSize ->
                action(testSize, repository)
            }
        }
    }

    private companion object {
        const val TEST_ITERATIONS = 1
    }
}