package com.zoyo.core.mvvm.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.zoyo.core.mvvm.utils.TypeUtil;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @param <M>
 */
public class BaseViewModel<M extends BaseRepository> extends AndroidViewModel implements IBaseViewModel, Consumer<Disposable> {
    //弱引用持有
    private WeakReference<LifecycleProvider> lifecycle;
    private CompositeDisposable compositeDisposable;
    protected final M repository;

    //状态值
    public MutableLiveData<Integer> statusValue = new MutableLiveData<>();

    public BaseViewModel(@NonNull Application application) {
        super(application);

        //获取泛型中Model对象实例
        repository = TypeUtil.getClassInstance(this, 0);
    }


    void injectLifecycleProvider(LifecycleProvider lifecycle) {
        this.lifecycle = new WeakReference<>(lifecycle);
    }

    @Override
    public void accept(Disposable disposable) throws Exception {
        addSubscribe(disposable);
    }

    private void addSubscribe(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    /**
     * ViewModel在Activity因为一些配置参数改变而被销毁和重建,则ViewModel不会被销毁,新建的Activity实例仍然持有这个旧的ViewModel;
     * 如果被销毁没有被重建,将会调用onCleared(),It is useful when ViewModel observes some data and you need to clear this subscription to
     * prevent a leak of this ViewModel.
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        if (repository != null) {
            repository.onCleared();
        }
        //ViewModel销毁时取消所有订阅
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }

    }

    /**
     * 生命周期onCreate(),onStart(),onResume()是在对应Activity的生命周期方法调用后执行,
     * onPause(),onStop(),onDestroy()是在对应Activity的生命周期方法调用之前执行
     *
     * @param owner
     * @param event
     */
    @Override
    public void onAny(LifecycleOwner owner, Lifecycle.Event event) {
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }
}
