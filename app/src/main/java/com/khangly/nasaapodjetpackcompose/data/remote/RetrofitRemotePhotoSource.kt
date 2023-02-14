package com.khangly.nasaapodjetpackcompose.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object RetrofitRemotePhotoSource {
    private const val BASE_URL = "https://api.nasa.gov/planetary/"

    val retrofitService: RemotePhotoApi by lazy {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(BASE_URL)
            .build()
        retrofit.create(RemotePhotoApi::class.java)
    }
}