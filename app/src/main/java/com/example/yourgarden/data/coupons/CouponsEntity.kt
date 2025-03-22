package com.example.yourgarden.data.coupons

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.util.Date

@Entity(tableName = "coupons")
data class CouponsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val code: String, // Zmienione z Int na String
    val used: Boolean,
    val date: Date?
)

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}