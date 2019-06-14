package com.zoyo.mvvmdemo.app;

import com.zoyo.common.application.BaseApplication;
import com.zoyo.mvvmdemo.BuildConfig;
import com.zoyo.mvvmdemo.model.API;
import com.zoyo.net.RetrofitConfigs;

public class MyApplication extends BaseApplication {

    MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        InitializeService.start(this);

        RetrofitConfigs.getInstance()
                .connectTimeout(10)
                .writeTimeout(20)
                .readTimeout(20)
                .showLog(BuildConfig.DEBUG)
                .baseUrl(API.BASE_URL)
                .token("");
    }
}
