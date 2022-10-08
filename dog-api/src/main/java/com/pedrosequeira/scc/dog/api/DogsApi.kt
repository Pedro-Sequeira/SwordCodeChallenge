package com.pedrosequeira.scc.dog.api

import com.pedrosequeira.scc.dog.api.entities.ApiImage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DogsApi {

    @GET("images/search")
    suspend fun getImages(
        @Query("has_breeds") hasBreeds: Boolean = true,
        @Query("order") order: String = "ASC",
        @Query("page") page: Int = 0,
        @Query("limit") limit: Int = 25
    ): List<ApiImage>
}
