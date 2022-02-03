package com.example.hsdemosoft.data.remote

import retrofit2.Response
import java.lang.Exception

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class Error(val error: Exception? = null): ResultWrapper<Nothing>()
}