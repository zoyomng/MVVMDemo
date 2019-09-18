package com.zoyo.mvvmdemo.app;

import com.zoyo.core.BaseApplication;
import com.zoyo.mvvmdemo.BuildConfig;
import com.zoyo.mvvmdemo.model.API;
import com.zoyo.core.common.net.RetrofitConfigs;

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
