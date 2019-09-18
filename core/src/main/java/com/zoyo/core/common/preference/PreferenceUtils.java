package com.zoyo.core.common.preference;

import android.content.Context;

import com.zoyo.core.BaseApplication;


/**
 * ---日期----------维护人---------
 * 2017/11/22     zuoyouming
 * SharedPreference管理类
 */

public class PreferenceUtils extends BasePreference {

    //======================================需要增加的key就在这里新建↓↓↓↓========================
    //用户名的key
    private String USER_NAME = "user_name";

    //======================================需要增加的key↑↑↑↑====================================
    private static PreferenceUtils preferenceUtils;

    private PreferenceUtils(Context context) {
        super(context);
    }

    /**
     * 通过Application来获取Context对象，所以在获取preferenceUtils时不需要传入Context。
     *
     * @return
     */
    public synchronized static PreferenceUtils getInstance() {
        if (null == preferenceUtils) {
            preferenceUtils = new PreferenceUtils(BaseApplication.getAppContext());
        }
        return preferenceUtils;
    }


    //userName
    public void setUserName(String name) {
        putString(USER_NAME, name);
    }

    public String getUserName() {
        return getString(USER_NAME);
    }

}
