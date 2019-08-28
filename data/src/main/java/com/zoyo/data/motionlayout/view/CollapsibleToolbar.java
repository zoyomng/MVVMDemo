package com.zoyo.data.motionlayout.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.constraintlayout.motion.widget.MotionLayout;

import com.google.android.material.appbar.AppBarLayout;

/**
 * @Description: 可折叠的Toobar
 * @Author: zoyomng
 * @CreateDate: 2019/8/13 15:03
 */
public class CollapsibleToolbar extends MotionLayout implements AppBarLayout.OnOffsetChangedListener {


    public CollapsibleToolbar(Context context) {
        this(context, null);
    }

    public CollapsibleToolbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CollapsibleToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        float process = -verticalOffset / (float) appBarLayout.getTotalScrollRange();
        setProgress(process);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getParent() instanceof AppBarLayout) {
            ((AppBarLayout) getParent()).addOnOffsetChangedListener(this);
        }
    }
}
