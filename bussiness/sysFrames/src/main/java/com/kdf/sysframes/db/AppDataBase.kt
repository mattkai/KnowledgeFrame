package com.kdf.sysframes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kdf.sysframes.data.CnStudent
import com.kdf.sysframes.db.dao.CnStuDao

@Database(entities = [CnStudent::class], version = 2,exportSchema = false)
abstract class AppDataBase: RoomDatabase() {

    abstract fun getCnStuDao(): CnStuDao

    companion object {
        fun getInstance(context: Context): AppDataBase {
            val dataBase = Room.databaseBuilder(context.applicationContext,AppDataBase::class.java,
                "app_data_base.db")
                .allowMainThreadQueries()
                .addMigrations(*getMigrationHistory())
                .fallbackToDestructiveMigration()
                .build()
            return dataBase
        }

        private fun getMigrationHistory(): Array<Migration> {
            return arrayOf(Migration1_2)
        }

        private val Migration1_2: Migration = object: Migration(1,2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE t_student  ADD COLUMN sex  INTEGER NOT NULL DEFAULT 0")
            }
        }

    }

}