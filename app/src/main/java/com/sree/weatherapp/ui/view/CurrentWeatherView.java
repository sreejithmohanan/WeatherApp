package com.sree.weatherapp.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sree.weatherapp.R;
import com.sree.weatherapp.app.DaggerService;
import com.sree.weatherapp.ui.common.OnListFragmentInteractionListener;
import com.sree.weatherapp.ui.screen.CurrentWeatherInfoScreen;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Sreejith on 04-11-2016.
 */

public class CurrentWeatherView extends RelativeLayout {


    @InjectView(R.id.txt_current_temperature)
    public TextView txtCurrentTemperature;
    @InjectView(R.id.id_current_weather_info)
    public TextView txtCurrentWeatherInfo;
    @InjectView(R.id.txt_current_humidity)
    public TextView txtCurrentHumidity;
    @InjectView(R.id.txt_current_duepoint)
    public  TextView txtCurrentDuePoint;
    @InjectView(R.id.txt_current_pressure)
    public TextView txtCurrentPressure;

    @Inject
    protected CurrentWeatherInfoScreen.Presenter presenter;


    public CurrentWeatherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        DaggerService.<CurrentWeatherInfoScreen.Component>getDaggerComponent(context).inject(this);
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
