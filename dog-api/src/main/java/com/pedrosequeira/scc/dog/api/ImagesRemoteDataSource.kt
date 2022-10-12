package com.pedrosequeira.scc.dog.api

import com.pedrosequeira.scc.data.ImagesDataSource
import com.pedrosequeira.scc.data.entities.DataImage
import com.pedrosequeira.scc.data.entities.DataResult
import com.pedrosequeira.scc.dog.api.extensions.mapEither
import com.pedrosequeira.scc.dog.api.mappers.ErrorMapper
import com.pedrosequeira.scc.dog.api.mappers.ImagesMapper
import com.pedrosequeira.scc.dog.api.mappers.PaginationMapper
import javax.inject.Inject

internal class ImagesRemoteDataSource @Inject constructor(
    private val dogsApi: DogsApi,
    private val imagesMapper: ImagesMapper,
    private val paginationMapper: PaginationMapper,
    private val errorMapper: ErrorMapper
) : ImagesDataSource {

    override suspend fun getImages(page: Int): DataResult<List<DataImage>> {
        return dogsApi.getImages(page = page).mapEither(
            paginationMapping = paginationMapper::mapToData,
            success = imagesMapper::mapToDataImages,
            failure = errorMapper::mapToDataError
        )
    }
}
