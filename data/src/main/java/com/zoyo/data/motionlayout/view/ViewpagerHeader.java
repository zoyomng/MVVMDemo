package com.zoyo.data.motionlayout.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.viewpager.widget.ViewPager;

/**
 * @Description: java类作用描述
 * @Author: zoyomng
 * @CreateDate: 2019/8/19 14:32
 */
public class ViewpagerHeader extends MotionLayout implements ViewPager.OnPageChangeListener {
    public ViewpagerHeader(Context context) {
        this(context, null);
    }

    public ViewpagerHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewpagerHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int pageNum = 3;
        setProgress((position + positionOffset) / (pageNum - 1));
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
