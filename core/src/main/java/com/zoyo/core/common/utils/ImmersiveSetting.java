package com.zoyo.core.common.utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

/**
 * ---日期----------维护人-----------
 * 2017/9/6       zuoyouming
 * <p>
 * 沉浸式状态栏及透明状态栏
 */

public class ImmersiveSetting {

    /**
     * 设置全屏且状态栏透明
     *
     * @param mActivity
     */
    public static void setFullScreenWithTranslucentStatus(Activity mActivity) {
        mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        mActivity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    /**
     * 设置全屏且状态栏隐藏
     *
     * @param mActivity
     */
    public static void setFullScreenWithNoStatus(Activity mActivity) {
        mActivity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    }


    /**
     * 设置全屏和状态栏背景色
     *
     * @param mActivity
     * @param colorRes  如0x66000000 / Color.TRANSPARENT
     */
    public static void setFullScreenAndStatusColor(Activity mActivity, int colorRes) {
        //android5.0以上才可使用
        if (Build.VERSION.SDK_INT >= 21) {
            // SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN和SYSTEM_UI_FLAG_LAYOUT_STABLE，注意两个Flag必须要结合在一起使用，
            // 表示会让应用的主体内容占用系统状态栏的空间(测试结果:不需要SYSTEM_UI_FLAG_LAYOUT_STABLE也可以)
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            mActivity.getWindow().getDecorView().setSystemUiVisibility(option);
//            mActivity.getWindow().setStatusBarColor(Color.TRANSPARENT);  //设置状态栏透明
            mActivity.getWindow().setStatusBarColor(colorRes);  //设置状态栏颜色
        }
    }

    public static void setFullScreenAndStatusColor(Activity mActivity) {
        setFullScreenAndStatusColor(mActivity, Color.TRANSPARENT);
    }


    /**
     * 设置全屏和状态栏背景色,导航栏颜色
     *
     * @param mActivity
     * @param colorRes  如0x66000000 / Color.TRANSPARENT
     */
    public static void setFullScreenAndNavigationColor(Activity mActivity, int... colorRes) {
        //android5.0以上才可使用
        if (Build.VERSION.SDK_INT >= 21) {
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

            mActivity.getWindow().getDecorView().setSystemUiVisibility(option);
            mActivity.getWindow().setStatusBarColor(colorRes[0]);
            mActivity.getWindow().setNavigationBarColor(colorRes.length >= 2 ? colorRes[1] : colorRes[0]);  //设置状态栏颜色
        }
    }

    public static void setFullScreenAndNavigationColor(Activity mActivity) {
        setFullScreenAndNavigationColor(mActivity, Color.TRANSPARENT);
    }


    /**
     * 真正的沉浸--状态栏hide
     *
     * @param mActivity
     * @param hasFocus
     */
    public static void setImmersive(Activity mActivity, boolean hasFocus) {
        //android5.0以上才可使用
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {

            mActivity.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }

        //使用方法-->重写Activity的onWindowFocusChanged()方法
//        @Override
//        public void onWindowFocusChanged(boolean hasFocus) {
//            super.onWindowFocusChanged(hasFocus);
//            ImmersiveSetting.setImmersive(this, hasFocus);
//        }
    }
}

/**
 * setSystemUiVisibility(int visibility)传入的实参类型如下：
 * <p>
 * 1. View.SYSTEM_UI_FLAG_VISIBLE ：状态栏和Activity共存，Activity不全屏显示。也就是应用平常的显示画面
 * 2. View.SYSTEM_UI_FLAG_FULLSCREEN ：Activity全屏显示，且状态栏被覆盖掉
 * 3. View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN ：Activity全屏显示，但是状态栏不会被覆盖掉，而是正常显示，只是Activity顶端占用系统状态栏的空间
 * 4. View.INVISIBLE ： Activity全屏显示，隐藏状态栏
 * 5. View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION：效果同View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
 * 6. View.SYSTEM_UI_LAYOUT_FLAGS：效果同View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
 * 7. View.SYSTEM_UI_FLAG_HIDE_NAVIGATION：隐藏虚拟按键(导航栏)。有些手机会用虚拟按键来代替物理按键。
 * 8. View.SYSTEM_UI_FLAG_LOW_PROFILE：状态栏显示处于低能显示状态(low profile模式)，状态栏上一些图标显示会被隐藏。
 */

