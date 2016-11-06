package com.sree.weatherapp.ui.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.sree.weatherapp.R;
import com.sree.weatherapp.app.AppDependencies;
import com.sree.weatherapp.app.DaggerScope;
import com.sree.weatherapp.app.DaggerService;
import com.sree.weatherapp.app.WeatherApplication;
import com.sree.weatherapp.flow.GsonParceler;
import com.sree.weatherapp.flow.HandlesBack;
import com.sree.weatherapp.ui.screen.CurrentWeatherInfoScreen;

import butterknife.ButterKnife;
import butterknife.InjectView;
import flow.Flow;
import flow.FlowDelegate;
import flow.History;
import flow.path.Path;
import flow.path.PathContainerView;
import mortar.MortarScope;
import mortar.bundler.BundleServiceRunner;

public class HomeActivity extends Activity implements Flow.Dispatcher {

    MortarScope mortarScope;
    FlowDelegate flowDelegate;
    @InjectView(R.id.container)
    PathContainerView pathContainerView;

    @Override
    public Object getSystemService(String name) {

        if (getApplication() == null) {
            return super.getSystemService(name);
        }
        Object service = null;

        if (flowDelegate != null) {
            service = flowDelegate.getSystemService(name);
        }

        if (service == null && mortarScope != null && mortarScope.hasService(name)) {
            service = mortarScope.getService(name);
        }
        return service != null ? service : super.getSystemService(name);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mortarScope = MortarScope.findChild(getApplicationContext(), getClass().getName());

        if (mortarScope == null) {
            Component component = DaggerHomeActivity_Component.builder()
                    .component(DaggerService.<WeatherApplication.Component>getDaggerComponent(getApplicationContext()))
                    .build();

            mortarScope = mortarScope.buildChild(getApplicationContext()).withService(BundleServiceRunner.SERVICE_NAME, new BundleServiceRunner())
                    .withService(DaggerService.SERVICE_NAME, component).build(getClass().getName());

        }

        DaggerService.<Component>getDaggerComponent(this).inject(this);

        BundleServiceRunner.getBundleServiceRunner(this).onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
          ButterKnife.inject(this);

        GsonParceler parceler = new GsonParceler(new Gson());

        @SuppressWarnings("deprecation") FlowDelegate.NonConfigurationInstance nonConfig =
                (FlowDelegate.NonConfigurationInstance) getLastNonConfigurationInstance();
        flowDelegate = FlowDelegate.onCreate(nonConfig, getIntent(), savedInstanceState, parceler, History.single(new CurrentWeatherInfoScreen()), this);

    }



    @Override
    protected void onResume() {
        super.onResume();
        flowDelegate.onResume();
    }

    @Override
    protected void onPause() {
        flowDelegate.onPause();
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        BundleServiceRunner.getBundleServiceRunner(this).onSaveInstanceState(outState);
        flowDelegate.onSaveInstanceState(outState);
    }

    @SuppressWarnings("deprecation") // https://code.google.com/p/android/issues/detail?id=151346
    @Override
    public Object onRetainNonConfigurationInstance() {
        return flowDelegate.onRetainNonConfigurationInstance();
    }

    @Override
    protected void onDestroy() {
        if (isFinishing()) {
            MortarScope activityScope = MortarScope.findChild(getApplicationContext(), getClass().getName());
            if (activityScope != null) {
                activityScope.destroy();
            }
        }

        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (((HandlesBack) pathContainerView).onBackPressed()) return;
        if (flowDelegate.onBackPressed()) return;

        super.onBackPressed();
    }

    // Flow.Dispatcher

    @Override
    public void dispatch(Flow.Traversal traversal, final Flow.TraversalCallback callback) {
        Path path = traversal.destination.top();
        setTitle(path.getClass().getSimpleName());
        ActionBar actionBar = getActionBar();
        boolean canGoBack = traversal.destination.size() > 1;
        actionBar.setDisplayHomeAsUpEnabled(canGoBack);
        actionBar.setHomeButtonEnabled(canGoBack);

        pathContainerView.dispatch(traversal, new Flow.TraversalCallback() {
            @Override
            public void onTraversalCompleted() {
                invalidateOptionsMenu();
                callback.onTraversalCompleted();
            }
        });
    }



    @dagger.Component(dependencies = WeatherApplication.Component.class)
    @DaggerScope(Component.class)
    public interface Component extends AppDependencies {
        void inject(HomeActivity activity);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

}








 /*       extends AppCompatActivity implements OnListFragmentInteractionListener{
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
    //    ((WeatherApplication) getApplication()).getWebServiceComponent().inject(this);
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

    */
/**
     * Request model class for weather data update.
     *//*

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

    */
/**
     * Create fragments for pager adapter.
     *
     * @param weatherSummery
     * @return
     *//*

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

*/

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
