package com.zoyo.common.utils;

import android.widget.Toast;

import androidx.annotation.StringRes;

import com.zoyo.common.application.BaseApplication;

/**
 * 无需等待,立即显示的Toast封装
 */
public class ToastUtil {


    private ToastUtil() {
    }

    public static void shortShow(@StringRes int stringRes) {
        Toast.makeText(BaseApplication.getAppContext(), stringRes, Toast.LENGTH_SHORT).show();
    }

    public static void shortShow(CharSequence message) {
        Toast.makeText(BaseApplication.getAppContext(), message, Toast.LENGTH_SHORT).show();
    }


    public static void longShow(@StringRes int stringRes) {
        Toast.makeText(BaseApplication.getAppContext(), stringRes, Toast.LENGTH_LONG).show();
    }

    public static void longShow(CharSequence message) {
        Toast.makeText(BaseApplication.getAppContext(), message, Toast.LENGTH_LONG).show();
    }
}
