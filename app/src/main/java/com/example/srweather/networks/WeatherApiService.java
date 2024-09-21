package com.example.srweather.networks;

import com.example.srweather.models.LocationResponse;
import com.example.srweather.models.WeatherResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {
    @GET("forecast.json")
    Call<WeatherResponse> getCurrentWeather(@Query("key") String apiKey, @Query("q") String location,@Query("days") int days);

//   https://api.weatherapi.com/v1/forecast.json?key=69864b428fc948e282131938240708&q=Barhaj&days=3

    @GET("search.json")
    Call<List<LocationResponse>> searchLocation(@Query("key") String apiKey, @Query("q") String query);
}
