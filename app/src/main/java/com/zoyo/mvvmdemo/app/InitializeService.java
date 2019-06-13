package com.zoyo.mvvmdemo.app;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.zoyo.mvvmdemo.BuildConfig;
import com.zoyo.mvvmdemo.model.API;
import com.zoyo.net.RetrofitManager;

class InitializeService extends IntentService {
    private static final String ACTION_INIT = "initApplication";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    MyApplication myApplication;

    public InitializeService(String name) {
        super(name);
    }

    public static void start(MyApplication myApplication) {
        Intent intent = new Intent(myApplication, IntentService.class);
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
        RetrofitManager.Builder builder = new RetrofitManager.Builder();
        builder.application(myApplication)
                .connectTimeout(10)
                .writeTimeout(20)
                .readTimeout(20)
                .showLog(BuildConfig.DEBUG)
                .baseUrl(API.BASE_URL)
                .token("");

    }
}
