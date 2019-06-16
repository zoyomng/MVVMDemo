package com.zoyo.mvvmdemo.viewModel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.zoyo.core.base.BaseViewModel;
import com.zoyo.core.base.OnDataCallback;
import com.zoyo.mvvmdemo.model.repository.MainRepository;
import com.zoyo.mvvmdemo.model.bean.MainResponse;
import com.zoyo.net.Response;

public class MainViewModel extends BaseViewModel<MainRepository> {


    public MainViewModel(@NonNull Application application) {
        super(application);
        model.request(new OnDataCallback<Response<MainResponse>>() {
            @Override
            public void onData(Response<MainResponse> mainResponseResponse) {
                System.out.println("===============" + mainResponseResponse.toString());
            }
        });
    }
}
