package com.pedrosequeira.scc.dog.api

import com.pedrosequeira.scc.data.ImagesDataSource
import com.pedrosequeira.scc.data.entities.DataImage
import com.pedrosequeira.scc.dog.api.entities.dogs.ApiImage
import javax.inject.Inject

internal class ImagesRemoteDataSource @Inject constructor(
    private val dogsApi: DogsApi,
    private val imagesMapper: ImagesMapper
) : ImagesDataSource {

    override suspend fun getImages(): List<DataImage> {
        return dogsApi.getImages().toData()
    }

    private fun List<ApiImage>.toData() = imagesMapper.mapToDataImages(this)
}
