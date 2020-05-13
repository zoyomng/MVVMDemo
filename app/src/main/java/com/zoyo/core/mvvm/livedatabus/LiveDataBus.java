package com.zoyo.core.mvvm.livedatabus;


import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: LiveDataBus类似于Rxbus(事件总线, 进程间通信), 基于LiveData, Lifecycle...
 * 注册订阅:
 * LiveDataBus.get()
 * .with("key_test",String.class)
 * .observe(this, new Observer<String>() {
 * @Override public void onChanged(String s) {
 * }
 * });
 * 发送消息: LiveDataBus.get().with("key_test").setValue("s");
 * 发送消息: LiveDataBus.get().with("key_test").postValue("s");
 * @Author: https://tech.meituan.com/2018/07/26/android-livedatabus.html
 * @CreateDate: 2019/9/17 11:04
 */
public class LiveDataBus {

    private static HashMap<String, BusMutableLiveData> bus;

    private LiveDataBus() {
        bus = new HashMap<>();
    }

    private static class SingletonHolder {
        private static LiveDataBus DEFAULT_BUS = new LiveDataBus();
    }

    public static LiveDataBus get() {
        return SingletonHolder.DEFAULT_BUS;
    }

    /**
     * 用作发送使用
     *
     * @param key
     * @return
     */
    public MutableLiveData<Object> with(String key) {
        return with(key, Object.class);
    }

    /**
     * 用作订阅使用
     *
     * @param key
     * @param type
     * @param <T>
     * @return
     */
    public <T> MutableLiveData<T> with(String key, Class<T> type) {
        if (!bus.containsKey(key)) {
            bus.put(key, new BusMutableLiveData<>());
        }
        return (MutableLiveData<T>) bus.get(key);
    }

    private static class ObserverWrapper<T> implements Observer<T> {

        private Observer<T> observer;

        public ObserverWrapper(Observer<T> observer) {
            this.observer = observer;
        }

        @Override
        public void onChanged(T t) {
            if (observer != null && !isCallOnObserve()) {
                observer.onChanged(t);
            }
        }

        private boolean isCallOnObserve() {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace != null && stackTrace.length > 0) {
                for (StackTraceElement element : stackTrace) {
                    if ("android.arch.lifecycle.LiveData".equals(element.getClassName()) && "observerForever".equals(element.getMethodName())) {
                        return true;
                    }
                }
            }
            return false;
        }
    }


    private class BusMutableLiveData<T> extends MutableLiveData<T> {
        private Map<Observer, Observer> observerMap = new HashMap<>();

        @Override
        public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
            super.observe(owner, observer);
            try {
                hook(observer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        @Override
        public void observeForever(@NonNull Observer<? super T> observer) {
            if (!observerMap.containsKey(observer)) {
                observerMap.put(observer, new ObserverWrapper(observer));
            }

            super.observeForever(observerMap.get(observer));
        }

        @Override
        public void removeObserver(@NonNull Observer<? super T> observer) {
            Observer realObserver = null;
            if (observerMap.containsKey(observer)) {
                realObserver = observerMap.remove(observer);
            } else {
                realObserver = observer;
            }
            super.removeObserver(realObserver);
        }


        private void hook(Observer<? super T> observer) throws Exception {
            Class<LiveData> liveDataClass = LiveData.class;
            Field declaredField = liveDataClass.getDeclaredField("mObservers");
            declaredField.setAccessible(true);
            Object objectObservers = declaredField.get(this);
            Class<?> observersClass = objectObservers.getClass();
            Method declaredMethod = observersClass.getDeclaredMethod("get", Object.class);
            declaredMethod.setAccessible(true);
            Object objectWrapperEntry = declaredMethod.invoke(objectObservers, observer);
            Object objectWrapper = null;
            if (objectWrapperEntry instanceof Map.Entry) {
                objectWrapper = ((Map.Entry) objectWrapperEntry).getValue();
            }
            if (objectWrapper == null) {
                throw new NullPointerException("Wrapper can not be null!");
            }
            Class<?> classObserverWrapper = objectWrapper.getClass().getSuperclass();
            Field mLastVersionField = classObserverWrapper.getDeclaredField("mLastVersion");
            mLastVersionField.setAccessible(true);
            Object objectVersion = mLastVersionField.get(this);
            mLastVersionField.set(objectWrapper, objectVersion);
        }
    }
}
