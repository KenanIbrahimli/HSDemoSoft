package com.example.hsdemosoft.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun gsonApplication(): Gson{
        return Gson()
    }
}