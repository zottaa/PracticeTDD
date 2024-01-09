package com.github.johnnysc.practicetdd

import com.google.gson.annotations.SerializedName

data class ServerException(
    override val message: String,
    private val errorType: String = ""
) : Exception()

data class ApiError(
    val errorMessage: String = "",
    val errorType: String = ""
)
