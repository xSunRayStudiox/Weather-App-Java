# WeatherWise 🌤️

**WeatherWise** is a dynamic weather application for Android that provides current and forecasted weather information for locations around the globe. It includes a sleek design, animations, and support for location search, daily and hourly forecasts.

---

## Features 🚀
- **Current Weather**: Get real-time weather information.
- **Forecasts**: View hourly and daily weather forecasts.
- **Search Locations**: Search for weather details of any city or town.
- **Animations**: Smooth transitions and engaging UI animations.
- **Weather Icons**: Icons loaded dynamically using **Glide**.

---

## Screenshots 📸
<!-- Add screenshots here -->
| Current Weather | Hourly Forecast | Location Search |
|------------------|-----------------|------------------|
| <kbd><img src="https://github.com/xSunRayStudiox/Weather-App-Java/blob/master/1.jpg" width="300"></kbd> | <kbd><img src="https://github.com/xSunRayStudiox/Weather-App-Java/blob/master/2.jpg" width="300"></kbd> | <kbd><img src="https://github.com/xSunRayStudiox/Weather-App-Java/blob/master/3.jpg" width="300"></kbd> |

---

## Tech Stack 🛠️
- **Retrofit**: For API calls to the [WeatherAPI](https://www.weatherapi.com/).
- **Glide**: For loading and caching weather icons efficiently.
- **RecyclerView**: Displays daily and hourly weather forecasts.

---

## Dependencies 📦
Add the following dependencies to your `build.gradle` file:

```gradle
dependencies {
    // Retrofit for API calls
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'

    // Glide for image loading
    implementation 'com.github.bumptech.glide:glide:4.15.1'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.15.1'

    // RecyclerView for list views
    implementation 'androidx.recyclerview:recyclerview:1.3.1'
}
