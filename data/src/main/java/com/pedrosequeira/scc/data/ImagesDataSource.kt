package com.pedrosequeira.scc.data

import com.pedrosequeira.scc.data.entities.DataImage

interface ImagesDataSource {

    suspend fun getImages(): List<DataImage>
}
