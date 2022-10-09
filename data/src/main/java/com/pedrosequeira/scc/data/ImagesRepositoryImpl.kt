package com.pedrosequeira.scc.data

import com.pedrosequeira.scc.data.entities.DataImage
import com.pedrosequeira.scc.domain.Image
import com.pedrosequeira.scc.domain.ImagesRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class ImagesRepositoryImpl @Inject constructor(
    private val imagesDataSource: ImagesDataSource,
    private val imagesMapper: ImagesMapper
) : ImagesRepository {

    override suspend fun getImages(): Flow<List<Image>> {
        return flow {
            emit(imagesDataSource.getImages().toDomain())
        }
    }

    private fun List<DataImage>.toDomain() = imagesMapper.mapToDomainImages(this)
}
