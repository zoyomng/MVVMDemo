package com.zoyo.core.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;

public class BaseViewModel extends AndroidViewModel implements IBaseViewModel{
    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void onAny(LifecycleOwner owner, Lifecycle.Event event) {
        System.out.println("=========onAny===========");
    }

    @Override
    public void onCreate() {
        System.out.println("=========onCreate===========");
    }
}
