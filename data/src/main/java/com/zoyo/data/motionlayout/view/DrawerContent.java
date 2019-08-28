package com.zoyo.data.motionlayout.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.drawerlayout.widget.DrawerLayout;

/**
 * @Description: java类作用描述
 * @Author: zoyomng
 * @CreateDate: 2019/8/16 8:54
 */
public class DrawerContent extends MotionLayout implements DrawerLayout.DrawerListener {

    public DrawerContent(Context context) {
        this(context, null);
    }

    public DrawerContent(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawerContent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
        setProgress(slideOffset);
    }

    @Override
    public void onDrawerOpened(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerClosed(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (parent instanceof DrawerLayout) {
            ((DrawerLayout) parent).addDrawerListener(this);
        }
    }
}
