package com.sree.weatherapp.module;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.sree.weatherapp.app.WeatherApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class WebServiceModule {

    String mBaseUrl;

    public WebServiceModule(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(WeatherApplication application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    /*@Provides
    @Singleton
    Cache provideOkHttpCache(WeatherApplication application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient client = new OkHttpClient();
        return client;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
        return retrofit;
    }
*/}
