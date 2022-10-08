package com.pedrosequeira.scc.domain

import kotlinx.coroutines.flow.Flow

interface ImagesRepository {

    suspend fun getImages(): Flow<List<Image>>
}
