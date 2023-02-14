package com.khangly.nasaapodjetpackcompose.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface RemotePhotoApi {
    companion object Const {
        //const val API_KEY = "DEMO_KEY"
        const val API_KEY = "HZ4FpuyIMiqmKfpferwGgzmKKBI5BDopPGUqsJ4M"
    }

    @GET("apod")
    suspend fun getPhotos(
        @Query("start_date") startDate: String,
        @Query("api_key") apiKey: String = API_KEY
    ): ArrayList<RemotePhotoDto>?
}
