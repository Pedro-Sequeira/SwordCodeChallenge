package com.pedrosequeira.scc.data

import com.pedrosequeira.scc.domain.Image
import com.pedrosequeira.scc.domain.ImagesRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ImagesRepositoryImpl @Inject constructor(
    private val imagesDataSource: ImagesDataSource
) : ImagesRepository {

    override suspend fun getImages(): Flow<List<Image>> {
        return flow {
            emit(imagesDataSource.getImages().map {
                Image(it.url)
            })
        }
    }
}
