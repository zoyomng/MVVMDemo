package com.zoyo.core.base;

import com.zoyo.net.RetrofitManager;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseModel implements IModel {

    private CompositeDisposable compositeDisposable;
    protected RetrofitManager mRetrofitManager = new RetrofitManager.Builder()
            .connectTimeout(10)
            .readTimeout(20)
            .writeTimeout(20)
            .build();

    public BaseModel() {

    }

    protected void addSubscribe(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    @Override
    public void onCleared() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }
}
