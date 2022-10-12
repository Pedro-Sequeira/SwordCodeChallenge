package com.pedrosequeira.scc.data

import com.pedrosequeira.scc.data.entities.DataImage
import com.pedrosequeira.scc.data.entities.DataResult

interface ImagesDataSource {

    suspend fun getImages(page: Int): DataResult<List<DataImage>>
}
