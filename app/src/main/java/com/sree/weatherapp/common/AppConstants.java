package com.sree.weatherapp.common;

/**
 */
public class AppConstants {

    public interface IntentConstants {
        String WEATHER_SUMMERY = "weatherSummery";
        String WEATHER_INFO = "weatherInfo";
    }

    public interface WebServiceConstants {
        String API_KEY = "2bf6dd4d03571155f631e7dae5b12fb6";
    }

    public interface DateTimeConstants {
        String DATE_TIME_PATTERN_DD_MMM_hh_mm_a = "dd MMM hh:mm a";
        String DATE_TIME_PATTERN_DD_MMM_YYYY_HH_MM = "dd MMM yyyy hh:mm a";
        String DATE_TIME_PATTERN_DD_MM = "dd MMM";
    }

    public interface UnitsConstants {
        String UNIT_TEMPRATURE = " °F";
        String UNIT_PRESSURE = " millibars";
        String UNIT_OZON = " Dobson";
        String UNIT_MPH = " mph";
    }

    public interface WeatherIconConstants {
        String WEATHER_CLEAR_DAY = "clear-day";
        String WEATHER_CLEAR_NIGHT = "clear-night";
        String WEATHER_RAIN = "rain";
        String WEATHER_SNOW = "snow";
        String WEATHER_WIND = "wind";
        String WEATHER_FOG = "fog";
        String WEATHER_CLOUDY = "cloudy";
        String WEATHER_PARTLY_CLOUDY_DAY = "partly-cloudy-day";
        String WEATHER_PARTLY_CLOUDY_NIGHT = "partly-cloudy-night";
    }


}
