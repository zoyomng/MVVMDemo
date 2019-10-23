package com.zoyo.core;

import android.app.Application;
import android.content.Context;
import android.view.Gravity;

import com.zoyo.core.common.widget.toast.Toasty;


public class BaseApplication extends Application {

    private static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
        initAutoSize();
        initToasty();
    }


    public static Context getAppContext() {
        return applicationContext;
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

    /**
     * 初始化屏幕适配
     */
    private void initAutoSize() {
        //当 App 中出现多进程, 并且您需要适配所有的进程, 就需要在 App 初始化时调用 initCompatMultiProcess()
        //在 Demo 中跳转的三方库中的 DefaultErrorActivity 就是在另外一个进程中, 所以要想适配这个 Activity 就需要调用 initCompatMultiProcess()
//        AutoSize.initCompatMultiProcess(this);
    }

}
