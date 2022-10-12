package com.pedrosequeira.scc.dog.api.mappers

import com.pedrosequeira.scc.data.entities.DataImage
import com.pedrosequeira.scc.data.entities.DataPagination
import com.pedrosequeira.scc.data.entities.DataResult
import com.pedrosequeira.scc.dog.api.entities.ApiPagination
import com.pedrosequeira.scc.dog.api.entities.ApiResult
import com.pedrosequeira.scc.dog.api.entities.dogs.ApiImage
import javax.inject.Inject

internal class ImagesMapper @Inject constructor() {

    fun mapToDataImages(apiImages: List<ApiImage>): List<DataImage> {
        return apiImages.map {
            with(it) {
                DataImage(
                    breedName = breeds?.firstOrNull()?.name.orEmpty(),
                    url = url.orEmpty()
                )
            }
        }
    }
}
