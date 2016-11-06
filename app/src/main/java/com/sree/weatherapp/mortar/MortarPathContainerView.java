package com.sree.weatherapp.mortar;

import android.content.Context;
import android.util.AttributeSet;


import com.sree.weatherapp.R;
import com.sree.weatherapp.flow.FramePathContainerView;
import com.sree.weatherapp.flow.SimplePathContainer;

import flow.path.Path;

/**
 */
public class MortarPathContainerView extends FramePathContainerView {

    public MortarPathContainerView(Context context, AttributeSet attrs) {
        super(context, attrs, new SimplePathContainer(R.id.screen_switcher_tag, Path.contextFactory(new BasicMortarContextFactory(new ScreenScoper()))));
    }
}
