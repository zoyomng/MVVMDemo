package com.zoyo.mvvmdemo.viewModel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.zoyo.core.base.BaseViewModel;
import com.zoyo.mvvmdemo.app.Constants;
import com.zoyo.mvvmdemo.model.repository.DownloadRepository;
import com.zoyo.net.download.DownloadManager;
import com.zoyo.net.download.ProgressSubscriber;

import java.io.File;
import java.io.IOException;

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
        try {
            File tempFile = File.createTempFile("icmes-presentation", ".apk", com.zoyo.common.application.Constants.FILE_DOWNLOAD);
            DownloadManager.getInstance().downloadFile(Constants.APK_URL, tempFile.getAbsolutePath(), new ProgressSubscriber() {
                @Override
                public void onStart() {
                    super.onStart();
                    System.out.println("==============onStart===========");
                }

                @Override
                public void onFailed(String message) {
                    super.onFailed(message);
                    System.out.println("==============onFailed===========" + message);

                }

                @Override
                public void onProgress(int progress) {
                    super.onProgress(progress);
                    //io线程
                    process.postValue(progress);

                }

                @Override
                public void onFinish() {
                    super.onFinish();
                    System.out.println("==============onFinish===========");

                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
