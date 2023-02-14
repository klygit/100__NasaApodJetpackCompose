package com.khangly.nasaapodjetpackcompose.ui.screen.photos

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khangly.nasaapodjetpackcompose.domain.PhotoData
import com.khangly.nasaapodjetpackcompose.domain.PhotosRepository
import com.khangly.nasaapodjetpackcompose.utils.Const
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private var photosRepository: PhotosRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState

    data class UiState(
        val isLoading: Boolean = false,
        val hasErrorWhileLoadData: Boolean = false,
        val photoItems: MutableList<PhotoData>? = null,
    )

    fun loadPhotoItems(onLoaded: (Boolean) -> Unit) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    hasErrorWhileLoadData = false,
                    photoItems = null
                )
            }

            try {
                val photoItems = photosRepository.getPhotos()
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        hasErrorWhileLoadData = false,
                        photoItems = photoItems
                    )
                }
                onLoaded.invoke(true)
            } catch (e: Exception) {
                Log.e(Const.TAG, null, e)
                when (e) {
                    is IOException, is HttpException -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                hasErrorWhileLoadData = true,
                                photoItems = null
                            )
                        }
                    }
                    else -> throw e
                }
            }
        }
    }
}