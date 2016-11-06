package com.sree.weatherapp.app;

import com.sree.weatherapp.webservice.ApiClient;

/**
 * Created by Sreejith on 03-11-2016.
 */

public interface AppDependencies {

    ApiClient restClient();
}
