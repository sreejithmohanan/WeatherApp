package com.sree.weatherapp.auth;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.sree.weatherapp.app.DaggerScope;
import com.sree.weatherapp.app.WeatherApplication;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Sreejith on 10-11-2016.
 */
@DaggerScope(WeatherApplication.Component.class)
public class AuthClient {

    @Singleton
    @Inject
    public AuthClient() {
      /*  GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();*/
    }
}
