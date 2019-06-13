package com.zoyo.mvvmdemo.app;

import android.app.Application;

public class MyApplication extends Application {

    MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        InitializeService.start(this);
    }
}
