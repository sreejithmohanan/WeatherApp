package com.sree.weatherapp.module;

import com.sree.weatherapp.app.WeatherApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    WeatherApplication mWeatherApplication;

    public AppModule(WeatherApplication application) {
        mWeatherApplication = application;
    }

    @Provides
    @Singleton
    WeatherApplication provideWeatherApplication() {
        return mWeatherApplication;
    }
}
