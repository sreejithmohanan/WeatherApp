package com.sree.weatherapp.app;

import android.app.Application;


import mortar.MortarScope;


public class WeatherApplication extends Application {

   // private WebServiceComponent webServiceComponent;
    public static final String BASE_URL = "https://api.forecast.io";

    private MortarScope mortarScope;

    @Override
    public Object getSystemService(String name) {
        return mortarScope.hasService(name) ? mortarScope.getService(name) : super.getSystemService(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();


        Component component = DaggerWeatherApplication_Component.create();
        component.inject(this);

        mortarScope = MortarScope.buildRootScope()
                .withService(DaggerService.SERVICE_NAME, component)
                .build("Root");
/*

        webServiceComponent = DaggerWebServiceComponent.builder()
                .appModule(new AppModule(this))
                .webServiceModule(new WebServiceModule(BASE_URL))
                .build();
*/


    }

   /* public WebServiceComponent getWebServiceComponent() {
        return webServiceComponent;
    }*/

    @dagger.Component
    @DaggerScope(Component.class)
    public interface Component extends AppDependencies {
        void inject(WeatherApplication app);
    }
}


