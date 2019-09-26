package com.zoyo.core.mvvm.base;

import com.zoyo.core.common.net.RetrofitManager;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.Retrofit;

/**
 * 可以理解为: MVVM-Model中的获取数据的仓库
 */
public class BaseRepository implements IModel {

    private CompositeDisposable compositeDisposable;

    protected Retrofit retrofit = RetrofitManager.build();

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
