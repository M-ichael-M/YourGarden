package com.example.yourgarden.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.yourgarden.data.coupons.Converters
import com.example.yourgarden.data.coupons.CouponsDao
import com.example.yourgarden.data.coupons.CouponsEntity
import com.example.yourgarden.data.song.SongDao
import com.example.yourgarden.data.song.SongEntity

@Database(entities = [SongEntity::class, CouponsEntity::class], version = 4, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GardenDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao
    abstract fun couponsDao(): CouponsDao

    companion object {
        @Volatile
        private var INSTANCE: GardenDatabase? = null

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE songs ADD COLUMN downloadStatus TEXT")
            }
        }

        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("""
                    CREATE TABLE coupons (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        title TEXT NOT NULL,
                        code TEXT NOT NULL,
                        used INTEGER NOT NULL,
                        date INTEGER
                    )
                """)
            }
        }

        private val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE coupons ADD COLUMN description TEXT")
            }
        }

        fun getInstance(context: Context): GardenDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GardenDatabase::class.java,
                    "garden-database"
                )
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}