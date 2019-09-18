package com.zoyo.core.common.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.zoyo.core.BaseApplication;


public class NetUtil {
    /**
     * 检查是否有可用网络
     *
     * @return
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) BaseApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }

    /**
     * 检查WiFi是否连接
     *
     * @return
     */
    public static boolean isWiFiConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) BaseApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return wifiInfo != null;
    }

    /**
     * 检查手机网络是否连接
     *
     * @return
     */
    public static boolean isMobileNetworkConnected(Context context) {
        ConnectivityManager connectivityManagerystemService = (ConnectivityManager) BaseApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobileNetworkInfo = connectivityManagerystemService.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return mobileNetworkInfo != null;
    }
}
