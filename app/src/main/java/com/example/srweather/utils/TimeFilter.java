package com.example.srweather.utils;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeFilter {

    public static int onlyHours (String value){
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat format = new SimpleDateFormat("HH");
        int hour = 0;
        try {
            Date date = input.parse(value);
            if (date != null) {hour = Integer.parseInt(format.format(date));}
        } catch (ParseException e) {e.printStackTrace();}
        return hour;
    }

    public static String HoursIn_AM_PM (String value){
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat format = new SimpleDateFormat("hh:mm a");
        String hour = "";
        try {
            Date date = input.parse(value);
            if (date != null) {hour = format.format(date);}
        } catch (ParseException e) {e.printStackTrace();}
        return hour;
    }

    public static String WeekName(String value) {
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat format = new SimpleDateFormat("EEEE", Locale.getDefault());

        String name = "";
        try {
            Date date = input.parse(value);
            if (date != null) {name = format.format(date);}
        } catch (ParseException e) {e.printStackTrace();}
        return name;
    }

    public static String currentDate(){
        Date currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(currentDate);
        return formattedDate;
    }

    public static String currentHours(){
        Date currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("HH");
        String output = format.format(currentDate);
        return output;
    }
}
