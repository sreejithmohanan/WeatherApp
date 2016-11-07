package com.sree.weatherapp.ui.common;


import com.sree.weatherapp.webservice.responsebean.WeatherInfo;

/**
 * Inter face to communicate between fragment and activity.
 * Created by Sreejith on 28-08-2016.
 */
public interface OnListFragmentInteractionListener {
    void onListFragmentInteraction(WeatherInfo item);
}
