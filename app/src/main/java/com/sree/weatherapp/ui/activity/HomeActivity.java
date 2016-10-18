package com.sree.weatherapp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.squareup.okhttp.OkHttpClient;
import com.sree.weatherapp.R;
import com.sree.weatherapp.WeatherApplication;
import com.sree.weatherapp.controlers.WeatherDataProcessor;
import com.sree.weatherapp.ui.adapter.HomePagerAdapter;
import com.sree.weatherapp.ui.common.OnListFragmentInteractionListener;
import com.sree.weatherapp.ui.core.BaseFragment;
import com.sree.weatherapp.ui.fragments.CurrentWeatherInfoFragment;
import com.sree.weatherapp.ui.fragments.DailyWeatherInfoFragment;
import com.sree.weatherapp.ui.fragments.HourlyWeatherInfoFragment;
import com.sree.weatherapp.ui.fragments.WeatherDetailFragment;
import com.sree.weatherapp.webservice.responsebean.WeatherInfo;
import com.sree.weatherapp.webservice.responsebean.WeatherSummery;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;

import static com.sree.weatherapp.common.AppConstants.IntentConstants.WEATHER_INFO;
import static com.sree.weatherapp.common.AppConstants.IntentConstants.WEATHER_SUMMERY;

public class HomeActivity extends AppCompatActivity implements OnListFragmentInteractionListener{
       // implements NavigationView.OnNavigationItemSelectedListener, OnListFragmentInteractionListener {


    public static final int RESULT_CODE = 0x1;
    private HomePagerAdapter mCustomPagerAdapter;
    @BindView(R.id.pager)
    protected ViewPager mViewPager;
    @BindView(R.id.progress_layout)
    protected FrameLayout progressView;
    @Inject
    Retrofit retrofit;
    @Inject
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_home);
        ButterKnife.bind(this);
        ((WeatherApplication) getApplication()).getWebServiceComponent().inject(this);
        updateWeatherData();
    }

    @Override
    public void onListFragmentInteraction(WeatherInfo weatherInfo) {
        WeatherDetailFragment weatherDetailFragment = new WeatherDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(WEATHER_INFO, weatherInfo);
        weatherDetailFragment.setArguments(args);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, weatherDetailFragment, "");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * Request model class for weather data update.
     */
    private void updateWeatherData() {
        progressView.setVisibility(View.VISIBLE);
        WeatherDataProcessor weatherDataProcessor = WeatherDataProcessor.getInstance();
        weatherDataProcessor.setDataProvider(new WeatherDataProcessor.IDataProvider() {
            @Override
            public void onDataReceived(WeatherSummery weatherSummery) {
                mCustomPagerAdapter = new HomePagerAdapter(getSupportFragmentManager(), createFragments(weatherSummery));
                mViewPager.setAdapter(mCustomPagerAdapter);
                progressView.setVisibility(View.GONE);
            }

            @Override
            public void onDataError() {

                progressView.setVisibility(View.GONE);
            }
        },retrofit);
        weatherDataProcessor.requestForWeatherUpdate(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    /**
     * Create fragments for pager adapter.
     *
     * @param weatherSummery
     * @return
     */
    public List<BaseFragment> createFragments(WeatherSummery weatherSummery) {
        List<BaseFragment> fragments = new ArrayList<>();
        BaseFragment weatherInfoFragment;
        Bundle args;

        weatherInfoFragment = new CurrentWeatherInfoFragment();
        args = new Bundle();
        args.putSerializable(WEATHER_INFO, weatherSummery.getCurrentWeatherInfo());
        fragments.add(weatherInfoFragment);
        weatherInfoFragment.setArguments(args);

        weatherInfoFragment = new DailyWeatherInfoFragment();
        args = new Bundle();
        args.putSerializable(WEATHER_SUMMERY, weatherSummery.getDailyWeatherInfo());
        fragments.add(weatherInfoFragment);
        weatherInfoFragment.setArguments(args);

        weatherInfoFragment = new HourlyWeatherInfoFragment();
        args = new Bundle();
        args.putSerializable(WEATHER_SUMMERY, weatherSummery.getHourlyWeatherInfo());
        fragments.add(weatherInfoFragment);
        weatherInfoFragment.setArguments(args);

        return fragments;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RESULT_CODE:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        // All required changes were successfully made
                        updateWeatherData();
                        break;
                    case Activity.RESULT_CANCELED:
                        // The user was asked to change settings, but chose not to
                        finish();
                        break;
                    default:
                        break;
                }
                break;
        }
    }
}


   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        updateWeatherData();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (drawer != null) {
            drawer.addDrawerListener(toggle);
        }
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
    }



    *//**
     * Request model class for weather data update.
     *//*
    private void updateWeatherData() {
        WeatherDataProcessor weatherDataProcessor = WeatherDataProcessor.getInstance();
        weatherDataProcessor.setDataProvider(new WeatherDataProcessor.IDataProvider() {
            @Override
            public void onDataReceived(WeatherSummery weatherSummery) {

            }

            @Override
            public void onDataError() {


            }
        });
        weatherDataProcessor.requestForWeatherUpdate(this);
    }

















    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }
}*/
