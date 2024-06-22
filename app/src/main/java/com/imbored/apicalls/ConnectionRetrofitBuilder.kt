package com.imbored.apicalls

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConnectionRetrofitBuilder {

    companion object{
        private var responseRetrofitService: ResponseRetrofitService?= null
        fun getInstance() : ResponseRetrofitService {
            if(responseRetrofitService == null){
                responseRetrofitService = Retrofit.Builder()
                    .baseUrl("https://bored-api.appbrewery.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ResponseRetrofitService::class.java)
            }
            return responseRetrofitService!!
        }
    }
}