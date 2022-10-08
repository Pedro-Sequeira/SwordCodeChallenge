package com.pedrosequeira.scc.dog.api

import com.pedrosequeira.scc.data.ImagesDataSource
import com.pedrosequeira.scc.data.entities.DataImage
import javax.inject.Inject

internal class ImagesRemoteDataSource @Inject constructor(
    private val dogsApi: DogsApi
) : ImagesDataSource {

    override suspend fun getImages(): List<DataImage> {
        return dogsApi.getImages().map { apiImage ->
            with(apiImage) {
                DataImage(
                    breedName = breeds?.firstOrNull()?.name.orEmpty(),
                    url = url.orEmpty()
                )
            }
        }
    }
}
