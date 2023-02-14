package com.khangly.nasaapodjetpackcompose.data.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemotePhotoDto(
    @SerialName(value = "url")
    var imgUrl: String,
    @SerialName(value = "hdurl")
    var imgUrlHd: String,
    @SerialName(value = "title")
    var title: String,
    @SerialName(value = "date")
    var date: String,
    @SerialName(value = "media_type")
    var mediaType: String,
    @SerialName(value = "explanation")
    var explanation: String,
    @SerialName(value = "copyright")
    var copyright: String? = null,
    @SerialName(value = "service_version")
    var serviceVersion: String,
)