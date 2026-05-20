package com.example.yourgarden.data

import android.annotation.SuppressLint
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
import java.text.SimpleDateFormat

@Database(entities = [SongEntity::class, CouponsEntity::class], version = 5, exportSchema = false)
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

        private val MIGRATION_4_5 = object : Migration(4, 5) {
            @SuppressLint("SimpleDateFormat")
            override fun migrate(db: SupportSQLiteDatabase) {
                // Ustal który kupon powinien mieć datę (Love me)
                val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                val parsedDate: Long? = dateFormat.parse("2023-10-27 10:30:00")?.time

                // Wyczyść wszystkie kupony
                db.execSQL("DELETE FROM coupons")

                // Wstaw kupony w żądanym stanie
                db.execSQL("""
                    INSERT INTO coupons (title, code, description, used, date) 
                    VALUES ('BEST BIRTHDAY OF YOUR LIFE', '28062025', 'Dostępne tylko 28.03.2025!', 1, NULL)
                """)
                db.execSQL("""
                    INSERT INTO coupons (title, code, description, used, date) 
                    VALUES ('SWEET SURPRISE', '1', 'Aktywować najpóźniej 24h przed randką!', 1, NULL)
                """)
                db.execSQL("""
                    INSERT INTO coupons (title, code, description, used, date) 
                    VALUES ('DINNER SURPRISE (outside)', '2', 'Aktywować najpóźniej 24h przed randką!', 0, NULL)
                """)
                db.execSQL("""
                    INSERT INTO coupons (title, code, description, used, date) 
                    VALUES ('DINNER SURPRISE (at home)', '3', 'Aktywować najpóźniej 24h przed randką!', 0, NULL)
                """)
                db.execSQL("""
                    INSERT INTO coupons (title, code, description, used, date) 
                    VALUES ('FILM SURPRISE', '4', 'Aktywować najpóźniej 24h przed randką!', 0, NULL)
                """)
                db.execSQL("""
                    INSERT INTO coupons (title, code, description, used, date) 
                    VALUES ('DATE SURPRISE (outside)', '5', 'Aktywować najpóźniej 48h przed randką, dostępne jak jest ciepło!', 0, NULL)
                """)
                db.execSQL("""
                    INSERT INTO coupons (title, code, description, used, date) 
                    VALUES ('DATE SURPRISE (at home)', '6', 'Aktywować najpóźniej 48h przed randką!', 0, NULL)
                """)
                db.execSQL("""
                    INSERT INTO coupons (title, code, description, used, date) 
                    VALUES ('SPICY SURPRISE 😏', '69', 'Aktywować najpóźniej 2 tygodnie przed randką!', 0, NULL)
                """)
                db.execSQL("""
                    INSERT INTO coupons (title, code, description, used, date) 
                    VALUES ('FREAKY SURPRISE', '7', 'Aktywować najpóźniej 2 tygodnie przed randką!', 0, NULL)
                """)
                db.execSQL("""
                    INSERT INTO coupons (title, code, description, used, date) 
                    VALUES ('DIY SURPRISE (from me)', '8', 'Aktywować najpóźniej 72h przed randką!', 1, NULL)
                """)
                db.execSQL("""
                    INSERT INTO coupons (title, code, description, used, date) 
                    VALUES ('DIY SURPRISE (we make it)', '9', 'Aktywować najpóźniej 48h przed randką!', 0, NULL)
                """)
                db.execSQL("""
                    INSERT INTO coupons (title, code, description, used, date) 
                    VALUES ('SPA SURPRISE', '10', 'Aktywować najpóźniej 48h przed randką!', 0, NULL)
                """)
                db.execSQL("""
                    INSERT INTO coupons (title, code, description, used, date) 
                    VALUES ('BEAUTY SPOT SURPRISE', '11', 'Aktywować tylko w ładną pogodę (zalecane w Warszawie)!', 0, NULL)
                """)
                db.execSQL("""
                    INSERT INTO coupons (title, code, description, used, date) 
                    VALUES ('COFFEHOUSE TEST', '12', 'Aktywować najpóźniej 24h przed randką!', 0, NULL)
                """)
                db.execSQL("""
                    INSERT INTO coupons (title, code, description, used, date) 
                    VALUES ('LOVE ME', '22062024', 'Nigdy nie przestanę!', 0, $parsedDate)
                """)
                db.execSQL("""
                    INSERT INTO coupons (title, code, description, used, date) 
                    VALUES ('COSY SURPRISE', '13', 'Aktywować 48h przed randką!', 0, NULL)
                """)
                db.execSQL("""
                    INSERT INTO coupons (title, code, description, used, date) 
                    VALUES ('KREMÓWKI', '2137', 'Aktywować 24h przed randką!', 1, NULL)
                """)
                db.execSQL("""
                    INSERT INTO coupons (title, code, description, used, date) 
                    VALUES ('BROWNIE', '14', 'Aktywować najpóźniej 72h przed randką!', 0, NULL)
                """)
            }
        }

        fun getInstance(context: Context): GardenDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GardenDatabase::class.java,
                    "garden-database"
                )
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}