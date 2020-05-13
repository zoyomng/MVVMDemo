package com.zoyo.core.common.utils;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.io.Reader;

import io.reactivex.annotations.NonNull;


/**
 * ---日期----------维护人---------
 * 2017/11/29     zuoyouming
 * 将json转为javabean
 */
public class GsonUtils {

    public static <T> T json2Bean(String result, Class<T> clz) {

        try {
            if (TextUtils.isEmpty(result)) {
//                ToastUtil.shortShow("数据解析错误");
                return null;
            }

            Gson gson = new Gson();
            T t = gson.fromJson(result, clz);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
//            ToastUtil.shortShow("数据解析错误");
            return null;
        }

    }

    public static <T> T json2Bean(Reader result, Class<T> clz) {
        if (result == null) {
//            ToastUtil.shortShow("数据解析错误");
            return null;
        }

        Gson gson = new Gson();
        T t = gson.fromJson(result, clz);
        return t;
    }


    public static String toJson(@NonNull Object obj) {

        try {
            Gson gson = new Gson();
            return gson.toJson(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
