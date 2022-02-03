package com.example.hsdemosoft.domain

import retrofit2.Response

interface Repository {
    suspend fun getData(query: String): Response<String>
}