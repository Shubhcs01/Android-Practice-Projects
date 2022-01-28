package com.example.apis

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://newsapi.org/v2/"
const val API_KEY = "9291ff6e154e4001b7cdda644655a79f"

interface NewsInterface {
    @GET("top-headlines?apiKey=$API_KEY")
    fun getHeadLines(@Query("country") country: String, @Query("category")category: String, @Query("Page") page: Int): Call<News>
}

object NewsService{

    val newsInstance: NewsInterface
    //--> retrofit object
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstance = retrofit.create(NewsInterface::class.java)
    }

}