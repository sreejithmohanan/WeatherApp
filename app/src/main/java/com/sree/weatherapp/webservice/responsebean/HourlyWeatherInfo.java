package com.sree.weatherapp.webservice.responsebean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class HourlyWeatherInfo implements Serializable {

    @SerializedName("summary")
    private String summary;
    @SerializedName("icon")
    private String weatherStatus;
    @SerializedName("data")
    private List<WeatherInfo> hourlyWeatherInfoList;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getWeatherStatus() {
        return weatherStatus;
    }

    public void setWeatherStatus(String weatherStatus) {
        this.weatherStatus = weatherStatus;
    }

    public List<WeatherInfo> getHourlyWeatherInfoList() {
        return hourlyWeatherInfoList;
    }

    public void setHourlyWeatherInfoList(List<WeatherInfo> hourlyWeatherInfoList) {
        this.hourlyWeatherInfoList = hourlyWeatherInfoList;
    }
}
