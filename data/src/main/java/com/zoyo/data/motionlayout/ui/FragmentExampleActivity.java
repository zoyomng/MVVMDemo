package com.zoyo.data.motionlayout.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.fragment.app.Fragment;

import com.zoyo.data.R;

/**
 * @Description: java类作用描述
 * @Author: zoyomng
 * @CreateDate: 2019/8/21 15:55
 */
public class FragmentExampleActivity extends AppCompatActivity implements MotionLayout.TransitionListener {

    private Fragment fragment;
    private float lastProcess;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_example);
        if (savedInstanceState == null) {
            fragment = MainFragment.getInstance();
            replaceFragment();
        }
        MotionLayout motionLayout = (MotionLayout) findViewById(R.id.motionLayout);
        motionLayout.setTransitionListener(this);
    }

    @Override
    public void onTransitionStarted(MotionLayout motionLayout, int i, int i1) {

    }

    /**
     * @param motionLayout
     * @param mBeginState
     * @param mEndState
     * @param mTransitionPosition (0~1) 开始位置为0,结束为止为1;往开始位置方向滑动趋于0,反之趋于1
     */
    @Override
    public void onTransitionChange(MotionLayout motionLayout, int mBeginState, int mEndState, float mTransitionPosition) {
        if (mTransitionPosition - lastProcess > 0) {
            //往结束位置滑动
            boolean atEnd = Math.abs(mTransitionPosition - 1f) < 0.1f;
            if (atEnd && fragment instanceof MainFragment) {
                fragment = SecondFragment.getInstance();
                replaceFragment();
            }
        } else {
            boolean atStart = mTransitionPosition < 0.9f;
            if (atStart && fragment instanceof SecondFragment) {
                fragment = MainFragment.getInstance();
                replaceFragment();
            }
        }
        lastProcess = mTransitionPosition;
    }

    private void replaceFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow();
    }

    @Override
    public void onTransitionCompleted(MotionLayout motionLayout, int i) {

    }

    @Override
    public void onTransitionTrigger(MotionLayout motionLayout, int i, boolean b, float v) {

    }


}
