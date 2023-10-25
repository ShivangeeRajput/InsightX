package com.example.recyclerviewapi


import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call


interface RetrofitAPI {

    @GET("top-headlines")
    fun getTopHeadlines(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String
    ): Call<NewsResponse>

//       @GET("$topHeadlines$country=in&$pageSize=100&$apiKey")
//    suspend fun getTopHeadlines() : Response<NewsModel>
    }
