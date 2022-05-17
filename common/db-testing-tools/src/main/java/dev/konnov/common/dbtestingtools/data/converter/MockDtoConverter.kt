package dev.konnov.common.dbtestingtools.data.converter

import javax.inject.Inject

class MockDtoConverter<Entity> @Inject constructor(): DtoConverter<Entity, Entity> {

    override fun convertToDto(entity: Entity): Entity = entity

    override fun convertToEntity(dto: Entity): Entity = dto
}