package com.sree.weatherapp;

import android.app.Application;

import com.sree.weatherapp.component.DaggerWebServiceComponent;
import com.sree.weatherapp.component.WebServiceComponent;
import com.sree.weatherapp.module.AppModule;
import com.sree.weatherapp.module.WebServiceModule;
import com.sree.weatherapp.webservice.ApiClient;


public class WeatherApplication extends Application {

    private WebServiceComponent webServiceComponent;
    public static final String BASE_URL = "https://api.forecast.io";
    @Override
    public void onCreate() {
        super.onCreate();

        webServiceComponent = DaggerWebServiceComponent.builder()
                .appModule(new AppModule(this))
                .webServiceModule(new WebServiceModule(BASE_URL))
                .build();
    }

    public WebServiceComponent getWebServiceComponent() {
        return webServiceComponent;
    }

}
