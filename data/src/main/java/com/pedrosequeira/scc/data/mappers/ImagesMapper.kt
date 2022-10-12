package com.pedrosequeira.scc.data.mappers

import com.pedrosequeira.scc.data.entities.DataImage
import com.pedrosequeira.scc.domain.entities.Image
import javax.inject.Inject

internal class ImagesMapper @Inject constructor() {

    fun mapToDomainImages(dataImages: List<DataImage>): List<Image> {
        return dataImages.map { image ->
            Image(
                breedName = image.breedName,
                url = image.url
            )
        }
    }
}
