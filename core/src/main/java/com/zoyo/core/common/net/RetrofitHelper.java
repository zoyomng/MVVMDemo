package com.zoyo.core.common.net;

/**
 * ---日期----------维护人-----------变更内容----------
 * 2019/6/15       zozo          zozo
 * 与RetrofitManager功能一致,两种待选择
 */

import android.text.TextUtils;

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


public class RetrofitHelper {
    private final String baseUrl;
    private final String cachePath;
    private long connectTimeout = 10;
    private long readTimeout = 10;
    private long writeTimeout = 10;
    private String token;
    private Retrofit retrofit;
    private boolean mBuildConfigDebug;

    private RetrofitHelper(Builder builder) {

        this.baseUrl = builder.baseUrl;
        this.cachePath = builder.cachePath;
        this.connectTimeout = builder.connectTimeout;
        this.readTimeout = builder.readTimeout;
        this.writeTimeout = builder.writeTimeout;
        this.token = builder.token;
        this.mBuildConfigDebug = builder.mBuildConfigDebug;

        OkHttpClient okHttpClient = initOkHttpClient();
        retrofit = initRetrofit(okHttpClient);
    }

    public static class Builder {
        private String baseUrl;
        private String cachePath;
        private long connectTimeout;
        private long readTimeout;
        private long writeTimeout;
        private String token;
        private boolean mBuildConfigDebug;

        public Builder() {
        }

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder cachePath(String cachePath) {
            this.cachePath = cachePath;
            return this;
        }

        public Builder connectTimeout(long connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder readTimeout(long readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        public Builder writeTimeout(long writeTimeout) {
            this.writeTimeout = writeTimeout;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        /**
         * @param mBuildConfigDebug :BuildConfig.DEBUG
         * @return
         */
        public Builder showLog(boolean mBuildConfigDebug) {
            this.mBuildConfigDebug = mBuildConfigDebug;
            return this;
        }

        public RetrofitHelper build() {
            return new RetrofitHelper(this);
        }
    }

    private Retrofit initRetrofit(OkHttpClient okHttpClient) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl);
        return builder.client(okHttpClient).build();
    }
//TODO header中添加设备信息,版本信息

//    // 设备类型
//    enum PBDeviceType {
//        DEVICE_ANDROID = 0;                      // 安卓
//        DEVICE_IOS = 1;                          // 苹果
//        DEVICE_PC = 2;                           // PC
//    }
//
//    // 设备
//    message PBDevice {
//        string deviceId = 1;                    // 设备ID
//        string deviceOs = 2;                    // 设备操作系统
//        string deviceModel = 3;                 // 设备模型
//        PBDeviceType deviceType = 4;             // 设备类型，参考PBDeviceType
//    }
//
//    // 网络类型
//    enum PBNetworkType {
//        NET_UNKNOWN = 0;                         // 未知网络
//        NET_WIFI = 1;                            // WIFI
//        NET_2G = 2;                              // 2G网络
//        NET_3G = 3;                              // 3G网络
//        NET_4G = 4;                              // 4G网络
//    }
//
//    // APP信息
//    message PBAppInfo {
//        string versionName = 1;                 // 应用程序版本名
//        uint32 versionCode = 2;                 // 应用程序版本号
//        PBNetworkType network = 3;               // 网络信息
//        PBDevice device = 4;                     // 设备信息
//    }

    private OkHttpClient initOkHttpClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //BuildConfig.DEBUG:debug状态打印log
        if (mBuildConfigDebug) {
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

    /**
     * 加载API
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T creat(Class<T> clazz) {
        if (retrofit == null) {
            OkHttpClient okHttpClient = initOkHttpClient();
            retrofit = initRetrofit(okHttpClient);
        }

        return retrofit.create(clazz);
    }
}
