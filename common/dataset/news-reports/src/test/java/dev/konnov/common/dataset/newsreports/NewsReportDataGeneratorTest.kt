package dev.konnov.common.dataset.newsreports

import dev.konnov.common.dataset.newsreports.data.datasource.NewsReportDataSetDataSource
import junit.framework.TestCase.assertEquals
import org.junit.Test

internal class NewsReportDataGeneratorTest {

    private val newsReportDataSetDataSource = NewsReportDataSetDataSource()

    @Test
    fun itemSizesAreCorrect() {
        assertEquals(10_000, newsReportDataSetDataSource.get(10_000).size)
        assertEquals(100_000, newsReportDataSetDataSource.get(100_000).size)
        assertEquals(1_000_000, newsReportDataSetDataSource.get(1_000_000).size)
    }

    @Test
    fun hasSomeUniqueItems() {
        val items = newsReportDataSetDataSource.get(1_000_000)
        val titles = items.map { it.title }
        val descriptions = items.map { it.description }
        val uniqueTitles = mutableSetOf<String>()
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
        val firstGeneration = newsReportDataSetDataSource.get(1_000_000)
        val secondGeneration = newsReportDataSetDataSource.get(1_000_000)

        assert(firstGeneration == secondGeneration)
    }

    @Test
    fun oldParameterToUpdateExistsInTheCollection() {
        val collection = newsReportDataSetDataSource.get(10_000)
        val oldParamToUpdate = newsReportDataSetDataSource.oldParameterToUpdate

        assert(collection.map { it.title }.contains(oldParamToUpdate))
    }

    @Test
    fun parameterToDeleteExistsInTheCollection() {
        val collection = newsReportDataSetDataSource.get(10_000)
        val paramToDelete = newsReportDataSetDataSource.parameterToDelete

        assert(collection.map { it.title }.contains(paramToDelete))
    }

    @Test
    fun parameterToLoadByExistsInTheCollection() {
        val collection = newsReportDataSetDataSource.get(10_000)
        val paramToLoadBy = newsReportDataSetDataSource.parameterToLoadBy

        assert(collection.map { it.title }.contains(paramToLoadBy))
    }
}