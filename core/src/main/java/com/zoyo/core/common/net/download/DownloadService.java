package com.zoyo.core.common.net.download;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @Description: 下载相关API
 * @Author: zoyomng
 * @CreateDate: 2019/7/2 16:59
 */
public interface DownloadService {
    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url);
}
