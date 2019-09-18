package com.zoyo.common.application;

import android.app.Application;
import android.content.Context;


public class BaseApplication extends Application {

    private static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
        initAutoSize();
    }

    public static Context getAppContext() {
        return applicationContext;
    }


    /**
     * 初始化屏幕适配
     */
    private void initAutoSize() {
        //当 App 中出现多进程, 并且您需要适配所有的进程, 就需要在 App 初始化时调用 initCompatMultiProcess()
        //在 Demo 中跳转的三方库中的 DefaultErrorActivity 就是在另外一个进程中, 所以要想适配这个 Activity 就需要调用 initCompatMultiProcess()
//        AutoSize.initCompatMultiProcess(this);
    }

}
