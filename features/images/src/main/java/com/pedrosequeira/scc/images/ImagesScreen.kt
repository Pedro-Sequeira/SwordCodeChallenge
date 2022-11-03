package com.pedrosequeira.scc.images

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pedrosequeira.scc.images.widgets.ImagesGridView

@Composable
fun ImagesScreen() {
    ImagesScreen(viewModel = hiltViewModel())
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
private fun ImagesScreen(viewModel: ImagesViewModel) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    Box() {
        when {
            uiState.images.isNotEmpty() -> {
                val canLoadMore = uiState.pagination.endReached.not() && uiState.isLoading.not()
                ImagesGridView(uiState.images, canLoadMore) {
                    viewModel.onBottomReached()
                }
            }

            uiState.isLoading -> {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }

            uiState.errorMessage != null -> {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = uiState.errorMessage
                )
            }
        }
    }
}
