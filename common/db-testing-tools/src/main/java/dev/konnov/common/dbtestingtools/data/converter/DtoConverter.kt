package dev.konnov.common.dbtestingtools.data.converter

interface DtoConverter<Entity, DTO> {

    fun convertToDto(entity: Entity): DTO

    fun convertToEntity(dto: DTO): Entity
}