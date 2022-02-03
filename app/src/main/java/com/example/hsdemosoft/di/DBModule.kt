package com.example.hsdemosoft.di

import android.content.Context
import com.example.hsdemosoft.db.CountryDB
import com.example.hsdemosoft.db.CountryDao
import com.example.hsdemosoft.db.CountryRepository
import dagger.Module
import dagger.Provides


@Module
class DBModule {
    @Provides
    fun countryDbSettings(applicationContext: Context): CountryDao {
        return CountryDB.getInstance(applicationContext).countryDao()
    }


    @Provides
    fun provideCountryRepository(): CountryRepository = CountryRepository()
}