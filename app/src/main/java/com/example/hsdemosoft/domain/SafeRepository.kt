package com.example.hsdemosoft.domain

import com.example.hsdemosoft.data.remote.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface SafeRepository {
    suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher = Dispatchers.IO, apiCall: suspend () -> T): ResultWrapper<T>
}