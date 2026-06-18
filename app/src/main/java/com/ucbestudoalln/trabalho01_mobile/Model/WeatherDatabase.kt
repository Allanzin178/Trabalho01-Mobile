package com.ucbestudoalln.trabalho01_mobile.Model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherDatabase(context: Context) : SQLiteOpenHelper(context, "weather_db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE weather_history (id INTEGER PRIMARY KEY AUTOINCREMENT, cep TEXT, city TEXT, temperature REAL, timestamp INTEGER)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS weather_history")
        onCreate(db)
    }

    suspend fun insertHistory(history: WeatherHistory) = withContext(Dispatchers.IO) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("cep", history.cep)
            put("city", history.city)
            put("temperature", history.temperature)
            put("timestamp", history.timestamp)
        }
        db.insert("weather_history", null, values)
    }

    suspend fun getAllHistory(): List<WeatherHistory> = withContext(Dispatchers.IO) {
        val list = mutableListOf<WeatherHistory>()
        val db = readableDatabase
        val cursor = db.query("weather_history", null, null, null, null, null, "timestamp DESC")
        
        cursor.use { c ->
            while (c.moveToNext()) {
                list.add(WeatherHistory(
                    id = c.getInt(c.getColumnIndexOrThrow("id")),
                    cep = c.getString(c.getColumnIndexOrThrow("cep")),
                    city = c.getString(c.getColumnIndexOrThrow("city")),
                    temperature = c.getDouble(c.getColumnIndexOrThrow("temperature")),
                    timestamp = c.getLong(c.getColumnIndexOrThrow("timestamp"))
                ))
            }
        }
        list
    }

    suspend fun deleteAll() = withContext(Dispatchers.IO) {
        writableDatabase.delete("weather_history", null, null)
    }
}
