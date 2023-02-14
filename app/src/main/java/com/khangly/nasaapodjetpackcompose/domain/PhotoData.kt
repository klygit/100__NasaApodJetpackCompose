package com.khangly.nasaapodjetpackcompose.domain

data class PhotoData(
    var imgUrl: String,
    var imgUrlHd: String,
    var title: String,
    var date: String,
    var explanation: String,
    var author: String? = null,
)