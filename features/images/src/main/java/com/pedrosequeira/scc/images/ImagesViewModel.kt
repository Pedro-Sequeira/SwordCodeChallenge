package com.pedrosequeira.scc.images

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedrosequeira.scc.domain.Result
import com.pedrosequeira.scc.domain.entities.Image
import com.pedrosequeira.scc.domain.entities.nextPage
import com.pedrosequeira.scc.domain.repositories.ImagesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
internal class ImagesViewModel @Inject constructor(
    private val imagesRepository: ImagesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ImagesUiState())
    val uiState: StateFlow<ImagesUiState> = _uiState.asStateFlow()

    private var allLoadedImages = emptyList<Image>()

    init {
        viewModelScope.launch {
            loadImages()
        }
    }

    fun onBottomReached() {
        viewModelScope.launch {
            loadMoreImages()
        }
    }

    private fun loadImages() {
        viewModelScope.launch {
            imagesRepository.getImages(page = uiState.value.pagination.page)
                .flowOn(Dispatchers.IO)
                .map { uiState.value.mapResult(it) }
                .flowOn(Dispatchers.Default)
                .onStart { emit(uiState.value.copy(isLoading = true)) }
                .map { state ->
                    state.toSuccessState(allLoadedImages + state.images)
                }
                .collect { state ->
                    allLoadedImages = state.images
                    _uiState.value = state
                }
        }
    }

    private fun ImagesUiState.mapResult(result: Result<List<Image>>): ImagesUiState {
        return when (result) {
            is Result.Success -> this.copy(
                images = result.data,
                pagination = result.pagination,
                isLoading = false
            )
            is Result.Error -> this.copy(
                errorMessage = result.message
            )
        }
    }

    private fun loadMoreImages() {
        _uiState.update { state ->
            state.copy(
                pagination = state.pagination.nextPage()
            )
        }
        loadImages()
    }
}
