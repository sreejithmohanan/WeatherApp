package com.sree.weatherapp.component;

import com.sree.weatherapp.module.AppModule;
import com.sree.weatherapp.module.WebServiceModule;
import com.sree.weatherapp.ui.activity.HomeActivity;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules={AppModule.class, WebServiceModule.class})
public interface WebServiceComponent {
    void inject(HomeActivity activity);
}
