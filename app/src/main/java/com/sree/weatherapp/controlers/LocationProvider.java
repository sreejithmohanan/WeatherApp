package com.sree.weatherapp.controlers;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

/**
 * Location manager class will register app with google api client. And give call back with current location to model class
 */
public class LocationProvider implements LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "LocationProvider";
    private InLocationUpdateListener inLocationUpdateListener;
    private Context mContext;
    private GoogleApiClient mGoogleApiClient;

    public LocationProvider(InLocationUpdateListener inLocationUpdateListener, Context context) {
        this.inLocationUpdateListener = inLocationUpdateListener;
        this.mContext = context;
        this.mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }


    public void registerGoogleApiClient() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    public void uaRegisterLocationUpdate() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
            mGoogleApiClient.unregisterConnectionCallbacks(this);
            mGoogleApiClient.unregisterConnectionFailedListener(this);
            mGoogleApiClient = null;
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        String currentLocation;
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            currentLocation = String.valueOf(mLastLocation.getLatitude()) + "," + String.valueOf(mLastLocation.getLongitude());
            inLocationUpdateListener.onLocationUpdate(currentLocation);
        } else {
            LocationRequest mLocationRequest = new LocationRequest();
            mLocationRequest.setInterval(10000);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_LOW_POWER);
            checkAndRegisterForLocationUpdate(mLocationRequest);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.e(TAG, "Location Update Suspended");
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            String currentLocation = String.valueOf(location.getLatitude()) + "," + String.valueOf(location.getLongitude());
            inLocationUpdateListener.onLocationUpdate(currentLocation);
            uaRegisterLocationUpdate();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        inLocationUpdateListener.onError(connectionResult.getErrorCode());
    }

    public interface InLocationUpdateListener {
        void onLocationUpdate(String latLong);

        void onError(int responseCode);
    }

    /**
     * Function check the location is enable or not
     * if enable fetch last known location from the device .
     * if the last known location is empty will try for new location fetch
     *
     * @param mLocationRequest
     */
    private void checkAndRegisterForLocationUpdate(final LocationRequest mLocationRequest) {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(mLocationRequest);
        builder.setAlwaysShow(true);
        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
                final Status status = locationSettingsResult.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can initialize location
                        // requests here.
                        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, LocationProvider.this);
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be fixed by showing the user
                        // a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult((Activity) mContext, 0x1);

                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way to fix the
                        // settings so we won't show the dialog.
                        inLocationUpdateListener.onError(LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE);
                        uaRegisterLocationUpdate();
                        break;
                }
            }

        });
    }

}
