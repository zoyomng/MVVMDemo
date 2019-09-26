package com.zoyo.mvvmdemo.model.repository;

import androidx.lifecycle.MutableLiveData;

import com.zoyo.core.common.constants.Constants;
import com.zoyo.core.common.rx.RxSubscriber;
import com.zoyo.core.common.rx.RxUtil;
import com.zoyo.core.mvvm.base.BaseRepository;
import com.zoyo.core.mvvm.base.OnDataCallback;
import com.zoyo.mvvmdemo.model.API;
import com.zoyo.mvvmdemo.model.bean.MainResponse;
import com.zoyo.core.common.net.Response;

public class MainRepository extends BaseRepository {
    private API api = retrofit.create(API.class);

    public void request(MutableLiveData<Integer> statusValue, OnDataCallback<Response<MainResponse>> onDataCallback) {
        statusValue.setValue(Constants.STAUTS_LOADING);

        addSubscribe(api.request()
                .compose(RxUtil.<Response>rxSchedulerHelper())
                .subscribeWith(new RxSubscriber<Response>() {
                    @Override
                    protected void onNoNetwork() {
                        statusValue.setValue(Constants.STATUS_NETWORK_ERROR);
                    }

                    @Override
                    protected void onSuccess(Response response) {
                        statusValue.setValue(Constants.STATUS_SUCCESS);
                        onDataCallback.onData(response);
                    }

                    @Override
                    protected void onFailure(String message) {
                        statusValue.setValue(Constants.STATUS_SERVER_ERROR);
                    }
                }));
    }

}
