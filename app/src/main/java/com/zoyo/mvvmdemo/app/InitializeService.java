package com.zoyo.mvvmdemo.app;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

/**
 * 费时的初始化项可以放在IntentService中进行
 */
public class InitializeService extends IntentService {
    private static final String ACTION_INIT = "initApplication";

    MyApplication myApplication;

    public InitializeService() {
        super("InitializeService");
    }

    public static void start(MyApplication myApplication) {
        Intent intent = new Intent(myApplication, InitializeService.class);
        intent.setAction(ACTION_INIT);
        myApplication.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            if (ACTION_INIT.equals(intent.getAction())) {
                initApplication();
            }
        }
    }

    private void initApplication() {
        System.out.println("初始化服务");
    }
}
