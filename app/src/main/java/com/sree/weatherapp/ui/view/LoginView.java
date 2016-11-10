package com.sree.weatherapp.ui.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.sree.weatherapp.R;
import com.sree.weatherapp.app.DaggerService;
import com.sree.weatherapp.ui.screen.CurrentWeatherInfoScreen;
import com.sree.weatherapp.ui.screen.LoginScreen;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Sreejith on 10-11-2016.
 */

public class LoginView extends ConstraintLayout {

    @InjectView(R.id.btn_sign_in)
    public SignInButton btnSignIn;

    public GoogleApiClient mGoogleApiClient;
    public ProgressDialog mProgressDialog;
    @Inject
    protected LoginScreen.Presenter presenter;


    public LoginView(Context context, AttributeSet attrs) {
        super(context, attrs);
        DaggerService.<LoginScreen.Component>getDaggerComponent(context).inject(this);
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
