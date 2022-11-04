package com.pedrosequeira.scc.images

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedrosequeira.scc.core.ErrorMapper
import com.pedrosequeira.scc.domain.Result
import com.pedrosequeira.scc.domain.Result.Error
import com.pedrosequeira.scc.domain.Result.Success
import com.pedrosequeira.scc.domain.entities.Image
import com.pedrosequeira.scc.domain.providers.DispatcherProvider
import com.pedrosequeira.scc.domain.repositories.ImagesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
internal class ImagesViewModel @Inject constructor(
    private val imagesRepository: ImagesRepository,
    private val errorMapper: ErrorMapper,
    private val dispatcherProvider: DispatcherProvider
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
        imagesRepository.getImages(page = _uiState.value.pagination.page)
            .flowOn(dispatcherProvider.io)
            .map { _uiState.value.mapResult(it) }
            .flowOn(dispatcherProvider.computation)
            .onStart { emit(_uiState.value.copy(isLoading = true)) }
            .onCompletion { emit(_uiState.value.copy(isLoading = false)) }
            .onEach { _uiState.value = it }
            .launchIn(viewModelScope)
    }

    private fun ImagesUiState.mapResult(result: Result<List<Image>>): ImagesUiState {
        return when (result) {
            is Success -> this.toSuccessState(result)
            is Error -> this.toErrorState(errorMapper.mapToMessage(result))
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
