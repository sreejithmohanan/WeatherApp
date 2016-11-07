package com.sree.weatherapp.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sree.weatherapp.R;
import com.sree.weatherapp.app.DaggerService;
import com.sree.weatherapp.ui.common.OnListFragmentInteractionListener;
import com.sree.weatherapp.ui.screen.WeatherDetailScreen;
import com.sree.weatherapp.webservice.responsebean.WeatherInfo;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Sreejith on 07-11-2016.
 */

public class WeatherDetailView extends RelativeLayout {

    private WeatherInfo weatherInfo;
    OnListFragmentInteractionListener mListener;
    @InjectView(R.id.txt_daily_precipitation_intensity)
    public TextView txtPrecipitationIntensity;
    @InjectView(R.id.txt_daily_precipitation_probability)
    public TextView txtPrecipitationProbablity;
    @InjectView(R.id.txt_daily_dew_point)
    public TextView txtDewPoint;
    @InjectView(R.id.txt_daily_relative_humidity)
    public TextView txtRelativeHumidity;
    @InjectView(R.id.txt_daily_pressure)
    public TextView txtPressure;
    @InjectView(R.id.txt_daily_wind_speed)
    public TextView txtWindSpeed;
    @InjectView(R.id.txt_daily_wind_bearing)
    public TextView txtWindBearing;
    @InjectView(R.id.txt_daily_cloud_cover)
    public TextView txtCloudCover;
    @InjectView(R.id.txt_daily_ozone)
    public TextView txtOzone;
    @InjectView(R.id.txt_daily_sunrise_time)
    public TextView txtSunriseTime;



    @Inject
    protected WeatherDetailScreen.Presenter presenter;


    public WeatherDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        DaggerService.<WeatherDetailScreen.Component>getDaggerComponent(context).inject(this);
    }

    public void show() {
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        presenter.takeView(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        presenter.dropView(this);
        super.onDetachedFromWindow();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.inject(this);
    }
}
