package dev.konnov.feature.sqliteopenhelper.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import dev.konnov.common.dataset.weatherlogs.WeatherLog
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

        db?.execSQL(createWeatherLogsTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion != newVersion) {
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_WEATHER");
            onCreate(db);
        }
    }

    fun deleteAllData() {
        writableDatabase.execSQL("DELETE FROM $TABLE_WEATHER")
    }

    fun getAll(): List<WeatherLog> {
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

    fun getByTemperature(temperature: Double): List<WeatherLog> {
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

    fun add(weatherLogs: List<WeatherLog>) {
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

    private companion object {
        const val DATABASE_NAME = "weatherLogSqliteHelperDatabase"
        const val TABLE_WEATHER = "weather_logs"
        const val COLUMN_WEATHER_ID = "id"
        const val COLUMN_WEATHER_TEMP = "temperature"
        const val COLUMN_WEATHER_HUMIDITY = "humidity"
        const val COLUMN_WEATHER_PRESSURE = "pressure"
        const val DATABASE_VERSION = 1
    }
}
