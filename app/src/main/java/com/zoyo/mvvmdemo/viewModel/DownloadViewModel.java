package com.zoyo.mvvmdemo.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.zoyo.core.mvvm.base.BaseViewModel;
import com.zoyo.mvvmdemo.app.Constants;
import com.zoyo.mvvmdemo.model.repository.DownloadRepository;
import com.zoyo.core.common.net.download.DownloadManager;
import com.zoyo.core.common.net.download.ProgressSubscriber;

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
            File tempFile = File.createTempFile("icmes-presentation", ".apk", com.zoyo.core.common.constants.Constants.FILE_DOWNLOAD);
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
