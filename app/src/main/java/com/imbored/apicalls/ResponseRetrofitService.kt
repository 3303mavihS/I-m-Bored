package com.imbored.apicalls

import retrofit2.Response
import retrofit2.http.GET

interface ResponseRetrofitService {

    @GET("random")
    suspend fun getTheActivity() : Response<YourActivity>

}