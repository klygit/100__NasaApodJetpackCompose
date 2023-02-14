# App

## App Overview
This app fetches [Astronomy Pictures of the Day](https://apod.nasa.gov/apod) from the 
[NASA API](https://api.nasa.gov) and display them in an overview screen. A detail screen 
with more information is shown, after selecting an image.

## App Technicals
- Codebase: Kotlin using Jetpack Compose
- Design Pattern: MVVM for app, UDF (unidirectional data flow) for composeables
- Architecture: UI Layer(View/ViewModel), Domain Layer(Repository interfaces/Data) Data Layer(Repository implementation/Dto)
- Navigation: Jetpack Navigation

## Other Tools and Skills
- Coroutines
- Fetch and serialize JSON data from a REST API
- Load images from a server
- Junit tests
- Dependency Injection: Dagger - Hilt
