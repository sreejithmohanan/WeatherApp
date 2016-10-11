package com.sree.weatherapp.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sree.weatherapp.R;
import com.sree.weatherapp.ui.adapter.DailyWeatherInfoViewAdapter;
import com.sree.weatherapp.ui.common.OnListFragmentInteractionListener;
import com.sree.weatherapp.ui.core.BaseFragment;
import com.sree.weatherapp.webservice.responsebean.DailyWeatherInfo;


/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class DailyWeatherInfoFragment extends BaseFragment {
    private OnListFragmentInteractionListener mListener;
    private DailyWeatherInfo dailyWeatherInfo;

    public DailyWeatherInfoFragment() {
    }

    @Override
    public String getFragmentName() {
        return "Daily";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        dailyWeatherInfo = (DailyWeatherInfo) bundle.getSerializable("weatherSummery");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weatherinfo_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            recyclerView.setAdapter(new DailyWeatherInfoViewAdapter(dailyWeatherInfo.getDailyWeatherInfoList(), mListener,context));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
