package com.example.hsdemosoft.data.repository

import com.example.hsdemosoft.data.remote.QueryServices
import com.example.hsdemosoft.data.remote.ResultWrapper
import com.example.hsdemosoft.domain.Repository
import com.example.hsdemosoft.domain.SafeRepository
import com.example.hsdemosoft.models.CountryModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(private val queryServices: QueryServices) : Repository,
    SafeRepository {
    override suspend fun getData(query: String): Response<String> {
        return queryServices.getData(query)
        }

    override suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T
    ): ResultWrapper<T> {
        return withContext(dispatcher) {
            try {
                val result = apiCall.invoke()
                ResultWrapper.Success(result)
            } catch (throwable: Exception) {
                ResultWrapper.Error(throwable)
            }
        }
    }
}