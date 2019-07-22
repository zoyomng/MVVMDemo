package com.zoyo.data;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Description: 底部导航栏
 * @Author: zoyomng
 * @CreateDate: 2019/7/19 15:02
 */
public class BottomNavigationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
    }
}
