package com.ducdiep.newsapp.repository

import com.ducdiep.newsapp.models.ResponseNews
import com.ducdiep.playmusic.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository {
    fun getTopNews(country:String, apiKey:String,onResult:(isSuccess:Boolean,response: ResponseNews?)->Unit){
        RetrofitInstance.getInstance()?.getTopNews(country, apiKey)?.enqueue(
            object :Callback<ResponseNews>{
                override fun onResponse(call: Call<ResponseNews>, response: Response<ResponseNews>) {
                    if (response.isSuccessful && response != null) {
                        onResult(true, response.body()!!)
                    } else {
                        onResult(false, null)

                    }
                }

                override fun onFailure(call: Call<ResponseNews>, t: Throwable) {
                    onResult(false,null)
                }

            }
        )
    }
    companion object {
        private var INSTANCE: NewsRepository? = null
        fun getInstance() = INSTANCE
            ?: NewsRepository().also {
                INSTANCE = it
            }
    }

}