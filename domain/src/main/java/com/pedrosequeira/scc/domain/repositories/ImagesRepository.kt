package com.pedrosequeira.scc.domain.repositories

import com.pedrosequeira.scc.domain.Result
import com.pedrosequeira.scc.domain.entities.Image
import kotlinx.coroutines.flow.Flow

interface ImagesRepository {

    suspend fun getImages(page: Int): Flow<Result<List<Image>>>
}
