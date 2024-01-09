package com.github.johnnysc.practicetdd

import com.google.gson.Gson

interface CloudDataSource {

    suspend fun fetch(): UserCloud

    class Base(
        private val apiService: ApiService
    ) : CloudDataSource {
        override suspend fun fetch(): UserCloud {
            val response = apiService.fetch()
            return try {
                response.body()!!
            } catch (e: Exception) {
                response.errorBody()?.let {
                    val apiError = Gson().fromJson(it.string(), ApiError::class.java)
                    throw ServerException(apiError.errorMessage, apiError.errorType)
                }
                    ?: throw ServerException(message = "Unknown error")
            }
        }
    }
}
