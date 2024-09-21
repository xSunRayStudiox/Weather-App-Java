package com.example.srweather.models;

import java.util.List;

public class WeatherResponse {

    public Location location;
    public Current current;
    public Forecast forecast;

    public static class Location {
        public String name;
        public String region;
        public String country;
    }

    public static class Current {
        public double temp_c;
        public double humidity;
        public double feelslike_c;
        public Condition condition;
        public double wind_kph;
        public int wind_degree;
        public double windchill_c;
        public double pressure_mb;

    }

    public static class Condition {
        public String text;
        public String icon;
    }

    public static class Day {
        public double avghumidity;
        public double maxtemp_c;
        public double mintemp_c;
        public double maxwind_kph;
        public Condition condition;
        public double uv;
        public double avgvis_km;
        public int daily_will_it_rain;
        public double totalprecip_mm;
        public int daily_chance_of_rain;
        public int daily_will_it_snow;
        public double totalsnow_cm;
        public int daily_chance_of_snow;
    }

    public static class Astro {
        public String sunrise;
        public String sunset;
        public String moonrise;
        public String moonset;
        public String moon_phase;
    }

    public static class Hour {
        public String time;
        public double temp_c;
        public Condition condition;
        public int wind_degree;
        public double pressure_mb;
        public int will_it_rain;
        public int will_it_snow;
        public int chance_of_rain;
        public int chance_of_snow;
    }

    public static class Forecast {
        public List<ForecastDay> forecastday;
    }

    public static class ForecastDay {
        public String date;
        public Day day;
        public Astro astro;
        public List<Hour> hour;
    }

}
