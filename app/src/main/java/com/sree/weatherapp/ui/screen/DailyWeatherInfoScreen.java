package com.sree.weatherapp.ui.screen;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.sree.weatherapp.R;
import com.sree.weatherapp.app.AppDependencies;
import com.sree.weatherapp.app.DaggerScope;
import com.sree.weatherapp.flow.Layout;
import com.sree.weatherapp.mortar.ScreenComponentFactory;
import com.sree.weatherapp.ui.activity.HomeActivity;
import com.sree.weatherapp.ui.adapter.DailyWeatherInfoViewAdapter;
import com.sree.weatherapp.ui.common.OnListFragmentInteractionListener;
import com.sree.weatherapp.ui.view.DailyWeatherInfoView;
import com.sree.weatherapp.ui.view.HourlyWeatherInfoView;
import com.sree.weatherapp.webservice.ApiClient;
import com.sree.weatherapp.webservice.responsebean.WeatherInfo;
import com.sree.weatherapp.webservice.responsebean.WeatherSummery;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import flow.Flow;
import flow.path.Path;
import mortar.ViewPresenter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

/**
 * Created by Sreejith on 07-11-2016.
 */


@Layout(R.layout.screen_daily_weatherinfo)
public class DailyWeatherInfoScreen extends Path implements ScreenComponentFactory<HomeActivity.Component> {

    @Override
    public Object createComponent(HomeActivity.Component parent) {
        return DaggerHourlyWeatherInfoScreen_Component.builder()
                .component(parent)
                .build();
    }

    @dagger.Component(dependencies = HomeActivity.Component.class)
    @DaggerScope(Component.class)
    public interface Component extends AppDependencies {
        void inject(DailyWeatherInfoView view);
    }

    @DaggerScope(Component.class)
    public static class Presenter extends ViewPresenter<DailyWeatherInfoView> implements OnListFragmentInteractionListener {

        private final ApiClient restClient;

        private DailyWeatherInfoViewAdapter adapter;
        private List<WeatherInfo> weatherInfoList = new ArrayList<>();

        @Inject
        public Presenter(ApiClient restClient) {
            this.restClient = restClient;
        }

        @Override
        protected void onLoad(Bundle savedInstanceState) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getView().getContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            getView().recyclerView.setLayoutManager(layoutManager);

            adapter = new DailyWeatherInfoViewAdapter(weatherInfoList, this,getView().getContext());
            getView().recyclerView.setAdapter(adapter);

            if (weatherInfoList.isEmpty()) {
                load();
            }
        }

        private void load() {

            restClient.getService().getPosts(new Callback<List<WeatherSummery>>() {
                @Override
                public void success(List<WeatherSummery> weatherSummeryList, Response response) {
                    if (!hasView()) return;
                    Timber.d("Success loaded %s", weatherSummeryList.size());

                    weatherInfoList.clear();
                    weatherInfoList.addAll(weatherSummeryList.get(0).getHourlyWeatherInfo().getHourlyWeatherInfoList());
                    adapter.notifyDataSetChanged();

                    getView().show();
                }

                @Override
                public void failure(RetrofitError error) {
                    if (!hasView()) return;
                    Timber.d("Failure %s", error.getMessage());
                }
            });
        }

        @Override
        public void onListFragmentInteraction(WeatherInfo item) {
            if (!hasView()) return;

            Flow.get(getView()).set(new WeatherDetailScreen());

        }
    }

}