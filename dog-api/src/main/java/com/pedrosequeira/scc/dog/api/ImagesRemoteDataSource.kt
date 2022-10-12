package com.pedrosequeira.scc.dog.api

import com.pedrosequeira.scc.data.ImagesDataSource
import com.pedrosequeira.scc.data.entities.DataImage
import com.pedrosequeira.scc.data.entities.DataPagination
import com.pedrosequeira.scc.data.entities.DataResult
import com.pedrosequeira.scc.dog.api.entities.ApiParams.Headers.Response.PAGE
import com.pedrosequeira.scc.dog.api.entities.ApiParams.Headers.Response.RESULTS_PER_PAGE
import com.pedrosequeira.scc.dog.api.entities.ApiParams.Headers.Response.TOTAL_PAGES
import com.pedrosequeira.scc.dog.api.entities.dogs.ApiImage
import javax.inject.Inject
import okhttp3.Headers

internal class ImagesRemoteDataSource @Inject constructor(
    private val dogsApi: DogsApi,
    private val imagesMapper: ImagesMapper
) : ImagesDataSource {

    override suspend fun getImages(page: Int): DataResult<List<DataImage>> {
        val response = dogsApi.getImages(page = page)

        if (response.isSuccessful) {
            val headers = response.headers()
            val body = response.body()

            val page = headers.find(PAGE)
            val totalPages = headers.find(TOTAL_PAGES)
            val resultsPerPage = headers.find(RESULTS_PER_PAGE)

            val pagination = DataPagination(
                page = page,
                totalPages = totalPages,
                resultsPerPage = resultsPerPage
            )

            return DataResult.Success(
                pagination,
                body!!.toData()
            )
        } else {
            return DataResult.Error(
                response.message()
            )
        }
    }

    private fun List<ApiImage>.toData() = imagesMapper.mapToDataImages(this)
}

private fun Headers.find(key: String): Int {
    return this.find { it.first == key }?.second?.toIntOrNull() ?: 0
}
