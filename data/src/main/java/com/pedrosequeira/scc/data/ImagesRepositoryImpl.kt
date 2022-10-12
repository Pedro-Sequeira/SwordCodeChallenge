package com.pedrosequeira.scc.data

import com.pedrosequeira.scc.data.entities.DataImage
import com.pedrosequeira.scc.data.entities.DataResult
import com.pedrosequeira.scc.data.mappers.ImagesMapper
import com.pedrosequeira.scc.data.mappers.map
import com.pedrosequeira.scc.data.mappers.mapToDomainImages
import com.pedrosequeira.scc.domain.repositories.ImagesRepository
import com.pedrosequeira.scc.domain.Result
import com.pedrosequeira.scc.domain.entities.Image
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class ImagesRepositoryImpl @Inject constructor(
    private val imagesDataSource: ImagesDataSource,
    private val imagesMapper: ImagesMapper
) : ImagesRepository {

    override suspend fun getImages(page: Int): Flow<Result<List<Image>>> {
        return flow {
            emit(imagesDataSource.getImages(page = page).toDomain())
        }
    }

    private fun DataResult<List<DataImage>>.toDomain() = this.map(imagesMapper::mapToDomainImages)
}
