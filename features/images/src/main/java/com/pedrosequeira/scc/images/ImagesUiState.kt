package com.pedrosequeira.scc.images

import com.pedrosequeira.scc.domain.entities.Image
import com.pedrosequeira.scc.domain.entities.Pagination

data class ImagesUiState(
    val images: List<Image> = emptyList(),
    val pagination: Pagination = Pagination(),
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)

fun ImagesUiState.toSuccessState(images: List<Image>): ImagesUiState {
    return copy(isLoading = false, images = images)
}
