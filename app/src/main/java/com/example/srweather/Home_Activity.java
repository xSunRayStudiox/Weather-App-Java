package com.example.srweather;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.srweather.adapter.DayAdapter;
import com.example.srweather.adapter.HoursAdapter;
import com.example.srweather.models.DayModel;
import com.example.srweather.models.HoursModel;
import com.example.srweather.models.WeatherResponse;
import com.example.srweather.networks.RetrofitClient;
import com.example.srweather.networks.WeatherApiService;
import com.example.srweather.utils.TimeFilter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home_Activity extends AppCompatActivity implements DayAdapter.DayItemClickListener {

    TextView Show_location, weather_date, weather_Temp, feel_like, weather_Type, Sunset, Sunrise,
            Moonrise, Moonset, Weather_humidity, air_pressure, uv_index, ves_km, precip_chance,
            snow_chance, moon_phase, wind_speed_temp;

    ImageView condition_icon, wind_dir, searchBtn;
    RecyclerView dayListSet, Hours_list;
    String dig = "° C", currentDate, location_name;
    int selectedHours = 0;
    List<DayModel> dayList = new ArrayList<>();
    List<HoursModel> HoursList = new ArrayList<>();
    DayAdapter adapter;
    HoursAdapter hoursAdapter;
    WeatherApiService apiService = RetrofitClient.getRetrofitInstance().create(WeatherApiService.class);
    Call<WeatherResponse> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        // Set window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        viewInitialization();

        searchBtn.setOnClickListener(v -> {
            Intent i = new Intent(this,Search_Activity.class);
            startActivity(i);
        });

        currentDate = TimeFilter.currentDate();
        getWeatherData(RequestCall(), currentDate, 0);
        SetDayListName();
    }

    private void viewInitialization() {
        searchBtn = findViewById(R.id.search_location);

        Show_location = findViewById(R.id.show_location);
        weather_date = findViewById(R.id.weather_date);
        weather_Temp = findViewById(R.id.weather_temp);
        feel_like = findViewById(R.id.weather_feel_like);
        weather_Type = findViewById(R.id.weather_type);
        condition_icon = findViewById(R.id.con_img);

        Sunrise = findViewById(R.id.sunrise);
        Sunset = findViewById(R.id.sunset);
        Moonrise = findViewById(R.id.moonrise);
        Moonset = findViewById(R.id.moonset);

        Weather_humidity = findViewById(R.id.weather_humidity);
        air_pressure = findViewById(R.id.air_pressure);
        uv_index = findViewById(R.id.uv_index);
        ves_km = findViewById(R.id.ves_km);
        precip_chance = findViewById(R.id.precip_chance);
        snow_chance = findViewById(R.id.snow_chance);
        moon_phase = findViewById(R.id.moon_phase);
        wind_speed_temp = findViewById(R.id.wind_speed_temp);
        wind_dir = findViewById(R.id.wind_dir);
        dayListSet = findViewById(R.id.day_list);
        Hours_list = findViewById(R.id.hours_list);
    }

    private void getWeatherData(Call<WeatherResponse> call, String selectDate, int position) {
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    WeatherResponse weather = response.body();

                    // Set general weather details
                    Show_location.setText(weather.location.name);

                    setWeatherDetails(weather, position, selectDate);
                    SetHourData(position, weather); // Update hourly data
                } else {
                    Toast.makeText(Home_Activity.this, "Failed to retrieve weather data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                Toast.makeText(Home_Activity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setWeatherDetails(WeatherResponse weather, int position, String selectDate) {
        WeatherResponse.ForecastDay forecast = weather.forecast.forecastday.get(position);

        Sunset.setText(forecast.astro.sunset);
        Sunrise.setText(forecast.astro.sunrise);
        Moonrise.setText(forecast.astro.moonrise);
        Moonset.setText(forecast.astro.moonset);
        uv_index.setText("Index: " + forecast.day.uv);
        ves_km.setText(forecast.day.avgvis_km + "km");
        precip_chance.setText(forecast.day.totalprecip_mm + "mm  " + forecast.day.daily_chance_of_rain + "%");
        snow_chance.setText(forecast.day.totalsnow_cm + "cm  " + forecast.day.daily_chance_of_snow + "%");
        moon_phase.setText(forecast.astro.moon_phase);

        if (position == 0) {
            // Current weather
            setWeatherCurrent(weather);
        } else {
            // Forecast weather
            weather_date.setText(selectDate);
            weather_Temp.setText(weather.current.temp_c + dig);
            feel_like.setVisibility(View.GONE);
            feel_like.setText(" ");
            weather_Type.setText(forecast.day.condition.text);
            Glide.with(this).load("https:" + forecast.day.condition.icon).into(condition_icon);
            Weather_humidity.setText(forecast.day.avghumidity + "%");
            air_pressure.setText(forecast.hour.get(selectedHours).pressure_mb + "mb");
            wind_speed_temp.setText(forecast.day.maxwind_kph + "km");
            imageRotation(forecast.hour.get(selectedHours).wind_degree);
        }
    }

    private void setWeatherCurrent(WeatherResponse weather) {
        weather_date.setText(currentDate);
        weather_Temp.setText(weather.current.temp_c + dig);
        feel_like.setVisibility(View.VISIBLE);
        feel_like.setText("Feel Like: " + weather.current.feelslike_c + dig);
        weather_Type.setText(weather.current.condition.text);
        Glide.with(this).load("https:" + weather.current.condition.icon).into(condition_icon);
        Weather_humidity.setText(weather.current.humidity + "%");
        air_pressure.setText(weather.current.pressure_mb + "mb");
        wind_speed_temp.setText(weather.current.wind_kph + "km");
        imageRotation(weather.current.wind_degree);
    }

    private void SetHourData(int position, WeatherResponse weather) {
        Hours_list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        call = RequestCall();

        List<WeatherResponse.Hour> hourList = weather.forecast.forecastday.get(position).hour;
        HoursList.clear();
        for (WeatherResponse.Hour hour : hourList) {
            HoursList.add(new HoursModel(
                    hour.time, "https:" + hour.condition.icon, String.valueOf(hour.temp_c+dig),
                    hour.will_it_rain, hour.chance_of_rain, hour.will_it_snow, hour.chance_of_snow));
        }
        hoursAdapter = new HoursAdapter(HoursList, Home_Activity.this, position);
        Hours_list.setAdapter(hoursAdapter);
    }

    private void SetDayListName() {
        dayListSet.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        call = RequestCall();

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<WeatherResponse.ForecastDay> forecastDayList = response.body().forecast.forecastday;
                    dayList.clear();
                    for (WeatherResponse.ForecastDay forecastDay : forecastDayList) {
                        String date = forecastDay.date;
                        String dayOfWeek = TimeFilter.WeekName(date);
                        String dayWeatherIcon = "https:" + forecastDay.day.condition.icon;
                        String dayMiMaxTemp = forecastDay.day.maxtemp_c + "°/" + forecastDay.day.mintemp_c + "°";

                        // Set Data in Model
                        dayList.add(new DayModel(dayOfWeek, date, dayWeatherIcon, dayMiMaxTemp));
                    }
                    adapter = new DayAdapter(dayList, Home_Activity.this, Home_Activity.this);
                    dayListSet.setAdapter(adapter);
                } else {
                    Toast.makeText(Home_Activity.this, "Failed to retrieve weather data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                Toast.makeText(Home_Activity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void imageRotation(int windDegree) {
        RotateAnimation rotate = new RotateAnimation(0, windDegree, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(1000);
        rotate.setFillAfter(true);
        wind_dir.startAnimation(rotate);
    }

    @Override
    public void onDayItemClick(int position, String dayName) {
        getWeatherData(RequestCall(), dayName, position);
    }

    private String LocationName() {
        Intent intent = getIntent();  // Retrieve the Intent that started the activity

        if (intent != null && intent.hasExtra("name")) {
            location_name = intent.getStringExtra("name");
        } else {
            location_name = "New Delhi";
        }
        return location_name;
    }

    private Call<WeatherResponse> RequestCall(){
        String api_key = "69864b428fc948e282131938240708";
        return apiService.getCurrentWeather(api_key, LocationName(), 3);
    }

}
