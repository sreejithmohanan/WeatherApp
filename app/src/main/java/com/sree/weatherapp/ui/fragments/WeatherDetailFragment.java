package com.sree.weatherapp.ui.fragments;

import android.support.v4.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Fragment for display detail weather information.
 */
public class WeatherDetailFragment{
   /*     extends Fragment {
    private WeatherInfo weatherInfo;
    OnListFragmentInteractionListener mListener;
    @BindView(R.id.txt_daily_precipitation_intensity)
    TextView txtPrecipitationIntensity;
    @BindView(R.id.txt_daily_precipitation_probability)
    TextView txtPrecipitationProbablity;
    @BindView(R.id.txt_daily_dew_point)
    TextView txtDewPoint;
    @BindView(R.id.txt_daily_relative_humidity)
    TextView txtRelativeHumidity;
    @BindView(R.id.txt_daily_pressure)
    TextView txtPressure;
    @BindView(R.id.txt_daily_wind_speed)
    TextView txtWindSpeed;
    @BindView(R.id.txt_daily_wind_bearing)
    TextView txtWindBearing;
    @BindView(R.id.txt_daily_cloud_cover)
    TextView txtCloudCover;
    @BindView(R.id.txt_daily_ozone)
    TextView txtOzone;
    @BindView(R.id.txt_daily_sunrise_time)
    TextView txtSunriseTime;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            weatherInfo = (WeatherInfo) bundle.getSerializable(AppConstants.IntentConstants.WEATHER_INFO);
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.screen_weather_detail, container, false);
        ButterKnife.bind(this, view);
        populateView();
        return view;
    }

    *//**
     * Populate the weather information to view.
     *//*
    private void populateView() {
        if (weatherInfo != null) {
            String time = Utils.getTimeFromSeconds(weatherInfo.getWeatherTime(), null);
            txtSunriseTime.setText(time);
            txtPrecipitationIntensity.setText(String.valueOf(weatherInfo.getPrecipIntensity()));
            txtPrecipitationProbablity.setText(String.valueOf(weatherInfo.getPrecipProbability()));
            txtDewPoint.setText(weatherInfo.getDewPoint() + AppConstants.UnitsConstants.UNIT_TEMPRATURE);
            txtRelativeHumidity.setText(String.valueOf(weatherInfo.getHumidity()));
            txtPressure.setText(weatherInfo.getPressure() + AppConstants.UnitsConstants.UNIT_PRESSURE);
            txtWindSpeed.setText(weatherInfo.getWindSpeed() + AppConstants.UnitsConstants.UNIT_MPH);
            txtWindBearing.setText(String.valueOf(weatherInfo.getWindBearing()));
            txtCloudCover.setText(String.valueOf(weatherInfo.getCloudCover()));
            txtOzone.setText(weatherInfo.getOzone() + AppConstants.UnitsConstants.UNIT_OZON);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
*/

}
