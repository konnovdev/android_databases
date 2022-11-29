package dev.konnov.feature.room.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.konnov.feature.room.data.model.NewsReportDto

@Dao
interface NewsReportDao {

    @Query("SELECT * FROM newsReport")
    suspend fun getAll(): List<NewsReportDto>

    @Query("SELECT * FROM newsReport WHERE title = :title")
    suspend fun getNewsReportByTitle(title: String): List<NewsReportDto>

    @Query(
        "UPDATE newsReport " +
                "SET title = :newTitle," +
                "description = :newDescription " +
                "WHERE title = :title"
    )
    suspend fun updateByTitle(
        title: String,
        newTitle: String,
        newDescription: String
    )

    @Insert
    suspend fun addNewsReports(newsReports: List<NewsReportDto>)

    @Query("DELETE FROM newsReport")
    suspend fun deleteAllNewsReportData()

    @Query("DELETE FROM newsReport WHERE title = :title")
    suspend fun deleteByTitle(title: String)
}
