package com.pedrosequeira.scc.images

import com.pedrosequeira.scc.domain.Result.Success
import com.pedrosequeira.scc.domain.entities.Image
import com.pedrosequeira.scc.domain.entities.Pagination

data class ImagesUiState(
    val images: List<Image> = emptyList(),
    val pagination: Pagination = Pagination(),
    val errorMessage: String? = null,
    val isLoading: Boolean = false
) {

    fun toSuccessState(result: Success<List<Image>>): ImagesUiState {
        return this.copy(
            images = this.images + result.data,
            pagination = result.pagination,
            errorMessage = null
        )
    }

    fun toErrorState(errorMessage: String): ImagesUiState {
        return this.copy(
            images = emptyList(),
            errorMessage = errorMessage
        )
    }
}

