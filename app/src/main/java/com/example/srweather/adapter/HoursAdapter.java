package com.example.srweather.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.srweather.R;
import com.example.srweather.models.HoursModel;
import com.example.srweather.utils.TimeFilter;

import java.util.Calendar;
import java.util.List;

public class HoursAdapter extends RecyclerView.Adapter<HoursAdapter.ViewHolder> {

    private final List<HoursModel> list;
    private final Context context;
    private final int pos;

    // Constructor to initialize the list, context, and position
    public HoursAdapter(List<HoursModel> list, Context context, int pos) {
        this.list = list;
        this.context = context;
        this.pos = pos;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public HoursAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hours_layout, parent, false);
        return new HoursAdapter.ViewHolder(view);
    }

    // Binds the data to the views
    @Override
    public void onBindViewHolder(@NonNull HoursAdapter.ViewHolder holder, int position) {
        HoursModel currentHour = list.get(position);

        // Set temperature text
        holder.HourlyTemp.setText(currentHour.getHourlyTemp());

        // Load weather icon using Glide
        Glide.with(context).load(currentHour.getHourlyWeatherIcon()).into(holder.condition_image);

        // Get hour and weather conditions (rain/snow) from the model
        int listHour = TimeFilter.onlyHours(currentHour.getHours());
        int rain = currentHour.getIt_rain();
        int snow = currentHour.getIt_snow();

        // Get the current hour
        int currentHourOfDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        // If the current hour matches the list hour, mark the current time block
        if (currentHourOfDay == listHour && pos == 0) {
            holder.timeHourly.setText("Now");
            holder.changeColor.setBackgroundColor(Color.parseColor("#E0E8EF")); // Change background color
        } else {
            // Set hour text in AM/PM format
            holder.timeHourly.setText(TimeFilter.HoursIn_AM_PM(currentHour.getHours()));
            holder.changeColor.setBackgroundColor(Color.parseColor("#FFFFFF")); // Change background color
        }

        // Show rain or snow chance, with appropriate icon
        if (rain > 0) {
            holder.weatherChance.setVisibility(View.VISIBLE);
            holder.weatherChance.setText(currentHour.getChance_rain());
            holder.weatherChance.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_rainy_24px, 0, 0, 0);
        } else if (snow > 0) {
            holder.weatherChance.setVisibility(View.VISIBLE);
            holder.weatherChance.setText(currentHour.getChance_rain());
            holder.weatherChance.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_cloudy_snowing_24px, 0, 0, 0);
        } else {
            holder.weatherChance.setVisibility(View.GONE); // Hide if no rain/snow
        }
    }

    // Returns the size of the dataset
    @Override
    public int getItemCount() {
        return list.size();
    }

    // ViewHolder class to hold the references to the views for each item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView timeHourly, weatherChance, HourlyTemp;
        ImageView condition_image;
        LinearLayout changeColor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            timeHourly = itemView.findViewById(R.id.time_hourly);
            weatherChance = itemView.findViewById(R.id.weather_chance);
            HourlyTemp = itemView.findViewById(R.id.hourly_temp);
            condition_image = itemView.findViewById(R.id.condition_icon);
            changeColor = itemView.findViewById(R.id.color_change);
        }
    }
}
