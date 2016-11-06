package com.sree.weatherapp.webservice;


import com.sree.weatherapp.webservice.responsebean.WeatherSummery;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Interface to handle different webservice api. New Api can defined as ne interface.
 * <p/>
 */
public interface ApiInterface {
   // @GET("/forecast/{apiKey}/{location}")
   // Call<WeatherSummery> getWeatherUpdate(@Path("apiKey") String apiKey, @Path("location") String location);

    @GET("/forecast/{apiKey}/{location}")
    void getPosts(Callback<List<WeatherSummery>> callback);

}
