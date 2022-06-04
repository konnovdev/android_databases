package dev.konnov.common.dbtestingtools.testresultextext

import dev.konnov.common.dbtestingtools.shorten
import junit.framework.TestCase
import org.junit.Test

class ShortenExtTest {

    @Test // todo change to parametrized test
    fun `shorten number between 1000 and 999_999 EXPECT k postfix`() {
        val expected = "30k"
        val actual = 30_000.shorten()

        val expected2 = "3k"
        val actual2 = 3_000.shorten()

        val expected3 = "120k"
        val actual3 = 120_000.shorten()

        val expected4 = "30"
        val actual4 = 30.shorten()

        TestCase.assertEquals(expected, actual)
        TestCase.assertEquals(expected2, actual2)
        TestCase.assertEquals(expected3, actual3)
        TestCase.assertEquals(expected4, actual4)
    }
}