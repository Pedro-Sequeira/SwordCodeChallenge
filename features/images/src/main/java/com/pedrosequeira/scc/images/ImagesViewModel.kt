package com.pedrosequeira.scc.images

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedrosequeira.scc.core.ErrorMapper
import com.pedrosequeira.scc.domain.Result
import com.pedrosequeira.scc.domain.Result.Error
import com.pedrosequeira.scc.domain.Result.Success
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
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch

@HiltViewModel
internal class ImagesViewModel @Inject constructor(
    private val imagesRepository: ImagesRepository,
    private val errorMapper: ErrorMapper
) : ViewModel() {

    private val _uiState = MutableStateFlow(ImagesUiState())
    val uiState: StateFlow<ImagesUiState> = _uiState.asStateFlow()

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
            imagesRepository.getImages(page = _uiState.value.pagination.page)
                .flowOn(Dispatchers.IO)
                .map { _uiState.value.mapResult(it) }
                .flowOn(Dispatchers.Default)
                .onStart { emit(uiState.value.copy(isLoading = true)) }
                .map { state ->
                    state.toSuccessState(state.images)
                }
                .collect { state ->
                    _uiState.value = state
                }
        }
    }

    private fun ImagesUiState.mapResult(result: Result<List<Image>>): ImagesUiState {
        return when (result) {
            is Success -> this.copy(
                images = _uiState.value.images + result.data,
                pagination = result.pagination,
                isLoading = false
            )

            is Error -> this.copy(
                errorMessage = errorMapper.mapToMessage(result)
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
