package com.zoyo.net;

public class RetrofitConfigs {
    //非必选字段
    String baseUrl = "";
    String cachePath;
    long connectTimeout;
    long readTimeout;
    long writeTimeout;
    String token;
    boolean mBuildConfigDebug;

    private RetrofitConfigs() {
    }

    public static RetrofitConfigs getInstance() {
        return OptionsHolder.instance;
    }

    private static class OptionsHolder {
        static RetrofitConfigs instance = new RetrofitConfigs();
    }

    public RetrofitConfigs baseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public RetrofitConfigs cachePath(String cachePath) {
        this.cachePath = cachePath;
        return this;
    }

    public RetrofitConfigs connectTimeout(long connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    public RetrofitConfigs readTimeout(long readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }

    public RetrofitConfigs writeTimeout(long writeTimeout) {
        this.writeTimeout = writeTimeout;
        return this;
    }

    public RetrofitConfigs token(String token) {
        this.token = token;
        return this;
    }

    /**
     * @param mBuildConfigDebug :BuildConfig.DEBUG
     * @return
     */
    public RetrofitConfigs showLog(boolean mBuildConfigDebug) {
        this.mBuildConfigDebug = mBuildConfigDebug;
        return this;
    }


}