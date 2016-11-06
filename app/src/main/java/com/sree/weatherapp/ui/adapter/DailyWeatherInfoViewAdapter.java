package com.sree.weatherapp.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.sree.weatherapp.R;
import com.sree.weatherapp.common.AppConstants;
import com.sree.weatherapp.ui.common.OnListFragmentInteractionListener;
import com.sree.weatherapp.utils.Utils;
import com.sree.weatherapp.webservice.responsebean.WeatherInfo;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Adapter class for binding daily weather information with view.
 * <p/>
 * {@link RecyclerView.Adapter} that can display a {@link WeatherInfo} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class DailyWeatherInfoViewAdapter extends RecyclerView.Adapter<DailyWeatherInfoViewAdapter.ViewHolder> {

    private final List<WeatherInfo> mValues;
    private final OnListFragmentInteractionListener mListener;
    private Context mContext;

    public DailyWeatherInfoViewAdapter(List<WeatherInfo> items, OnListFragmentInteractionListener listener, Context mContext) {
        mValues = items;
        mListener = listener;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mWeatherInfo.setText(mValues.get(position).getWeatherStatus());
        Drawable weatherIconDrawable = mContext.getResources().getDrawable(Utils.getWeatherIcons(mValues.get(position).getWeatherStatus()), null);
        holder.weatherIcon.setBackground(weatherIconDrawable);
        String time = Utils.getTimeFromSeconds(mValues.get(position).getWeatherTime(), AppConstants.DateTimeConstants.DATE_TIME_PATTERN_DD_MM);
        holder.weatherUpdateTime.setText(time);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.img_weather_status)
        public ImageView weatherIcon;
        @InjectView(R.id.txt_weatherInfo)
        public TextView mWeatherInfo;
        @InjectView(R.id.txt_time)
        public TextView weatherUpdateTime;
        public WeatherInfo mItem;
        public View mView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
            mView = view;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + weatherUpdateTime.getText() + "'";
        }
    }
}
