package com.sree.weatherapp.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.sree.weatherapp.R;
import com.sree.weatherapp.common.AppConstants;
import com.sree.weatherapp.ui.common.OnListFragmentInteractionListener;
import com.sree.weatherapp.ui.core.BaseFragment;
import com.sree.weatherapp.webservice.responsebean.WeatherInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Fragment show the current weather information.
 */
public class CurrentWeatherInfoFragment extends BaseFragment {
    @BindView(R.id.txt_current_temperature)
    TextView txtCurrentTemperature;
    @BindView(R.id.id_current_weather_info)
    TextView txtCurrentWeatherInfo;
    @BindView(R.id.txt_current_humidity)
    TextView txtCurrentHumidity;
    @BindView(R.id.txt_current_duepoint)
    TextView txtCurrentDuePoint;
    @BindView(R.id.txt_current_pressure)
    TextView txtCurrentPressure;
    private OnListFragmentInteractionListener mListener;

    private WeatherInfo currentWeatherInfo;

    public CurrentWeatherInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public String getFragmentName() {
        return "Current";
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Bundle bundle = getArguments();
        if (bundle != null) {
            currentWeatherInfo = (WeatherInfo) bundle.getSerializable(AppConstants.IntentConstants.WEATHER_INFO);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current_weather_info, container, false);
        ButterKnife.bind(this, view);
        populateView();
        return view;


    }

    @SuppressLint("SetTextI18n")
    private void populateView() {
        if (currentWeatherInfo != null) {
            txtCurrentTemperature.setText(String.valueOf(Math.round(currentWeatherInfo.getTemperature())) + "°");
            txtCurrentWeatherInfo.setText(String.valueOf(currentWeatherInfo.getWeatherSummary()));
            txtCurrentHumidity.setText(String.valueOf(currentWeatherInfo.getHumidity()));
            txtCurrentDuePoint.setText(String.valueOf(currentWeatherInfo.getDewPoint()));
            txtCurrentPressure.setText(String.valueOf(currentWeatherInfo.getPressure()));

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

}
