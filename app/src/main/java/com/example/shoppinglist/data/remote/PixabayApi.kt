package com.example.shoppinglist.data.remote


import com.example.shoppinglist.data.remote.responses.ImageResponse
import com.example.shoppinglist.other.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("/api/")
    suspend fun  searchForImage(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = API_KEY
    ): Response<ImageResponse>
}