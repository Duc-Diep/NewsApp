package com.ducdiep.playmusic.api

import com.ducdiep.newsapp.models.ResponseNews
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines")
    fun getTopNews(@Query("country") country:String,@Query("apiKey") apiKey:String): Call<ResponseNews>
}