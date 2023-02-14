package com.khangly.nasaapodjetpackcompose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.khangly.nasaapodjetpackcompose.domain.PhotoData
import com.khangly.nasaapodjetpackcompose.ui.screen.photo.PhotoScreen
import com.khangly.nasaapodjetpackcompose.ui.screen.photos.PhotosScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@Composable
fun RootScreen() {
    val viewModel = hiltViewModel<RootViewModel>()
    val uiState = viewModel.uiState

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen =
        AppScreen.valueOf(backStackEntry?.destination?.route ?: AppScreen.Photos.name)

    Scaffold(
        topBar = {
            AppTopBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateBack = { navController.navigateUp() })
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = AppScreen.Photos.name,
            modifier = Modifier.padding(padding)
        ) {
            composable(route = AppScreen.Photos.name) {
                PhotosScreen(onPhotoClick = {
                    viewModel.setPhotoItem(it)
                    navController.navigate(AppScreen.PhotoDetails.name)
                })
            }

            composable(route = AppScreen.PhotoDetails.name) {
                PhotoScreen(uiState.photoItem!!)
            }
        }
    }
}

@Composable
fun AppTopBar(
    currentScreen: AppScreen,
    canNavigateBack: Boolean,
    navigateBack: () -> Unit
) {
    TopAppBar(
        title = { Text(text = currentScreen.title) },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = { navigateBack.invoke() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        })
}

enum class AppScreen(val title: String) {
    Photos(title = "Home"),
    PhotoDetails(title = "Detail")
}

@HiltViewModel
class RootViewModel @Inject constructor() : ViewModel() {
    var uiState by mutableStateOf(UiState())
        private set

    data class UiState(
        val photoItem: PhotoData? = null
    )

    fun setPhotoItem(photoItem: PhotoData) {
        uiState = uiState.copy(photoItem = photoItem)
    }
}