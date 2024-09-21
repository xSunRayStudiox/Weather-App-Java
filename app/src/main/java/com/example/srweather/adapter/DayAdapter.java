package com.example.srweather.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.srweather.Home_Activity;
import com.example.srweather.R;
import com.example.srweather.models.DayModel;

import org.w3c.dom.Text;

import java.util.List;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.ViewHolder> {

    List<DayModel> dayModelList;
    Context context;
    int isSelected = 0;
    private final DayItemClickListener dayItemClickListener;

    public interface DayItemClickListener {
        void onDayItemClick(int position, String dayName);
    }

    public DayAdapter(List<DayModel> dayModelList, Context context, DayItemClickListener dayItemClickListener) {
        this.dayModelList = dayModelList;
        this.context = context;
        this.dayItemClickListener = dayItemClickListener;
    }

    @NonNull
    @Override
    public DayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day_layout,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayAdapter.ViewHolder holder, int position) {
        DayModel dayModel = dayModelList.get(position);
        holder.day_name.setText(dayModel.getDayName());
        holder.dayTemp.setText(dayModel.getDayMiMaxTemp());
        Glide.with(context).load(dayModel.getDayWeatherIcon()).into(holder.day_weather_icon);

        if (position == isSelected){
            holder.view.setBackgroundColor(Color.parseColor("#0080FF"));
        } else {
            holder.view.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        }

        holder.itemView.setOnClickListener(v -> {
            int oldPosition = isSelected;
            isSelected = holder.getAdapterPosition();
            notifyItemChanged(oldPosition);
            notifyItemChanged(isSelected);
            dayItemClickListener.onDayItemClick(isSelected,dayModel.getDate());
        });
    }

    @Override
    public int getItemCount() {
        return dayModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView day_weather_icon;
        TextView day_name, dayTemp;
        View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            day_name = itemView.findViewById(R.id.day_name);
            dayTemp = itemView.findViewById(R.id.day_temp_max_min);
            day_weather_icon = itemView.findViewById(R.id.day_weather_icon);
            view = itemView.findViewById(R.id.day_selected);
        }
    }
}
