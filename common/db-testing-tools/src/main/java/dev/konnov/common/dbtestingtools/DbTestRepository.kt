package dev.konnov.common.dbtestingtools

interface DbTestRepository<Entity, Parameter> {

    fun insert(items: List<Entity>): TestResult

    fun loadEverything(): TestResult

    fun loadByParameter(param: Parameter): TestResult

    fun update(param: Parameter, item: Entity): TestResult
}