package com.example.srweather.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.srweather.Home_Activity;
import com.example.srweather.R;
import com.example.srweather.models.LocationResponse;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    private List<LocationResponse> locations;
    private Context context;
    private OnLocationClickListener onLocationClickListener;

    // Constructor with the listener
    public LocationAdapter(List<LocationResponse> locations, Context context, OnLocationClickListener listener) {
        this.locations = locations;
        this.context = context;
        this.onLocationClickListener = listener;
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_layout, parent, false);
        return new LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {

        LocationResponse location = locations.get(position);
        holder.textView.setText(location.getName()+", "+location.getRegion());

        holder.textView.setOnClickListener(v -> {
            if (onLocationClickListener != null) {
                onLocationClickListener.onLocationClick(location);
            }
        });
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    static class LocationViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView textView;

        LocationViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.location_text);
        }
    }

    public void updateData(List<LocationResponse> newLocations) {
        this.locations = newLocations;
        notifyDataSetChanged();
    }

    public interface OnLocationClickListener {
        void onLocationClick(LocationResponse location);
    }

}

