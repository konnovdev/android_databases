package dev.konnov.common.dbtestingtools.data.converter

interface DtoConverter<Entity, DTO> {

    fun convert(entity: Entity): DTO
}