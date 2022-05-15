package dev.konnov.feature.sqliteopenhelper.data.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import dev.konnov.common.dataset.newsreports.data.model.NewsReport
import dev.konnov.common.dataset.weatherlogs.data.model.WeatherLog
import javax.inject.Inject

class SqliteOpenHelperDbManager @Inject constructor(
    context: Context
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createWeatherLogsTableQuery = "CREATE TABLE " + TABLE_WEATHER +
                "(" +
                COLUMN_WEATHER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_WEATHER_TEMP + " REAL, " +
                COLUMN_WEATHER_HUMIDITY + " REAL, " +
                COLUMN_WEATHER_PRESSURE + " REAL" +
                ")";

        val createNewsReportsTableQuery = "CREATE TABLE " + TABLE_NEWS_REPORT +
                "(" +
                COLUMN_NEWS_REPORT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NEWS_REPORT_TITLE + " TEXT, " +
                COLUMN_NEWS_REPORT_DESCRIPTION + " TEXT " +
                ")";

        db?.execSQL(createWeatherLogsTableQuery)
        db?.execSQL(createNewsReportsTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion != newVersion) {
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_WEATHER");
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_NEWS_REPORT");
            onCreate(db);
        }
    }

    fun deleteAllWeatherData() {
        writableDatabase.execSQL("DELETE FROM $TABLE_WEATHER")
    }

    fun deleteAllNewsReportsData() {
        writableDatabase.execSQL("DELETE FROM $TABLE_NEWS_REPORT")
    }

    /**
     * @returns Int - number of affected rows
     */
    fun deleteWeatherByTemperature(temperature: Double): Int =
        writableDatabase.delete(
            TABLE_WEATHER,
            "$COLUMN_WEATHER_TEMP = ?",
            arrayOf(temperature.toString())
        )

    /**
     * @returns Int - number of affected rows
     */
    fun deleteNewsByTitle(title: String): Int =
        writableDatabase.delete(
            TABLE_NEWS_REPORT,
            "$COLUMN_NEWS_REPORT_TITLE = ?",
            arrayOf(title)
        )

    fun getAllWeatherData(): List<WeatherLog> {
        val weatherLogsSelectQuery = "SELECT * FROM $TABLE_WEATHER"
        val weatherLogs = mutableListOf<WeatherLog>()

        val cursor = readableDatabase.rawQuery(weatherLogsSelectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val temperature =
                    cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_WEATHER_TEMP))
                val humidity =
                    cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_WEATHER_HUMIDITY))
                val pressure =
                    cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_WEATHER_PRESSURE))
                weatherLogs.add(WeatherLog(temperature, humidity, pressure))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return weatherLogs
    }

    fun getAllNewsData(): List<NewsReport> {
        val newsReportsSelectQuery = "SELECT * FROM $TABLE_NEWS_REPORT"
        val newsReports = mutableListOf<NewsReport>()

        val cursor = readableDatabase.rawQuery(newsReportsSelectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val title =
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NEWS_REPORT_TITLE))
                val description =
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NEWS_REPORT_DESCRIPTION))
                newsReports.add(NewsReport(title, description))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return newsReports
    }

    fun getWeatherByTemperature(temperature: Double): List<WeatherLog> {
        val weatherLogsSelectQuery =
            "SELECT * " +
                    "FROM $TABLE_WEATHER " +
                    "WHERE $COLUMN_WEATHER_TEMP = $temperature"
        val weatherLogs = mutableListOf<WeatherLog>()

        val cursor = readableDatabase.rawQuery(weatherLogsSelectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val temp = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_WEATHER_TEMP))
                val humidity =
                    cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_WEATHER_HUMIDITY))
                val pressure =
                    cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_WEATHER_PRESSURE))
                weatherLogs.add(WeatherLog(temp, humidity, pressure))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return weatherLogs
    }

    fun getNewsByTitle(title: String): List<NewsReport> {
        val newsReportsSelectQuery =
            "SELECT * " +
                    "FROM $TABLE_NEWS_REPORT " +
                    "WHERE $COLUMN_NEWS_REPORT_TITLE = '$title'"
        val newsReports = mutableListOf<NewsReport>()

        val cursor = readableDatabase.rawQuery(newsReportsSelectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NEWS_REPORT_TITLE))
                val description =
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NEWS_REPORT_DESCRIPTION))
                newsReports.add(NewsReport(title, description))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return newsReports
    }

    fun addWeather(weatherLogs: List<WeatherLog>) {
        val db = writableDatabase

        db.beginTransaction()
        weatherLogs.forEach {
            val values = ContentValues()
            values.put(COLUMN_WEATHER_TEMP, it.temperature)
            values.put(COLUMN_WEATHER_HUMIDITY, it.humidity)
            values.put(COLUMN_WEATHER_PRESSURE, it.pressure)
            db.insertOrThrow(TABLE_WEATHER, null, values)
        }
        db.setTransactionSuccessful()
        db.endTransaction()
    }

    fun addNewsReports(newsReports: List<NewsReport>) {
        val db = writableDatabase

        db.beginTransaction()
        newsReports.forEach {
            val values = ContentValues()
            values.put(COLUMN_NEWS_REPORT_TITLE, it.title)
            values.put(COLUMN_NEWS_REPORT_DESCRIPTION, it.description)
            db.insertOrThrow(TABLE_NEWS_REPORT, null, values)
        }
        db.setTransactionSuccessful()
        db.endTransaction()
    }

    /**
     * @returns Int - number of affected rows
     */
    fun updateByTemperature(temperature: Double, weatherLog: WeatherLog): Int {
        val contentValues =
            ContentValues().also {
                it.put(COLUMN_WEATHER_TEMP, weatherLog.temperature)
                it.put(COLUMN_WEATHER_HUMIDITY, weatherLog.humidity)
                it.put(COLUMN_WEATHER_PRESSURE, weatherLog.pressure)
            }

        return writableDatabase.update(
            TABLE_WEATHER,
            contentValues,
            "$COLUMN_WEATHER_TEMP = ?",
            arrayOf(temperature.toString())
        )
    }

    /**
     * @returns Int - number of affected rows
     */
    fun updateByTitle(title: String, newsReport: NewsReport): Int {
        val contentValues =
            ContentValues().also {
                it.put(COLUMN_NEWS_REPORT_TITLE, newsReport.title)
                it.put(COLUMN_NEWS_REPORT_DESCRIPTION, newsReport.description)
            }

        return writableDatabase.update(
            TABLE_NEWS_REPORT,
            contentValues,
            "$COLUMN_NEWS_REPORT_TITLE = ?",
            arrayOf(title)
        )
    }

    private companion object {
        const val DATABASE_NAME = "sqliteHelperDatabase"
        const val TABLE_WEATHER = "weather_logs"
        const val COLUMN_WEATHER_ID = "id"
        const val COLUMN_WEATHER_TEMP = "temperature"
        const val COLUMN_WEATHER_HUMIDITY = "humidity"
        const val COLUMN_WEATHER_PRESSURE = "pressure"

        const val TABLE_NEWS_REPORT = "news_report"
        const val COLUMN_NEWS_REPORT_ID = "id"
        const val COLUMN_NEWS_REPORT_TITLE = "title"
        const val COLUMN_NEWS_REPORT_DESCRIPTION = "description"

        const val DATABASE_VERSION = 1
    }
}
