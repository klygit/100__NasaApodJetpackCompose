package com.khangly.nasaapodjetpackcompose.domain

interface PhotosRepository {
    suspend fun getPhotos(): MutableList<PhotoData>?
}