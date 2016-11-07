package com.sree.weatherapp.ui.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.sree.weatherapp.R;
import com.sree.weatherapp.app.DaggerService;
import com.sree.weatherapp.ui.screen.DailyWeatherInfoScreen;
import com.sree.weatherapp.ui.screen.HourlyWeatherInfoScreen;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Sreejith on 07-11-2016.
 */

public class DailyWeatherInfoView extends LinearLayout {
    @Inject
    protected DailyWeatherInfoScreen.Presenter presenter;

    @InjectView(R.id.list)
    public RecyclerView recyclerView;

    @InjectView(R.id.progress)
    public ProgressBar progressBar;

    public DailyWeatherInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        DaggerService.<DailyWeatherInfoScreen.Component>getDaggerComponent(context).inject(this);
    }

    public void show() {
        progressBar.setVisibility(GONE);
        recyclerView.setVisibility(VISIBLE);
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
