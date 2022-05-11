package dev.konnov.common.dbtestingtools

interface DbTestRepository<Entity, Parameter> {

    suspend fun insert(items: List<Entity>): TestResult

    suspend fun loadEverything(): TestResult

    suspend fun loadByParameter(param: Parameter): TestResult

    suspend fun update(param: Parameter, item: Entity): TestResult

    suspend fun delete(param: Parameter): TestResult
}