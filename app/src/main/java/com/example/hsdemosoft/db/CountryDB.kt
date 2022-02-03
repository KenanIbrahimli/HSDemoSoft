package com.example.hsdemosoft.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hsdemosoft.utils.Constants

@Database(entities = [CountryDBModel::class], version = Constants.DB_VERSION, exportSchema = false)
abstract class CountryDB: RoomDatabase() {

    abstract fun countryDao(): CountryDao

    companion object{
        private var INSTANCE: CountryDB? = null

        fun getInstance(context: Context): CountryDB =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: buildDataBase(context).also { INSTANCE = it}
            }

        private fun buildDataBase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                CountryDB::class.java,
                "CountryDb.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}