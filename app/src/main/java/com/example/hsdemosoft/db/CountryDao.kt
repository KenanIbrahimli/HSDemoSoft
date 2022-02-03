package com.example.hsdemosoft.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CountryDao {

    @Insert
    fun addDataToDb(item:CountryDBModel)

    @Query("SELECT * FROM CountryList")
    fun getAllData(): CountryDBModel?

    @Update
    fun updateDataInDb(item: CountryDBModel)
}