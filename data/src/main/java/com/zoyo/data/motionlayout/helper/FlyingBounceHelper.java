package com.zoyo.data.motionlayout.helper;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * @Description: java类作用描述
 * @Author: zoyomng
 * @CreateDate: 2019/8/21 10:29
 */
public class FlyingBounceHelper extends ConstraintHelper {
    protected ConstraintLayout mContainer;

    public FlyingBounceHelper(Context context) {
        super(context);
    }

    public FlyingBounceHelper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlyingBounceHelper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void updatePreLayout(ConstraintLayout container) {
        if (mContainer != container) {
            //获取的views.length == 0 , 应该为1
            View[] views = getViews(container);
            for (int i = 0; i < mCount; i++) {
                View view = views[i];
                ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", -2000, 0).setDuration(1000);
                animator.setInterpolator(new BounceInterpolator());
                animator.start();
            }
        }
        mContainer = container;
    }
}
