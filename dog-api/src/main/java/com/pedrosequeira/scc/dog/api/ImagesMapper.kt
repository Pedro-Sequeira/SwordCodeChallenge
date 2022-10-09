package com.pedrosequeira.scc.dog.api

import com.pedrosequeira.scc.data.entities.DataImage
import com.pedrosequeira.scc.dog.api.entities.dogs.ApiImage
import javax.inject.Inject

internal class ImagesMapper @Inject constructor() {

    fun mapToDataImage(apiImage: ApiImage): DataImage = with(apiImage) {
        return DataImage(
            breedName = breeds?.firstOrNull()?.name.orEmpty(),
            url = url.orEmpty()
        )
    }
}

internal fun ImagesMapper.mapToDataImages(apiImages: List<ApiImage>): List<DataImage> {
    return apiImages.map(::mapToDataImage)
}
