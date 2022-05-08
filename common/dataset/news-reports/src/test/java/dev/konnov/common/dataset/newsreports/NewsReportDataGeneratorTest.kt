package dev.konnov.common.dataset.newsreports

import junit.framework.TestCase
import org.junit.Test

internal class NewsReportDataGeneratorTest {

    @Test
    fun itemSizesAreCorrect() {
        TestCase.assertEquals(10_000, NewsReportDataGenerator.getEntities(10_000).size)
        TestCase.assertEquals(100_000, NewsReportDataGenerator.getEntities(100_000).size)
        TestCase.assertEquals(1_000_000, NewsReportDataGenerator.getEntities(1_000_000).size)
    }

    @Test
    fun hasSomeUniqueItems() {
        val items = NewsReportDataGenerator.getEntities(1_000_000)
        val titles = items.map { it.title }
        val descriptions = items.map { it.description }
        val uniqueTitles = mutableSetOf<Title>()
        uniqueTitles.addAll(titles)

        val titleUniqueness = uniqueTitles.size.toDouble() / titles.size.toDouble()
        println("news report uniqueness: ${titleUniqueness.toBigDecimal().toPlainString()}")

        assert(titleUniqueness > 0.000005)
    }

    @Test
    fun twoGenerationsGiveTheSameItem() {
        val firstGeneration = NewsReportDataGenerator.getEntities(1_000_000)
        val secondGeneration = NewsReportDataGenerator.getEntities(1_000_000)

        assert(firstGeneration == secondGeneration)
    }
}