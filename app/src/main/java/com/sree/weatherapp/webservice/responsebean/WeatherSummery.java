package com.sree.weatherapp.webservice.responsebean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class WeatherSummery implements Serializable {

    @SerializedName("latitude")
    private float latitude;
    @SerializedName("longitude")
    private float longitude;
    @SerializedName("offset")
    private float offset;
    @SerializedName("currently")
    private WeatherInfo currentWeatherInfo;
    @SerializedName("hourly")
    private HourlyWeatherInfo hourlyWeatherInfo;
    @SerializedName("daily")
    private DailyWeatherInfo dailyWeatherInfo;
    @SerializedName("isd-stations")
    private List<String> isdStations;
    @SerializedName("units")
    private String units;

    public DailyWeatherInfo getDailyWeatherInfo() {
        return dailyWeatherInfo;
    }

    public void setDailyWeatherInfo(DailyWeatherInfo dailyWeatherInfo) {
        this.dailyWeatherInfo = dailyWeatherInfo;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getOffset() {
        return offset;
    }

    public void setOffset(float offset) {
        this.offset = offset;
    }

    public WeatherInfo getCurrentWeatherInfo() {
        return currentWeatherInfo;
    }

    public void setCurrentWeatherInfo(WeatherInfo currentWeatherInfo) {
        this.currentWeatherInfo = currentWeatherInfo;
    }

    public HourlyWeatherInfo getHourlyWeatherInfo() {
        return hourlyWeatherInfo;
    }

    public void setHourlyWeatherInfo(HourlyWeatherInfo hourlyWeatherInfo) {
        this.hourlyWeatherInfo = hourlyWeatherInfo;
    }

    public List<String> getIsdStations() {
        return isdStations;
    }

    public void setIsdStations(List<String> isdStations) {
        this.isdStations = isdStations;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }
}
