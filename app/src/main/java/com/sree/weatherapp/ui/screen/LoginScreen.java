package com.sree.weatherapp.ui.screen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.sree.weatherapp.R;
import com.sree.weatherapp.app.AppDependencies;
import com.sree.weatherapp.app.DaggerScope;
import com.sree.weatherapp.mortar.ScreenComponentFactory;
import com.sree.weatherapp.ui.activity.HomeActivity;
import com.sree.weatherapp.ui.view.LoginView;
import com.sree.weatherapp.webservice.ApiClient;

import javax.inject.Inject;

import butterknife.OnClick;
import butterknife.OnItemClick;
import flow.path.Path;
import mortar.ViewPresenter;

/**
 * Created by Sreejith on 10-11-2016.
 */

public class LoginScreen extends Path implements ScreenComponentFactory<HomeActivity.Component>, GoogleApiClient.OnConnectionFailedListener {


    @Override
    public Object createComponent(HomeActivity.Component parent) {
        return DaggerCurrentWeatherInfoScreen_Component.builder()
                .component(parent)
                .build();
    }

    @dagger.Component(dependencies = HomeActivity.Component.class)
    @DaggerScope(LoginScreen.Component.class)
    public interface Component extends AppDependencies {
        void inject(LoginView view);
    }

    private GoogleApiClient mGoogleApiClient;

    @DaggerScope(LoginScreen.Component.class)
    public static class Presenter extends ViewPresenter<LoginView> {

        private GoogleApiClient mGoogleApiClient;

        @Inject
        public Presenter(GoogleApiClient googleApiClient) {
            mGoogleApiClient=googleApiClient;
        }

        @SuppressLint("SetTextI18n")
        private void populateView() {

        }


        @Override
        protected void onLoad(Bundle savedInstanceState) {
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();

            mGoogleApiClient = new GoogleApiClient.Builder(getView().getContext())
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();

        }


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
    }

    @OnClick(R.id.btn_sign_in)
    public void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }
}
