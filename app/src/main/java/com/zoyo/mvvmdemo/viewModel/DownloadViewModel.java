package com.zoyo.mvvmdemo.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.zoyo.core.mvvm.base.BaseViewModel;
import com.zoyo.core.mvvm.base.OnDataCallback;
import com.zoyo.mvvmdemo.model.repository.DownloadRepository;

/**
 * @Description: DownloadViewModel
 * @Author: zoyomng
 * @CreateDate: 2019/7/12 15:30
 */
public class DownloadViewModel extends BaseViewModel<DownloadRepository> {
    public MutableLiveData<Integer> process = new MutableLiveData<Integer>();

    public DownloadViewModel(@NonNull Application application) {
        super(application);
    }

    public void download() {
        repository.download(statusValue, new OnDataCallback<Integer>() {
            @Override
            public void onData(Integer integer) {
                //io线程
                process.postValue(integer);
            }
        });
    }
}
