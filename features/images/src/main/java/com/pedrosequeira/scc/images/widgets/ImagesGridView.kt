package com.pedrosequeira.scc.images.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pedrosequeira.scc.domain.Image

@Composable
internal fun ImagesGridView(images: List<Image>) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(images) { image ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(image.url)
                        .build(),
                    contentDescription = null
                )
                Text(image.breedName)
            }
        }
    }
}
