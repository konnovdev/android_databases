package dev.konnov.common.dbtestingtools.data.converter

import junit.framework.TestCase.assertEquals
import org.junit.Test

internal class MockDtoConverterTest {

    private val mockDtoConverter = MockDtoConverter<TestEntity>()

    @Test
    fun entityIsTheSame() {
        val entity = TestEntity(23)

        assertEquals(entity, mockDtoConverter.convert(entity))
    }


    @Test
    fun entityIsNotTheSame() {
        val entity = TestEntity(23)
        val anotherEntity = TestEntity(2332)

        assert(entity != mockDtoConverter.convert(anotherEntity))
    }

    private data class TestEntity(val param: Int)
}