package com.sree.weatherapp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.sree.weatherapp.ui.core.BaseFragment;

import java.util.List;

/**
 * Adapter class for loading viewpager fragment.
 */
public class HomePagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> weatherInfoFragments;

    public HomePagerAdapter(FragmentManager fm, List<BaseFragment> weatherInfoFragments) {
        super(fm);
        this.weatherInfoFragments = weatherInfoFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return weatherInfoFragments.get(position);
    }

    @Override
    public int getCount() {
        return weatherInfoFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return weatherInfoFragments.get(position).getFragmentName();
    }

}
