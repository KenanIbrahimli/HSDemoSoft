package com.example.hsdemosoft.db

import com.example.hsdemosoft.MainApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class CountryRepository {

    @Inject
    lateinit var countryDbSettings: CountryDao


    init {
        MainApplication.appComponents.inject(this)
    }

    suspend fun addDataToDb(item: String): Int = withContext(Dispatchers.IO){
        try {
            val value = CountryDBModel(
                id =  0,
                respose = item
            )
            countryDbSettings.addDataToDb(value)
            100
        }catch (ex:Exception){
            0
        }
    }

    suspend fun getData(): CountryDBModel? = withContext(Dispatchers.IO){
        try {
            val value = countryDbSettings.getAllData()
            value
        } catch (ex:Exception){
            CountryDBModel(0, "")
        }
    }


    suspend fun updateData(item: String): Int = withContext(Dispatchers.IO){
        try {
            val value = CountryDBModel(
                id = 0,
                respose = item
            )
            countryDbSettings.updateDataInDb(value)
            100
        }catch (ex:Exception){
            0
        }
    }

}