package com.pedrosequeira.scc.images

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pedrosequeira.scc.images.widgets.ImagesGridView

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun ImagesScreen(
    viewModel: ImagesViewModel = hiltViewModel()
) {
    val images = viewModel.images.collectAsStateWithLifecycle(
        initialValue = emptyList()
    ).value

    if (images.isNotEmpty()) {
        ImagesGridView(images)
    } else {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }
}
