package com.khangly.nasaapodjetpackcompose.ui.screen.photo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.khangly.nasaapodjetpackcompose.domain.PhotoData

@Composable
fun PhotoScreen(photoItem: PhotoData) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = photoItem.title,
            fontSize = 30.sp,
            modifier = Modifier.padding(16.dp)
        )

        AsyncImage(
            model = ImageRequest
                .Builder(context = LocalContext.current)
                .data(photoItem.imgUrl)
                .crossfade(true)
                .build(),
            contentDescription = photoItem.imgUrl,
            placeholder = ColorPainter(Color.Blue),
            error = ColorPainter(Color.Red),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.aspectRatio(1f)
        )

        Row(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
            Text(
                text = "${photoItem.author}",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left
            )
            Text(
                text = photoItem.date,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Right,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = photoItem.explanation,
                fontSize = 24.sp,
            )
        }
    }
}