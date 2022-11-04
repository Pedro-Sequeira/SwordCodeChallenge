package com.pedrosequeira.scc.data

import com.pedrosequeira.scc.data.extensions.mapEither
import com.pedrosequeira.scc.data.mappers.ErrorMapper
import com.pedrosequeira.scc.data.mappers.ImagesMapper
import com.pedrosequeira.scc.data.mappers.PaginationMapper
import com.pedrosequeira.scc.domain.Result
import com.pedrosequeira.scc.domain.entities.Image
import com.pedrosequeira.scc.domain.repositories.ImagesRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class ImagesRepositoryImpl @Inject constructor(
    private val imagesDataSource: ImagesDataSource,
    private val imagesMapper: ImagesMapper,
    private val paginationMapper: PaginationMapper,
    private val errorMapper: ErrorMapper
) : ImagesRepository {

    override fun getImages(page: Int): Flow<Result<List<Image>>> = flow {
        val result = imagesDataSource.getImages(page = page)
        emit(
            result.mapEither(
                paginationMapping = paginationMapper::mapToDomainPagination,
                success = imagesMapper::mapToDomainImages,
                failure = errorMapper::mapToDomainError
            )
        )
    }
}
