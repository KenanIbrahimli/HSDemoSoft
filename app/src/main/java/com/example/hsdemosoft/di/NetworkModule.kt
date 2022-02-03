package com.example.hsdemosoft.di

import com.example.hsdemosoft.data.remote.QueryServices
import com.example.hsdemosoft.data.repository.RepositoryImpl
import com.example.hsdemosoft.domain.Repository
import com.example.hsdemosoft.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient{
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addNetworkInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient, baseUrl: String): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    @Provides
    fun getQueryServices(retrofit: Retrofit): QueryServices{
        return retrofit.create(QueryServices::class.java)
    }

    @Provides
    fun provideQueryServiceHelper(repositoryImpl: RepositoryImpl):
            Repository = repositoryImpl
}