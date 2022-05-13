package dev.konnov.common.dataset.newsreports

import junit.framework.TestCase.assertEquals
import org.junit.Test

internal class NewsReportDataGeneratorTest {

    @Test
    fun itemSizesAreCorrect() {
        assertEquals(10_000, NewsReportDataGenerator.getEntities(10_000).size)
        assertEquals(100_000, NewsReportDataGenerator.getEntities(100_000).size)
        assertEquals(1_000_000, NewsReportDataGenerator.getEntities(1_000_000).size)
    }

    @Test
    fun hasSomeUniqueItems() {
        val items = NewsReportDataGenerator.getEntities(1_000_000)
        val titles = items.map { it.title }
        val descriptions = items.map { it.description }
        val uniqueTitles = mutableSetOf<Title>()
        uniqueTitles.addAll(titles)
        val uniqueDescriptions = mutableSetOf<String>()
        uniqueDescriptions.addAll(descriptions)

        val titleUniqueness = uniqueTitles.size.toDouble() / titles.size.toDouble()
        val descriptionUniqueness = uniqueDescriptions.size.toDouble() / descriptions.size.toDouble()

        println("news report title uniqueness: ${titleUniqueness.toBigDecimal().toPlainString()}")
        println("news report description uniqueness: ${titleUniqueness.toBigDecimal().toPlainString()}")

        assert(titleUniqueness > 0.000005)
        assert(descriptionUniqueness > 0.000005)
    }

    @Test
    fun twoGenerationsGiveTheSameItem() {
        val firstGeneration = NewsReportDataGenerator.getEntities(1_000_000)
        val secondGeneration = NewsReportDataGenerator.getEntities(1_000_000)

        assert(firstGeneration == secondGeneration)
    }
}