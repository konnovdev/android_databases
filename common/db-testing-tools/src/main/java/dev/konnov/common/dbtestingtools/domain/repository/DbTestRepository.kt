package dev.konnov.common.dbtestingtools.domain.repository

import dev.konnov.common.dbtestingtools.domain.entity.TestResult

interface DbTestRepository {

    suspend fun insert(entitiesSize: Int): TestResult

    suspend fun loadAll(): TestResult

    suspend fun loadByParameter(): TestResult

    suspend fun update(): TestResult

    suspend fun delete(): TestResult
}
