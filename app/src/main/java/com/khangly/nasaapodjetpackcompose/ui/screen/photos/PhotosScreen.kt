package com.khangly.nasaapodjetpackcompose.ui.screen.photos

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.khangly.nasaapodjetpackcompose.R
import com.khangly.nasaapodjetpackcompose.domain.PhotoData

@Composable
fun PhotosScreen(onPhotoClick: (PhotoData) -> Unit) {
    val viewModel = hiltViewModel<PhotosViewModel>()
    val uiState = viewModel.uiState.collectAsState().value

    val photoGridState = rememberLazyGridState()


    if (uiState.hasErrorWhileLoadData) {
        Text(text = stringResource(R.string.error_msg__loading_photos))
        return
    }

    val photoItems: MutableList<PhotoData>? = uiState.photoItems
    if (photoItems == null) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        viewModel.loadPhotoItems(onLoaded = {})
        return
    }

    PhotoGrid(
        photos = photoItems,
        state = photoGridState,
        onClick = onPhotoClick
    )
}

@Composable
fun PhotoGrid(
    photos: MutableList<PhotoData>?,
    onClick: (PhotoData) -> Unit,
    state: LazyGridState
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(8.dp),
        state = state
    ) {
        items(items = photos ?: ArrayList(), key = { it.imgUrl }) {
            PhotoCard(photoItem = it, onClick = onClick)
        }
    }
}

@Composable
fun PhotoCard(
    photoItem: PhotoData,
    onClick: (PhotoData) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .padding(8.dp)
            .background(Color.Gray)
            .clickable { onClick.invoke(photoItem) }
    ) {
        AsyncImage(
            model = ImageRequest
                .Builder(context = LocalContext.current)
                .data(photoItem.imgUrl)
                .crossfade(true)
                .build(),
            contentDescription = photoItem.imgUrl,
            placeholder = ColorPainter(Color.Blue),
            error = ColorPainter(Color.Red),
            contentScale = ContentScale.FillBounds
        )

        Text(
            text = photoItem.title,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0x55000000))
        )
    }
}