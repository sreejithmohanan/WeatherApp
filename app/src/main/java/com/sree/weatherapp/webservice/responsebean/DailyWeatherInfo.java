package com.sree.weatherapp.webservice.responsebean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class DailyWeatherInfo implements Serializable {

    @SerializedName("summary")
    private String summary;
    @SerializedName("icon")
    private String weatherStatus;
    @SerializedName("data")
    private List<WeatherInfo> dailyWeatherInfoList;

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

    public List<WeatherInfo> getDailyWeatherInfoList() {
        return dailyWeatherInfoList;
    }

    public void setDailyWeatherInfoList(List<WeatherInfo> dailyWeatherInfoList) {
        this.dailyWeatherInfoList = dailyWeatherInfoList;
    }
}
