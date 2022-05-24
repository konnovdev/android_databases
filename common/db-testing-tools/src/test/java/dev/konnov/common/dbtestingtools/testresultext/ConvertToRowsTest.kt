package dev.konnov.common.dbtestingtools.testresultext

import dev.konnov.common.dbtestingtools.domain.entity.DataSetType
import dev.konnov.common.dbtestingtools.domain.entity.OperationType
import dev.konnov.common.dbtestingtools.domain.entity.TestResult
import dev.konnov.common.dbtestingtools.toListOfRows
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ConvertToRowsTest {

    private val testResults = listOf(
        TestResult(13232399, DataSetType.STRING, 10000, OperationType.INSERT),
        TestResult(132352599, DataSetType.STRING, 100_000, OperationType.INSERT),

        TestResult(132325559, DataSetType.REAL, 10000, OperationType.INSERT),
        TestResult(1323255579, DataSetType.REAL, 100_000, OperationType.INSERT),

        TestResult(9245263, DataSetType.STRING, 10000, OperationType.LOAD_ALL),
        TestResult(92452643, DataSetType.STRING, 100_000, OperationType.LOAD_ALL),

        TestResult(92456631, DataSetType.REAL, 10000, OperationType.LOAD_ALL),
        TestResult(924512631, DataSetType.REAL, 100_000, OperationType.LOAD_ALL),

        TestResult(65345154, DataSetType.STRING, 10000, OperationType.LOAD_PARAM),
        TestResult(615345154, DataSetType.STRING, 100_000, OperationType.LOAD_PARAM),

        TestResult(453451541, DataSetType.REAL, 10000, OperationType.LOAD_PARAM),
        TestResult(7534521541, DataSetType.REAL, 100_000, OperationType.LOAD_PARAM),

        TestResult(235233, DataSetType.STRING, 10000, OperationType.UPDATE),
        TestResult(2358233, DataSetType.STRING, 100_000, OperationType.UPDATE),

        TestResult(2355331, DataSetType.REAL, 10000, OperationType.UPDATE),
        TestResult(23565331, DataSetType.REAL, 100_000, OperationType.UPDATE),

        TestResult(42122, DataSetType.STRING, 10000, OperationType.DELETE),
        TestResult(321122, DataSetType.STRING, 100_000, OperationType.DELETE),

        TestResult(541221, DataSetType.REAL, 10000, OperationType.DELETE),
        TestResult(5541221, DataSetType.REAL, 100_000, OperationType.DELETE),
    )

    @Test
    fun testConvertToRows() {
        val expected =
            listOf(
                listOf("insert 10k", "13232399", "132325559"),
                listOf("load_all 10k", "9245263", "92456631"),
                listOf("load_param 10k", "65345154", "453451541"),
                listOf("update 10k", "235233", "2355331"),
                listOf("delete 10k", "42122", "541221"),
                listOf("insert 100k", "132352599", "1323255579"),
                listOf("load_all 100k", "92452643", "924512631"),
                listOf("load_param 100k", "615345154", "7534521541"),
                listOf("update 100k", "2358233", "23565331"),
                listOf("delete 100k", "321122", "5541221"),
            )

        val actual = testResults.toListOfRows()

        assertEquals(expected, actual)
    }
}