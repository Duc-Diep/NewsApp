package com.ducdiep.playmusic.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val baseUrl = " https://newsapi.org/v2/"

class RetrofitInstance {
    companion object{
        fun getInstance(): NewsService? {
            var gson = GsonBuilder()
                .setDateFormat("YYYY-MM-dd HH:mm:ss").create()

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(NewsService::class.java)
        }
    }
}