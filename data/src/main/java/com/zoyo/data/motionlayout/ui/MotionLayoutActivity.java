package com.zoyo.data.motionlayout.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import com.zoyo.data.R;

/**
 * @Description: MotionLayout布局
 * @Author: zoyomng
 * @CreateDate: 2019/8/12 15:38
 */
public class MotionLayoutActivity extends AppCompatActivity {

    private View motionLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int layoutResId = intent.getIntExtra("layoutResId", R.layout.activity_motion_01);
        setContentView(layoutResId);

        if (layoutResId == R.layout.activity_motion_12) {
            ImageView head = (ImageView) findViewById(R.id.head);
            head.setClipToOutline(true);
        }
        motionLayout = findViewById(R.id.motionLayout);
        if (motionLayout instanceof MotionLayout) {
            ((MotionLayout) motionLayout).setDebugMode(MotionLayout.DEBUG_SHOW_PATH);
        }
    }

    /**
     * 点击事件,开始运动轨迹
     *
     * @param v
     */
    public void changeState(View v) {
        if (!(this.motionLayout instanceof MotionLayout)) {
            return;
        }
        MotionLayout motionLayout = (MotionLayout) this.motionLayout;
        if (motionLayout.getProgress() > 0.5f) {
            motionLayout.transitionToStart();
        } else {
            motionLayout.transitionToEnd();
        }
    }
}
