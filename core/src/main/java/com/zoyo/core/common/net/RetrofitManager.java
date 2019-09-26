package com.zoyo.core.common.net;

/**
 * ---日期----------维护人-----------变更内容----------
 * 2019/6/15       zozo          zozo
 * 在Application中初始化
 * RetrofitManager.Configs.getInstance()
 * .baseUrl(API.BASE_URL)
 * .connectTimeout(10)
 * .readTimeout(10)
 * .writeTimeout(10)
 * .showLog(BuildConfig.DEBUG)
 * .token("")
 * .apply();
 */

import android.text.TextUtils;

import androidx.annotation.CheckResult;

import com.zoyo.core.common.constants.Constants;
import com.zoyo.core.common.net.Interceptor.CacheInterceptor;
import com.zoyo.core.common.net.Interceptor.TokenHeaderInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitManager {
    private static final long DEFAULT_CONNECT_TIMEOUT = 10;
    private static final long DEFAULT_READ_TIMEOUT = 10;
    private static final long DEFAULT_WRITE_TIMEOUT = 10;

    private static String baseUrl;
    private static String cachePath;
    private static long connectTimeout = DEFAULT_CONNECT_TIMEOUT;
    private static long readTimeout = DEFAULT_READ_TIMEOUT;
    private static long writeTimeout = DEFAULT_WRITE_TIMEOUT;
    private static String token;
    private static boolean buildConfigDebug;

    private RetrofitManager() {
    }

    @CheckResult
    public static Retrofit build() {
        OkHttpClient okHttpClient = buildOkHttpClient();
        Retrofit.Builder builder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl);

        return builder.client(okHttpClient).build();
    }

    private static OkHttpClient buildOkHttpClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //BuildConfig.DEBUG:debug状态打印log
        if (buildConfigDebug) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }

        File cacheFile = new File(TextUtils.isEmpty(cachePath) ? Constants.PATH_CACHE : cachePath);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        builder.cache(cache);

        builder.addNetworkInterceptor(new CacheInterceptor());
        builder.addNetworkInterceptor(new TokenHeaderInterceptor(token));
        builder.addInterceptor(new CacheInterceptor());
        //设置超时
        builder.connectTimeout(connectTimeout, TimeUnit.SECONDS);
        builder.readTimeout(readTimeout, TimeUnit.SECONDS);
        builder.writeTimeout(writeTimeout, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }


    public static class Configs {
        private String baseUrl;
        private String cachePath;
        private long connectTimeout = RetrofitManager.DEFAULT_CONNECT_TIMEOUT;
        private long readTimeout = RetrofitManager.DEFAULT_READ_TIMEOUT;
        private long writeTimeout = RetrofitManager.DEFAULT_WRITE_TIMEOUT;
        private String token;
        private boolean mBuildConfigDebug;

        private Configs() {
        }

        @CheckResult
        public static Configs getInstance() {
            return new Configs();
        }

        @CheckResult
        public Configs baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        @CheckResult
        public Configs cachePath(String cachePath) {
            this.cachePath = cachePath;
            return this;
        }

        @CheckResult
        public Configs connectTimeout(long connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        @CheckResult
        public Configs readTimeout(long readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        @CheckResult
        public Configs writeTimeout(long writeTimeout) {
            this.writeTimeout = writeTimeout;
            return this;
        }

        @CheckResult
        public Configs token(String token) {
            this.token = token;
            return this;
        }

        /**
         * @param mBuildConfigDebug :BuildConfig.DEBUG
         * @return
         */
        @CheckResult
        public Configs showLog(boolean mBuildConfigDebug) {
            this.mBuildConfigDebug = mBuildConfigDebug;
            return this;
        }

        public void apply() {
            RetrofitManager.baseUrl = baseUrl;
            RetrofitManager.cachePath = cachePath;
            RetrofitManager.connectTimeout = connectTimeout;
            RetrofitManager.readTimeout = readTimeout;
            RetrofitManager.writeTimeout = writeTimeout;
            RetrofitManager.token = token;
            RetrofitManager.buildConfigDebug = mBuildConfigDebug;
        }
    }

}
