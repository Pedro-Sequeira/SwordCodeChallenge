package com.pedrosequeira.scc.images

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedrosequeira.scc.domain.Image
import com.pedrosequeira.scc.domain.ImagesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

@HiltViewModel
class ImagesViewModel @Inject constructor(
    private val imagesRepository: ImagesRepository
) : ViewModel() {

    var images: MutableStateFlow<List<Image>> = MutableStateFlow(emptyList())
        private set

    init {
        viewModelScope.launch {
            imagesRepository.getImages()
                .flowOn(Dispatchers.IO)
                .collect {
                    images.value = it
                }
        }
    }
}
