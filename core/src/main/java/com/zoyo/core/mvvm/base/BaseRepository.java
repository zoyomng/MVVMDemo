package com.zoyo.core.mvvm.base;

import com.zoyo.net.RetrofitConfigs;
import com.zoyo.net.RetrofitManager;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 可以理解为: MVVM-Model中的获取数据的仓库
 */
public class BaseRepository implements IModel {

    private CompositeDisposable compositeDisposable;
    protected RetrofitManager mRetrofitManager = RetrofitManager.getInstance().loadConfigs(RetrofitConfigs.getInstance()).build();

    public BaseRepository() {

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
