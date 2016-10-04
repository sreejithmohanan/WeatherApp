package com.sree.weatherapp.webservice;



import com.sree.weatherapp.webservice.responsebean.WeatherSummery;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Interface to handle different webservice api. New Api can defined as ne interface.
 * <p/>
 */
public interface ApiInterface {
    @GET("/forecast/{apiKey}/{location}")
    Call<WeatherSummery> getWeatherUpdate(@Path("apiKey") String apiKey, @Path("location") String location);
}
