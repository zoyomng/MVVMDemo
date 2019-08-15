package com.zoyo.data.constraint_layout;

import android.content.Intent;
import android.os.Bundle;

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

    private MotionLayout motionLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int layoutResId = intent.getIntExtra("layoutResId", R.layout.activity_motion_01);
        setContentView(layoutResId);
        motionLayout = (MotionLayout) findViewById(R.id.motionLayout);
        motionLayout.setDebugMode(MotionLayout.DEBUG_SHOW_PATH);
    }
}
