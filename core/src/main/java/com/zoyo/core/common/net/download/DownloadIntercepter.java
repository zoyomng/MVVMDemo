package com.zoyo.core.common.net.download;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @Description: okhttp拦截器
 * @Author: zoyomng
 * @CreateDate: 2019/7/2 16:26
 */
public class DownloadIntercepter implements Interceptor {
    DownloadProgressListener listener;

    public DownloadIntercepter(DownloadProgressListener listener) {
        this.listener = listener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        return originalResponse.newBuilder()
                .body(new DownloadResponseBody(originalResponse.body(), listener))
                .build();
    }
}
