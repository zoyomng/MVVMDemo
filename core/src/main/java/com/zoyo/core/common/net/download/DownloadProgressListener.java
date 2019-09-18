package com.zoyo.core.common.net.download;

/**
 * @Description: java类作用描述
 * @Author: zoyomng
 * @CreateDate: 2019/7/2 16:10
 */
public interface DownloadProgressListener {
    void onFailed(String message);

    void onStart();

    void onProgress(int progress);

    void onFinish();
}
