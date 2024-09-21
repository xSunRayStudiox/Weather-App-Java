package com.example.srweather.models;

public class HoursModel {

    private String hours;
    private String hourlyWeatherIcon;
    private String hourlyTemp;

    private int it_rain;
    private int chance_rain;
    private int it_snow;
    private int chance_snow;

    public HoursModel(String hours, String hourlyWeatherIcon, String hourlyTemp, int it_rain, int chance_rain, int it_snow, int chance_snow) {
        this.hours = hours;
        this.hourlyWeatherIcon = hourlyWeatherIcon;
        this.hourlyTemp = hourlyTemp;
        this.it_rain = it_rain;
        this.chance_rain = chance_rain;
        this.it_snow = it_snow;
        this.chance_snow = chance_snow;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getHourlyWeatherIcon() {
        return hourlyWeatherIcon;
    }

    public void setHourlyWeatherIcon(String hourlyWeatherIcon) {
        this.hourlyWeatherIcon = hourlyWeatherIcon;
    }

    public String getHourlyTemp() {
        return hourlyTemp;
    }

    public void setHourlyTemp(String hourlyTemp) {
        this.hourlyTemp = hourlyTemp;
    }

    public int getIt_rain() {
        return it_rain;
    }

    public void setIt_rain(int it_rain) {
        this.it_rain = it_rain;
    }

    public int getChance_rain() {
        return chance_rain;
    }

    public void setChance_rain(int chance_rain) {
        this.chance_rain = chance_rain;
    }

    public int getIt_snow() {
        return it_snow;
    }

    public void setIt_snow(int it_snow) {
        this.it_snow = it_snow;
    }

    public int getChance_snow() {
        return chance_snow;
    }

    public void setChance_snow(int chance_snow) {
        this.chance_snow = chance_snow;
    }
}
