package com.khangly.nasaapodjetpackcompose.di

import com.khangly.nasaapodjetpackcompose.data.remote.RemotePhotoApi
import com.khangly.nasaapodjetpackcompose.data.remote.RemotePhotosRepository
import com.khangly.nasaapodjetpackcompose.data.remote.RetrofitRemotePhotoSource
import com.khangly.nasaapodjetpackcompose.domain.PhotosRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun providePhotosRepository(api: RemotePhotoApi): PhotosRepository {
        return RemotePhotosRepository(api)
    }

    @Provides
    @Singleton
    fun provideRemotePhotoApi(): RemotePhotoApi {
        return RetrofitRemotePhotoSource.retrofitService
    }
}