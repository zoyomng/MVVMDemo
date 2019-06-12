package com.zoyo.net;

import android.app.Application;
import android.text.TextUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitManager {
    private final Application application;
    private final String baseUrl;
    private final String cachePath;
    private long connectTimeout = 10;
    private long readTimeout = 10;
    private long writeTimeout = 10;
    private String token;

    private RetrofitManager(Builder builder) {

        this.application = builder.application;
        this.baseUrl = builder.baseUrl;
        this.cachePath = builder.cachePath;
        this.connectTimeout = builder.connectTimeout;
        this.readTimeout = builder.readTimeout;
        this.writeTimeout = builder.writeTimeout;
        this.token = builder.token;

        OkHttpClient okHttpClient = initOkHttpClient();
        initRetrofit(okHttpClient);
    }

    static class Builder {
        //必选字段
        private final Application application;
        //非必选字段
        String baseUrl;
        String cachePath;
        long connectTimeout;
        long readTimeout;
        long writeTimeout;
        String token;

        public Builder(Application application) {
            this.application = application;
        }

        //必选字段
        Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        Builder cachePath(String cachePath) {
            this.cachePath = cachePath;
            return this;
        }

        Builder connectTimeout(long connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        Builder readTimeout(long readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        Builder writeTimeout(long writeTimeout) {
            this.writeTimeout = writeTimeout;
            return this;
        }

        Builder token(String token) {
            this.token = token;
            return this;
        }

        RetrofitManager build() {
            return new RetrofitManager(this);
        }
    }

    private Retrofit initRetrofit(OkHttpClient okHttpClient) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl);
        return builder.client(okHttpClient).build();
    }

    private OkHttpClient initOkHttpClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //TODO moduleLibrary 中的BuildConfig.需要修改
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }

        final String PATH_CACHE = application.getCacheDir().getAbsolutePath() + File.separator + "cache";

        File cacheFile = new File(TextUtils.isEmpty(cachePath) ? PATH_CACHE : cachePath);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
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
        };
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.addNetworkInterceptor(new TokenHeaderInterceptor(token));
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);
        //设置超时
        builder.connectTimeout(connectTimeout, TimeUnit.SECONDS);
        builder.readTimeout(readTimeout, TimeUnit.SECONDS);
        builder.writeTimeout(writeTimeout, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }
}
