package com.khangly.nasaapodjetpackcompose.data.mappers

import com.khangly.nasaapodjetpackcompose.data.remote.RemotePhotoDto
import com.khangly.nasaapodjetpackcompose.domain.PhotoData

fun RemotePhotoDto.toPhotoData(): PhotoData {
    return PhotoData(
        imgUrl = imgUrl,
        imgUrlHd = imgUrlHd,
        title = title,
        date = date,
        explanation = explanation,
        author = copyright
    )
}