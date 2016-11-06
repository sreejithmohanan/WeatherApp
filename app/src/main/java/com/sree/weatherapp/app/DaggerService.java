package com.sree.weatherapp.app;

import android.content.Context;

/**
 * Created by Sreejith on 03-11-2016.
 */

public class DaggerService { public static final String SERVICE_NAME = DaggerService.class.getName();

    /**
     * Caller is required to know the type of the component for this context.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getDaggerComponent(Context context) {
        //noinspection ResourceType
        return (T) context.getSystemService(SERVICE_NAME);
    }
}

