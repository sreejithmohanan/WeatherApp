package com.sree.weatherapp.ui.screen;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.sree.weatherapp.R;
import com.sree.weatherapp.app.AppDependencies;
import com.sree.weatherapp.app.DaggerScope;
import com.sree.weatherapp.flow.Layout;
import com.sree.weatherapp.mortar.ScreenComponentFactory;
import com.sree.weatherapp.ui.activity.HomeActivity;
import com.sree.weatherapp.ui.adapter.DailyWeatherInfoViewAdapter;
import com.sree.weatherapp.ui.view.CurrentWeatherView;
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
 * Created by Sreejith on 04-11-2016.
 */
@Layout(R.layout.screen_current_weather)
public class CurrentWeatherInfoScreen extends Path implements ScreenComponentFactory<HomeActivity.Component> {

    @Override
    public Object createComponent(HomeActivity.Component parent) {
        return DaggerCurrentWeatherInfoScreen_Component.builder()
                .component(parent)
                .build();
    }

    @dagger.Component(dependencies = HomeActivity.Component.class)
    @DaggerScope(Component.class)
    public interface Component extends AppDependencies {
        void inject(CurrentWeatherView view);
    }

    @DaggerScope(Component.class)
    public static class Presenter extends ViewPresenter<CurrentWeatherView>  {

        private final ApiClient restClient;

        private DailyWeatherInfoViewAdapter adapter;

        @Inject
        public Presenter(ApiClient restClient) {
            this.restClient = restClient;
        }

        @SuppressLint("SetTextI18n")
        private void populateView(WeatherInfo currentWeatherInfo) {
            if (currentWeatherInfo != null) {
                getView().txtCurrentTemperature.setText(String.valueOf(Math.round(currentWeatherInfo.getTemperature())) + "°");
                getView().txtCurrentWeatherInfo.setText(String.valueOf(currentWeatherInfo.getWeatherSummary()));
                getView().txtCurrentHumidity.setText(String.valueOf(currentWeatherInfo.getHumidity()));
                getView().txtCurrentDuePoint.setText(String.valueOf(currentWeatherInfo.getDewPoint()));
                getView().txtCurrentPressure.setText(String.valueOf(currentWeatherInfo.getPressure()));

            }
        }


        @Override
        protected void onLoad(Bundle savedInstanceState) {

        }

        private void load() {

            restClient.getService().getPosts(new Callback<List<WeatherSummery>>() {
                @Override
                public void success(List<WeatherSummery> summeries, Response response) {
                    if (!hasView()) return;
                    Timber.d("Success loaded %s", summeries.size());
                    populateView(summeries.get(0).getCurrentWeatherInfo());
                   getView().show();
                }

                @Override
                public void failure(RetrofitError error) {
                    if (!hasView()) return;
                }
            });
        }
    }
}