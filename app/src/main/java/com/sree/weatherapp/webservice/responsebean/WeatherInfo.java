package com.sree.weatherapp.webservice.responsebean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class WeatherInfo implements Serializable{

    @SerializedName("time")
    private long weatherTime;
    @SerializedName("summary")
    private String weatherSummary;
    @SerializedName("icon")
    private String weatherStatus;
    @SerializedName("precipIntensity")
    private float precipIntensity;
    @SerializedName("precipProbability")
    private float precipProbability;
    @SerializedName("precipType")
    private String precipType;
    @SerializedName("temperature")
    private float temperature;
    @SerializedName("apparentTemperature")
    private float apparentTemperature;
    @SerializedName("dewPoint")
    private float dewPoint;
    @SerializedName("windSpeed")
    private float windSpeed;
    @SerializedName("humidity")
    private float humidity;
    @SerializedName("windBearing")
    private int windBearing;
    @SerializedName("cloudCover")
    private float cloudCover;
    @SerializedName("pressure")
    private float pressure;
    @SerializedName("ozone")
    private float ozone;

    public long getWeatherTime() {
        return weatherTime;
    }

    public void setWeatherTime(long weatherTime) {
        this.weatherTime = weatherTime;
    }

    public String getWeatherSummary() {
        return weatherSummary;
    }

    public void setWeatherSummary(String weatherSummary) {
        this.weatherSummary = weatherSummary;
    }

    public String getWeatherStatus() {
        return weatherStatus;
    }

    public void setWeatherStatus(String weatherStatus) {
        this.weatherStatus = weatherStatus;
    }

    public float getPrecipIntensity() {
        return precipIntensity;
    }

    public void setPrecipIntensity(float precipIntensity) {
        this.precipIntensity = precipIntensity;
    }

    public float getPrecipProbability() {
        return precipProbability;
    }

    public void setPrecipProbability(float precipProbability) {
        this.precipProbability = precipProbability;
    }

    public String getPrecipType() {
        return precipType;
    }

    public void setPrecipType(String precipType) {
        this.precipType = precipType;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getApparentTemperature() {
        return apparentTemperature;
    }

    public void setApparentTemperature(float apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
    }

    public float getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(float dewPoint) {
        this.dewPoint = dewPoint;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public int getWindBearing() {
        return windBearing;
    }

    public void setWindBearing(int windBearing) {
        this.windBearing = windBearing;
    }

    public float getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(float cloudCover) {
        this.cloudCover = cloudCover;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getOzone() {
        return ozone;
    }

    public void setOzone(float ozone) {
        this.ozone = ozone;
    }
}
