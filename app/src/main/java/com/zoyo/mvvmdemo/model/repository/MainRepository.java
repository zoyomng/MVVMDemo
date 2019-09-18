package com.zoyo.mvvmdemo.model.repository;

import com.zoyo.core.common.rx.RxSubscriber;
import com.zoyo.core.common.rx.RxUtil;
import com.zoyo.core.mvvm.base.BaseRepository;
import com.zoyo.core.mvvm.base.OnDataCallback;
import com.zoyo.mvvmdemo.model.API;
import com.zoyo.mvvmdemo.model.bean.MainResponse;
import com.zoyo.core.common.net.Response;

public class MainRepository extends BaseRepository {
    private API api = mRetrofitManager.creat(API.class);

    public void request(final OnDataCallback<Response<MainResponse>> onDataCallback) {

        addSubscribe(api.request()
                .compose(RxUtil.<Response>rxSchedulerHelper())
                .subscribeWith(new RxSubscriber<Response>() {
                    @Override
                    protected void onNoNetwork() {
                        System.out.println("====onNoNetwork===========");
                    }

                    @Override
                    protected void onSuccess(Response response) {
                        onDataCallback.onData(response);
                    }

                    @Override
                    protected void onFailure(String message) {
                        System.out.println("====onFailure===========" + message);

                    }
                }));
    }

}
