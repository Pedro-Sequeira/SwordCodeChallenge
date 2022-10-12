package com.pedrosequeira.scc.images.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pedrosequeira.scc.domain.entities.Image
import com.pedrosequeira.scc.images.R

@Composable
internal fun ImagesGridView(
    images: List<Image>,
    onBottomReached: () -> Unit
) {
    val lastIndex = images.lastIndex

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        itemsIndexed(images) { index, image ->
            if (index == lastIndex) {
                LaunchedEffect(lastIndex) {
                    onBottomReached()
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .crossfade(true)
                        .placeholder(R.drawable.placeholder)
                        .data(image.url)
                        .build(),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                Text(image.breedName)
            }
        }
    }
}
