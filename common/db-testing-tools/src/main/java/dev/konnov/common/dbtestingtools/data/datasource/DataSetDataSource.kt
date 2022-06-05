package dev.konnov.common.dbtestingtools.data.datasource

interface DataSetDataSource<Param, Entity> {

    fun get(size: Int): List<Entity>

    val oldParameterToUpdate: Param

    val objectToInsertAsUpdate: Entity

    val parameterToDelete: Param

    val parameterToLoadBy: Param
}
