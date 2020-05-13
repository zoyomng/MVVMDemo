package com.zoyo.core.common.net.Interceptor;


import com.zoyo.core.common.utils.NetUtil;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //request
        if (!NetUtil.isNetworkConnected()) {
            //没有网络,强制使用缓存
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        //response
        Response response = chain.proceed(request);
        if (NetUtil.isNetworkConnected()) {
            int maxAge = 0;
            //有网络时,不缓存,最大保存时长为0
            response.newBuilder()
                    .header("Cache-Control", "public,max-age=" + maxAge)
                    .removeHeader("Pragma")
                    .build();
        } else {
            //无网络时,设置超时为4周
            int maxStale = 60 * 60 * 24 * 28;
            response.newBuilder()
                    .header("Cache-Control", "public,only-if-cached,max-stale=" + maxStale)
                    .removeHeader("Pragma")
                    .build();
        }

        return response;
    }
}
