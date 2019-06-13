package com.zoyo.mvvmdemo.model;

import com.zoyo.common.rx.RxSubscriber;
import com.zoyo.common.rx.RxUtil;
import com.zoyo.core.base.BaseModel;
import com.zoyo.net.Response;

public class MainModel extends BaseModel {
    private API api = mRetrofitManager.creat(API.class);

    public void request() {

        addSubscribe(api.request()
                .compose(RxUtil.<Response>rxSchedulerHelper())
                .subscribeWith(new RxSubscriber<Response>() {
                    @Override
                    protected void onNoNetwork() {

                    }

                    @Override
                    protected void onSuccess(Response response) {

                    }

                    @Override
                    protected void onFailure(String message) {

                    }
                }));
    }

}
