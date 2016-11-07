package com.sree.weatherapp.ui.screen;

import android.os.Bundle;

import com.sree.weatherapp.R;
import com.sree.weatherapp.app.AppDependencies;
import com.sree.weatherapp.app.DaggerScope;
import com.sree.weatherapp.common.AppConstants;
import com.sree.weatherapp.flow.Layout;
import com.sree.weatherapp.mortar.ScreenComponentFactory;
import com.sree.weatherapp.ui.activity.HomeActivity;
import com.sree.weatherapp.ui.view.WeatherDetailView;
import com.sree.weatherapp.utils.Utils;
import com.sree.weatherapp.webservice.ApiClient;
import com.sree.weatherapp.webservice.responsebean.WeatherInfo;
import com.sree.weatherapp.webservice.responsebean.WeatherSummery;

import java.util.List;

import javax.inject.Inject;

import flow.path.Path;
import mortar.ViewPresenter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

/**
 * Created by Sreejith on 07-11-2016.
 */
@Layout(R.layout.screen_weather_detail)
public class WeatherDetailScreen extends Path implements ScreenComponentFactory<HomeActivity.Component> {

    @Override
    public Object createComponent(HomeActivity.Component parent) {
        return DaggerCurrentWeatherInfoScreen_Component.builder()
                .component(parent)
                .build();
    }

    @dagger.Component(dependencies = HomeActivity.Component.class)
    @DaggerScope(WeatherDetailScreen.Component.class)
    public interface Component extends AppDependencies {
        void inject(WeatherDetailView view);
    }

    @DaggerScope(WeatherDetailScreen.Component.class)
    public static class Presenter extends ViewPresenter<WeatherDetailView> {

        private final ApiClient restClient;


        @Inject
        public Presenter(ApiClient restClient) {
            this.restClient = restClient;
        }


        /**
         * Populate the weather information to view.
         */
        private void populateView(WeatherInfo weatherInfo) {
            if (weatherInfo != null) {
                String time = Utils.getTimeFromSeconds(weatherInfo.getWeatherTime(), null);
                getView().txtSunriseTime.setText(time);
                getView().txtPrecipitationIntensity.setText(String.valueOf(weatherInfo.getPrecipIntensity()));
                getView().txtPrecipitationProbablity.setText(String.valueOf(weatherInfo.getPrecipProbability()));
                getView().txtDewPoint.setText(weatherInfo.getDewPoint() + AppConstants.UnitsConstants.UNIT_TEMPRATURE);
                getView().txtRelativeHumidity.setText(String.valueOf(weatherInfo.getHumidity()));
                getView().txtPressure.setText(weatherInfo.getPressure() + AppConstants.UnitsConstants.UNIT_PRESSURE);
                getView().txtWindSpeed.setText(weatherInfo.getWindSpeed() + AppConstants.UnitsConstants.UNIT_MPH);
                getView().txtWindBearing.setText(String.valueOf(weatherInfo.getWindBearing()));
                getView().txtCloudCover.setText(String.valueOf(weatherInfo.getCloudCover()));
                getView().txtOzone.setText(weatherInfo.getOzone() + AppConstants.UnitsConstants.UNIT_OZON);
            }
        }

        @Override
        protected void onLoad(Bundle savedInstanceState) {
            load();
        }

        private void load() {

            restClient.getService().getPosts(new Callback<List<WeatherSummery>>() {
                @Override
                public void success(List<WeatherSummery> summeries, Response response) {
                    if (!hasView()) return;
                    Timber.d("Success loaded %s", summeries.size());
                    populateView(summeries.get(0).getCurrentWeatherInfo());
                    getView().show();
                    populateView(summeries.get(0).getCurrentWeatherInfo());
                }

                @Override
                public void failure(RetrofitError error) {
                    if (!hasView()) return;
                }
            });
        }
    } }
