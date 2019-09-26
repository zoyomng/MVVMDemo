package com.zoyo.core.common.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.StringRes;

import com.zoyo.core.BaseApplication;


/**
 * Toast封装
 */
public class ToastUtil {


    private static long tempTimeMillis = 0;
    private static Toast toast;
    private static CharSequence oldText;

    private ToastUtil() {
    }

    public static void shortShow(@StringRes int stringRes) {
        CharSequence text = BaseApplication.getAppContext().getResources().getText(stringRes);
        shortShow(text);
    }

    @SuppressLint("ShowToast")
    public static void shortShow(CharSequence text) {
        if (toast == null) {
            toast = Toast.makeText(BaseApplication.getAppContext(), text, Toast.LENGTH_SHORT);
        }
        if (TextUtils.equals(oldText, text)) {
            if (isNeedToShow()) {
                toast.show();
            }
        } else {
            oldText = text;
            toast.setText(text);
            toast.show();
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
        boolean isNeed = System.currentTimeMillis() - tempTimeMillis > Toast.LENGTH_SHORT;
        if (isNeed) {
            tempTimeMillis = System.currentTimeMillis();
        }
        return isNeed;
    }

}
