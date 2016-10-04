package com.sree.weatherapp.controlers;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.sree.weatherapp.common.AppConstants;
import com.sree.weatherapp.webservice.ApiClient;
import com.sree.weatherapp.webservice.ApiInterface;
import com.sree.weatherapp.webservice.responsebean.WeatherSummery;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Controller class for handle the location data and web service response. It will give call back to
 */
public class WeatherDataProcessor implements LocationProvider.InLocationUpdateListener {

    private static WeatherDataProcessor ourInstance = new WeatherDataProcessor();
    public static final String TAG = "WeatherDataProcessor";
    private IDataProvider dataProvider;

    public void setDataProvider(IDataProvider dataProvider) {
        this.dataProvider = dataProvider;

    }

    /**
     * Function responsible for registering googleApi client. On success full connection will
     * get location update call back in onLocationUpdate function. For any error will get call back in
     * onError function with error code.
     *
     * @param mContext
     */
    public void requestForWeatherUpdate(Context mContext) {
        LocationProvider locationProvider = new LocationProvider(this, mContext);
        locationProvider.registerGoogleApiClient();

    }

    public static WeatherDataProcessor getInstance() {
        return ourInstance;
    }

    private WeatherDataProcessor() {
    }

    @Override
    public void onLocationUpdate(String latLong) {
        getWeatherUpdate(latLong);
    }

    @Override
    public void onError(int responseCode) {

    }

    /**
     * Function invoke weather Api for weather broadcast.
     * @param currentLocation
     */
    private void getWeatherUpdate(String currentLocation) {
        ApiInterface apiService =
                ApiClient.getApiClient().create(ApiInterface.class);

        Call<WeatherSummery> call = apiService.getWeatherUpdate(AppConstants.WebServiceConstants.API_KEY, currentLocation);
        call.enqueue(new Callback<WeatherSummery>() {


            @Override
            public void onResponse(Call<WeatherSummery> call, Response<WeatherSummery> response) {

                WeatherSummery weatherSummery = response.body();

                if (dataProvider != null) {
                    dataProvider.onDataReceived(weatherSummery);
                }
                Gson gson = new Gson();
                Log.i(TAG, gson.toJson(weatherSummery, WeatherSummery.class));
            }

            @Override
            public void onFailure(Call<WeatherSummery> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    /**
     * Interface to communicate with ui from data model.
     */
    public interface IDataProvider {
        void onDataReceived(WeatherSummery weatherSummery);

        void onDataError();
    }
}
