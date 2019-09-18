package com.zoyo.core.common.net.download;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @Description: java类作用描述
 * @Author: zoyomng
 * @CreateDate: 2019/7/2 17:14
 */
public class ProgressSubscriber<T> implements Observer<T>, DownloadProgressListener {


    /**
     * 订阅开始显示ProgressBar
     *
     * @param d
     */
    @Override
    public void onSubscribe(Disposable d) {
        //订阅即开始
        onStart();
    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {
        onFailed(e.getMessage());
    }

    @Override
    public void onComplete() {
        onFinish();
    }

    /**
     * 开始下载
     */
    @Override
    public void onStart() {

    }


    /**
     * 下载进程
     *
     * @param progress
     */
    @Override
    public void onProgress(int progress) {
    }

    /**
     * 下载完成
     */
    @Override
    public void onFinish() {

    }

    /**
     * 下载失败
     *
     * @param message
     */
    @Override
    public void onFailed(String message) {

    }

}
