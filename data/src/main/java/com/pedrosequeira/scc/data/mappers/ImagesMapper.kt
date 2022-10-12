package com.pedrosequeira.scc.data.mappers

import com.pedrosequeira.scc.data.entities.DataImage
import com.pedrosequeira.scc.domain.entities.Image
import javax.inject.Inject

internal class ImagesMapper @Inject constructor() {

    fun mapToDomainImage(dataImage: DataImage): Image = with(dataImage) {
        return Image(
            breedName = breedName,
            url = url
        )
    }
}

internal fun ImagesMapper.mapToDomainImages(dataImages: List<DataImage>): List<Image> {
    return dataImages.map(::mapToDomainImage)
}
