package com.zoyo.data;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Description: 使用一个Activity填充多种布局(非一次)
 * @Author: zoyomng
 * @CreateDate: 2019/7/18 15:35
 */
public class MultiLayoutActivity extends AppCompatActivity {
    private String mTag = "activity_multilayout";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mTag);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //配置项发生改变时,重新走onCreate()方法,所以根据需要改变
        setContentView(mTag);
    }

    /**
     * 点击事件-切换不同的布局
     *
     * @param view
     */
    public void show(View view) {
        mTag = (String) view.getTag();
        setContentView(mTag);
    }

    @Override
    public void onBackPressed() {
        if (!mTag.equals("activity_multilayout")) {
            mTag = "activity_multilayout";
            setContentView(mTag);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * @param mTag layout布局名称
     */
    private void setContentView(String mTag) {
        int layoutResID = getResources().getIdentifier(mTag, "layout", getPackageName());
        setContentView(layoutResID);
    }
}
