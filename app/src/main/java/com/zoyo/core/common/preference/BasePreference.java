package com.zoyo.core.common.preference;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * SharedPreferences工具类
 */

public class BasePreference {

    private Context context;
    private SharedPreferences sp;
    private String SP_FILE_NAME = "zoyo";


    protected BasePreference(Context context) {
        this.context = context;
        sp = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * SP中写入String类型value
     *
     * @param key   键
     * @param value 值
     */
    public void putString(String key, String value) {
        sp.edit().putString(key, value).apply();
    }

    /**
     * SP中读取String
     *
     * @param key 键
     */
    public String getString(String key) {
        return sp.getString(key, "");
    }


    public void putInt(String key, int value) {
        sp.edit().putInt(key, value).apply();
    }

    public int getInt(String key) {
        return sp.getInt(key, 0);
    }

    public void putLong(String key, long value) {
        sp.edit().putLong(key, value).apply();
    }

    public long getLong(String key) {
        return sp.getLong(key, 0);
    }

    public void putFloat(String key, float value) {
        sp.edit().putFloat(key, value).apply();
    }

    public float getFloat(String key) {
        return sp.getFloat(key, -1F);
    }

    public void putBoolean(String key, boolean value) {
        sp.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    public void putStringSet(String key,   Set<String> set) {
        sp.edit().putStringSet(key, set).apply();

    }

    public Set<String> getStringSet(String key) {
        return sp.getStringSet(key,new LinkedHashSet<String>());
    }

    public void remove(String key) {
        sp.edit().remove(key).apply();
    }

    /**
     * 清除指定的信息
     *
     * @param name 键名
     * @param key  若为null 则删除name下所有的键值
     */
    public void clearPreference(String name, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (key != null) {
            editor.remove(key);
        } else {
            editor.clear();
        }
        editor.apply();
    }
}
