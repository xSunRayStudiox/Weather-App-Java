package com.example.srweather.models;

public class DayModel {
    private String dayName;
    private String date;
    private String dayWeatherIcon;
    private String dayMiMaxTemp;

    public DayModel(String dayName, String date, String dayWeatherIcon, String dayMiMaxTemp) {
        this.dayName = dayName;
        this.date = date;
        this.dayWeatherIcon = dayWeatherIcon;
        this.dayMiMaxTemp = dayMiMaxTemp;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDayWeatherIcon() {
        return dayWeatherIcon;
    }

    public void setDayWeatherIcon(String dayWeatherIcon) {
        this.dayWeatherIcon = dayWeatherIcon;
    }

    public String getDayMiMaxTemp() {
        return dayMiMaxTemp;
    }

    public void setDayMiMaxTemp(String dayMiMaxTemp) {
        this.dayMiMaxTemp = dayMiMaxTemp;
    }
}
