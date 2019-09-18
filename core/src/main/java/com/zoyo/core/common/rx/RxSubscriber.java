package com.zoyo.core.common.rx;

import com.google.gson.JsonParseException;
import com.zoyo.core.common.utils.NetUtil;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.UnknownHostException;

import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.HttpException;

public abstract class RxSubscriber<T> extends DisposableSubscriber<T> {
    @Override
    protected void onStart() {
        super.onStart();
        if (!NetUtil.isNetworkConnected()) {
            onNoNetwork();
            cancel();
        }
    }

    @Override
    public void onNext(T t) {
        //统一处理返回结果
        onSuccess(t);
    }

    @Override
    public void onError(Throwable t) {
        String message = "";
        if (t instanceof UnknownHostException) {
            message = "没有网络";
        } else if (t instanceof ConnectException) {
            message = "连接失败";
        } else if (t instanceof HttpException) {
            message = "网络错误";
        } else if (t instanceof JsonParseException || t instanceof JSONException) {
            message = "解析错误";
        } else {
            message = t.getMessage();
        }
        onFailure(message);
    }


    @Override
    public void onComplete() {

    }

    protected abstract void onNoNetwork();

    protected abstract void onSuccess(T t);

    protected abstract void onFailure(String message);


}
