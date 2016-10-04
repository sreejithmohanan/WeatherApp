package com.sree.weatherapp.webservice.responsebean;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Flags {
    @SerializedName("sources")
    private List<String> sources;

}
