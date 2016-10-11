package com.sree.weatherapp.utils;




import com.sree.weatherapp.R;
import com.sree.weatherapp.common.AppConstants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static com.sree.weatherapp.common.AppConstants.DateTimeConstants.DATE_TIME_PATTERN_DD_MMM_YYYY_HH_MM;


public class Utils {
    public static String getTimeFromSeconds(long seconds, String datePattern) {
        String toReturn = null;
        if (seconds > 0) {
            long timeInMillis = seconds * 1000;
            SimpleDateFormat dateFormat = new SimpleDateFormat();
            if (datePattern == null) {
                dateFormat.applyPattern(DATE_TIME_PATTERN_DD_MMM_YYYY_HH_MM);
            } else {
                dateFormat.applyPattern(datePattern);
            }

            dateFormat.setTimeZone(TimeZone.getDefault());
            toReturn = dateFormat.format(new Date(timeInMillis));
        }
        return toReturn;

    }

    public static int getWeatherIcons(String iconString) {
        int drawableId = 0;
        if (iconString != null) {
            switch (iconString) {
                case AppConstants.WeatherIconConstants.WEATHER_CLEAR_DAY:
                    drawableId = R.drawable.ic_clear_day_small;
                    break;
                case AppConstants.WeatherIconConstants.WEATHER_CLEAR_NIGHT:
                    drawableId=R.drawable.ic_clear_night_small;
                    break;

                case AppConstants.WeatherIconConstants.WEATHER_CLOUDY:
                    drawableId =  R.drawable.ic_cloudy_small;
                    break;
                case AppConstants.WeatherIconConstants.WEATHER_FOG:
                    drawableId=R.drawable.ic_fog;
                    break;

                case AppConstants.WeatherIconConstants.WEATHER_PARTLY_CLOUDY_DAY:
                    drawableId=R.drawable.ic_partialy_cloudy_day;
                    break;
                case AppConstants.WeatherIconConstants.WEATHER_PARTLY_CLOUDY_NIGHT:
                    drawableId=R.drawable.ic_partialy_cloudy_night;
                    break;

                case AppConstants.WeatherIconConstants.WEATHER_RAIN:
                    drawableId = R.drawable.ic_rain_small;
                    break;

                case AppConstants.WeatherIconConstants.WEATHER_SNOW:
                    drawableId =  R.drawable.ic_snow_small;
                    break;


                case AppConstants.WeatherIconConstants.WEATHER_WIND:
                    drawableId=R.drawable.ic_wind;
                    break;

            }
        }

        return drawableId;
    }


   /* public static int getWeatherIcons( String iconString, boolean isThumbnail) {
        int drawableId = 0;
        if ( iconString != null) {
            switch (iconString) {
                case AppConstants.WeatherIconConstants.WEATHER_CLEAR_DAY:
                    drawableId=isThumbnail?R.drawable.ic_clear_day_small:R.drawable.ic_clear_day;
                    break;
                case AppConstants.WeatherIconConstants.WEATHER_CLEAR_NIGHT:
                    drawableId=isThumbnail?R.drawable.ic_clear_night_small:R.drawable.ic_clear_night;
                    break;

                case AppConstants.WeatherIconConstants.WEATHER_CLOUDY:
                    drawableId=isThumbnail?R.drawable.ic_cloudy_small:R.drawable.ic_cloudy;
                    break;
                case AppConstants.WeatherIconConstants.WEATHER_FOG:
                    drawableId=isThumbnail?R.drawable.ic_fog_small:R.drawable.ic_fog;
                    break;

                case AppConstants.WeatherIconConstants.WEATHER_PARTLY_CLOUDY_DAY:
                    drawableId=isThumbnail?R.drawable.ic_partialy_cloudy_day_small:R.drawable.ic_partialy_cloudy_day;
                    break;
                case AppConstants.WeatherIconConstants.WEATHER_PARTLY_CLOUDY_NIGHT:
                    drawableId=isThumbnail?R.drawable.ic_partialy_cloudy_night_small:R.drawable.ic_partialy_cloudy_night;
                    break;

                case AppConstants.WeatherIconConstants.WEATHER_RAIN:
                    drawableId=isThumbnail?R.drawable.ic_rain_small:R.drawable.ic_rain;
                    break;

                case AppConstants.WeatherIconConstants.WEATHER_SNOW:
                    drawableId=isThumbnail?R.drawable.ic_snow_small:R.drawable.ic_snow;
                    break;

                case AppConstants.WeatherIconConstants.WEATHER_WIND:
                    drawableId=isThumbnail?R.drawable.ic_wind_small:R.drawable.ic_wind;
                    break;

            }
        }

        return drawableId;
    }*/
}
