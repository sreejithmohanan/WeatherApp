package com.sree.weatherapp.webservice;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * Class create Retrofit object for webservice request.
 */
public class ApiClient {
    public static final String BASE_URL = "https://api.forecast.io";
    private static Retrofit retrofit = null;

    private ApiClient() {
    }

    public static Retrofit getApiClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}
