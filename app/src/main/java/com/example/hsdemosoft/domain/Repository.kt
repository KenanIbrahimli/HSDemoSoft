package com.example.hsdemosoft.domain

import com.example.hsdemosoft.data.remote.ResultWrapper
import com.example.hsdemosoft.models.CountryModel
import retrofit2.Response

interface Repository {
    suspend fun getData(query: String): Response<String>
}