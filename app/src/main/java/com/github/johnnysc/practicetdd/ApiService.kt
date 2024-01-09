package com.github.johnnysc.practicetdd

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET
    suspend fun fetch() : Response<UserCloud>

}