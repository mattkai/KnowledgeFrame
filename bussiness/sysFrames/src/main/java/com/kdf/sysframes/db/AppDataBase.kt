package com.kdf.sysframes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kdf.sysframes.data.CnStudent
import com.kdf.sysframes.db.dao.CnStuDao

@Database(entities = [CnStudent::class], version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun getCnStuDao(): CnStuDao

    companion object {
        fun getInstance(context: Context): AppDataBase {
            val dataBase = Room.databaseBuilder(context.applicationContext,AppDataBase::class.java,
                "app_data_base.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
            return dataBase
        }
    }

}