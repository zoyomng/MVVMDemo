package com.zoyo.mvvmdemo.app;

import android.app.Application;
import android.content.Context;
import android.view.Gravity;

import com.zoyo.core.common.net.RetrofitManager;
import com.zoyo.core.common.widget.toast.Toasty;
import com.zoyo.mvvmdemo.BuildConfig;
import com.zoyo.mvvmdemo.model.API;


public class MyApplication extends Application {

    private static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
        initToasty();

        InitializeService.start(this);

        initRetrofitManagerConfig();
    }

    public static Context getAppContext() {
        return applicationContext;
    }

    private void initRetrofitManagerConfig() {
        RetrofitManager.Configs.getInstance()
                .baseUrl(API.BASE_URL)
                .connectTimeout(20)
                .readTimeout(20)
                .writeTimeout(20)
                .showLog(BuildConfig.DEBUG)
                .token("")
                .apply();
    }

    /**
     * 初始化Toast配置
     */
    private void initToasty() {
        Toasty.Config.getInstance()
                .isTintIcon(true)
                .setTextSize(16)
                .setGravity(Gravity.CENTER, 0, 0)
                .apply();
    }
}
