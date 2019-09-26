package com.zoyo.mvvmdemo.app;

import com.zoyo.core.BaseApplication;
import com.zoyo.core.common.net.RetrofitManager;
import com.zoyo.mvvmdemo.BuildConfig;
import com.zoyo.mvvmdemo.model.API;

public class MyApplication extends BaseApplication {

    MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        InitializeService.start(this);

        RetrofitManager.Configs.getInstance()
                .baseUrl(API.BASE_URL)
                .connectTimeout(20)
                .readTimeout(20)
                .writeTimeout(20)
                .showLog(BuildConfig.DEBUG)
                .token("")
                .apply();
    }
}
