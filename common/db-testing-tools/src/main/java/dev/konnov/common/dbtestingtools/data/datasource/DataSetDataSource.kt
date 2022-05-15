package dev.konnov.common.dbtestingtools.data.datasource

interface DataSetDataSource<Param, Data> {

    fun get(size: Int): List<Data>

    val oldParameterToUpdate: Param

    val objectToInsertAsUpdate: Data

    val parameterToDelete: Param

    val parameterToLoadBy: Param
}
