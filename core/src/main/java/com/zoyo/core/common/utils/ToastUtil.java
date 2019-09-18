package com.zoyo.core.common.utils;

import android.widget.Toast;

import androidx.annotation.StringRes;

import com.zoyo.core.BaseApplication;


/**
 * Toast封装
 */
public class ToastUtil {


    private static final long INTERVAL_TIME = 2000;
    private static long tempTimeMillis = 0;

    private ToastUtil() {
    }

    public static void shortShow(@StringRes int stringRes) {
        if (isNeedToShow()) {
            Toast.makeText(BaseApplication.getAppContext(), stringRes, Toast.LENGTH_SHORT).show();
        }
    }


    public static void shortShow(CharSequence message) {
        if (isNeedToShow()) {
            Toast.makeText(BaseApplication.getAppContext(), message, Toast.LENGTH_SHORT).show();
        }
    }


    public static void longShow(@StringRes int stringRes) {
        if (isNeedToShow()) {
            Toast.makeText(BaseApplication.getAppContext(), stringRes, Toast.LENGTH_LONG).show();
        }

    }

    public static void longShow(CharSequence message) {
        if (isNeedToShow()) {
            Toast.makeText(BaseApplication.getAppContext(), message, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 自定义布局
     *
     * @param layoutResId
     */
//    public static ToastUtil setView(@LayoutRes int layoutResId) {
//        LayoutInflater layoutInflater = (LayoutInflater) BaseApplication.getAppContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        return this;
//    }


    /**
     * 避免连续多次点击,出现多个吐司,长时间显示
     *
     * @return
     */
    private static boolean isNeedToShow() {
        boolean isNeed = System.currentTimeMillis() - tempTimeMillis > INTERVAL_TIME;
        if (isNeed) {
            tempTimeMillis = System.currentTimeMillis();
        }
        return isNeed;
    }

}
