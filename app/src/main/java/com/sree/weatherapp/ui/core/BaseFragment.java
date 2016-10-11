package com.sree.weatherapp.ui.core;


import android.support.v4.app.Fragment;

/**
 * Base class for all fragment. Things that is common for all fragment should be done here.
 * <p/>
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {


    public BaseFragment() {
    }

    public abstract String getFragmentName();

}
