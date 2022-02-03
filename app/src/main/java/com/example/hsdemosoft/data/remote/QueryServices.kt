package com.example.hsdemosoft.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface QueryServices {
    @Headers("Content-Type: application/json")
    @POST("/")
    suspend fun getData(@Body query: String): Response<String>
}