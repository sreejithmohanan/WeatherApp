package com.sree.weatherapp.webservice;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sree.weatherapp.app.DaggerScope;
import com.sree.weatherapp.app.WeatherApplication;

import javax.inject.Inject;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 *
 * Class create Retrofit object for webservice request.
 */
@DaggerScope(WeatherApplication.Component.class)
public class ApiClient {

    public static final String BASE_URL = "https://api.forecast.io";
    private ApiInterface service;

    @Inject
    public ApiClient() {

        Gson gson = new GsonBuilder().create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .setEndpoint(BASE_URL)
                .setConverter(new GsonConverter(gson))
                .build();

        service = restAdapter.create(ApiInterface.class);
    }

    public ApiInterface getService() {
        return service;
    }






/*
    public static final String BASE_URL = "https://api.forecast.io";
    private static Retrofit retrofit = null;
    @Inject
    private ApiClient() {
    }

    public static Retrofit getApiClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
*/

}
