package dev.konnov.common.dbtestingtools.data.converter

import javax.inject.Inject

class MockDtoConverter<Entity> @Inject constructor(): DtoConverter<Entity, Entity> {

    override fun convert(entity: Entity): Entity = entity
}