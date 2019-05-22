package com.zoyo.core.base;

import android.app.Activity;

import java.util.Stack;

public class AppManager {

    private Stack<Activity> activityStack;

    private AppManager() {
    }

    public static AppManager getInstance() {
        return AppManagerHolder.instance;
    }

    private static class AppManagerHolder {
        private static final AppManager instance = new AppManager();
    }

    private void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 当前activity
     *
     * @return
     */
    private Activity getCurrentActivity() {
        if (activityStack != null)
            return activityStack.lastElement();
        return null;
    }

    /**
     * 移除指定activity
     *
     * @param activity
     */
    private void removeActivity(Activity activity) {
        if (activity != null)
            activityStack.remove(activity);
    }

    /**
     * @param activity
     */
    private void finishActivity(Activity activity) {
        if (activity != null && !activity.isFinishing())
            activity.finish();
    }


}
