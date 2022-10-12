package com.pedrosequeira.scc.dog.api

import com.pedrosequeira.scc.dog.api.entities.ApiParams.Queries.ITEMS_PER_PAGE
import com.pedrosequeira.scc.dog.api.entities.ApiParams.Queries.ONLY_WITH_BREED_INFO
import com.pedrosequeira.scc.dog.api.entities.ApiParams.Queries.ORDERING
import com.pedrosequeira.scc.dog.api.entities.ApiParams.Queries.PAGE
import com.pedrosequeira.scc.dog.api.entities.dogs.ApiImage
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface DogsApi {

    @GET("images/search")
    suspend fun getImages(
        @Query(ONLY_WITH_BREED_INFO) onlyWithBreedInfo: Boolean = true,
        @Query(ORDERING) ordering: String = "ASC",
        @Query(PAGE) page: Int = 0,
        @Query(ITEMS_PER_PAGE) itemsPerPage: Int = 25
    ): Response<List<ApiImage>>
}
