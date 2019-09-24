package com.zoyo.mvvmdemo.model.repository;

import androidx.lifecycle.MutableLiveData;

import com.zoyo.core.common.net.download.DownloadManager;
import com.zoyo.core.common.net.download.ProgressSubscriber;
import com.zoyo.core.mvvm.base.BaseRepository;
import com.zoyo.core.mvvm.base.OnDataCallback;
import com.zoyo.mvvmdemo.app.Constants;

import java.io.File;
import java.io.IOException;

/**
 * @Description: java类作用描述
 * @Author: zoyomng
 * @CreateDate: 2019/7/12 15:35
 */
public class DownloadRepository extends BaseRepository {
    public void download(MutableLiveData<Integer> statusValue, OnDataCallback<Integer> callback) {
        statusValue.setValue(com.zoyo.core.common.constants.Constants.STAUTS_LOADING);

        try {
            File tempFile = File.createTempFile("icmes-presentation", ".apk", com.zoyo.core.common.constants.Constants.FILE_DOWNLOAD);
            DownloadManager.getInstance().downloadFile(Constants.APK_URL, tempFile.getAbsolutePath(), new ProgressSubscriber() {
                @Override
                public void onStart() {
                    super.onStart();
                    statusValue.postValue(com.zoyo.core.common.constants.Constants.STATUS_SUCCESS);
                }

                @Override
                public void onFailed(String message) {
                    super.onFailed(message);
                    statusValue.postValue(com.zoyo.core.common.constants.Constants.STATUS_SERVER_ERROR);
                }

                @Override
                public void onProgress(int progress) {
                    super.onProgress(progress);
                    callback.onData(progress);
                }

                @Override
                public void onFinish() {
                    super.onFinish();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
