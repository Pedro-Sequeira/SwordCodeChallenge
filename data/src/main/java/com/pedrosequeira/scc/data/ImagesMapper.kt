package com.pedrosequeira.scc.data

import com.pedrosequeira.scc.data.entities.DataImage
import com.pedrosequeira.scc.domain.Image

internal class ImagesMapper {

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
