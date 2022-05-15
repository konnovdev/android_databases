package dev.konnov.common.dbtestingtools.data.datasource

interface DbDataSource<Param, DTO> {

    suspend fun insert(items: List<DTO>)

    suspend fun loadAll()

    suspend fun loadByParameter(param: Param)

    suspend fun update(param: Param, objectToInsert: DTO)

    suspend fun delete(param: Param)

    suspend fun deleteAll()
}
