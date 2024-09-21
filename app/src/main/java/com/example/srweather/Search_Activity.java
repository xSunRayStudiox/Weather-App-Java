package com.example.srweather;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.srweather.adapter.LocationAdapter;
import com.example.srweather.models.LocationResponse;
import com.example.srweather.networks.RetrofitClient;
import com.example.srweather.networks.WeatherApiService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search_Activity extends AppCompatActivity implements LocationAdapter.OnLocationClickListener {

    private WeatherApiService weatherApiService;
    private LocationAdapter locationAdapter;
    private List<LocationResponse> locationList = new ArrayList<>();
    private static final String API_KEY = "69864b428fc948e282131938240708";

    TextInputEditText searchEditText;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        searchEditText = findViewById(R.id.searchEditText);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        locationAdapter = new LocationAdapter(locationList,this, this);
        recyclerView.setAdapter(locationAdapter);

        weatherApiService = RetrofitClient.getRetrofitInstance().create(WeatherApiService.class);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 2) {
                    searchLocation(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    private void searchLocation(String query) {
        weatherApiService.searchLocation(API_KEY, query).enqueue(new Callback<List<LocationResponse>>() {
            @Override
            public void onResponse(Call<List<LocationResponse>> call, Response<List<LocationResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    locationList = response.body();
                    locationAdapter.updateData(locationList);
                }
            }

            @Override
            public void onFailure(Call<List<LocationResponse>> call, Throwable t) {}
        });
    }

    @Override
    public void onLocationClick(LocationResponse location) {
        Intent i = new Intent(this, Home_Activity.class);
        i.putExtra("name", location.getName());
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}