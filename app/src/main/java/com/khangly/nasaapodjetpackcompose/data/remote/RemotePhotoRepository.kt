package com.khangly.nasaapodjetpackcompose.data.remote

import com.khangly.nasaapodjetpackcompose.data.mappers.toPhotoData
import com.khangly.nasaapodjetpackcompose.domain.PhotoData
import com.khangly.nasaapodjetpackcompose.domain.PhotosRepository
import java.text.SimpleDateFormat
import java.util.*

class RemotePhotosRepository(
    private var remotePhotoApi: RemotePhotoApi
) : PhotosRepository {
    
    companion object {
        private const val COUNT_LAST_DAYS = 13
    }

    override suspend fun getPhotos(): MutableList<PhotoData>? {

        var dtoPhotos: MutableList<RemotePhotoDto>? = null
        try {
            val calender = Calendar.getInstance(TimeZone.getTimeZone("PST"))
            calender.add(Calendar.DAY_OF_YEAR, (COUNT_LAST_DAYS - 1) * -1)

            val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.GERMAN)
            val date = formatter.format(calender.time)

            dtoPhotos = remotePhotoApi.getPhotos(startDate = date)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val photos = ArrayList<PhotoData>()
        if (dtoPhotos != null) {
            for (photoDto in dtoPhotos) {
                photos.add(photoDto.toPhotoData())
            }
        }

        return photos
    }
}