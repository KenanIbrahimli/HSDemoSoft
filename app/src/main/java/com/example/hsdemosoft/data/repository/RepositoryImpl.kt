package com.example.hsdemosoft.data.repository

import com.example.hsdemosoft.data.remote.QueryServices
import com.example.hsdemosoft.domain.Repository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(private val queryServices: QueryServices) : Repository{
    override suspend fun getData(query: String): Response<String> {
        return queryServices.getData(query)
        }
}