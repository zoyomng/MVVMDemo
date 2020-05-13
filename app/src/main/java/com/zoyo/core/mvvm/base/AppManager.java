package com.zoyo.core.mvvm.base;

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

    void addActivity(Activity activity) {
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
    void removeActivity(Activity activity) {
        if (activity != null)
            activityStack.remove(activity);
    }

    /**
     * finish指定activity
     *
     * @param activity
     */
    private void finishActivity(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            activity.finish();
            activityStack.remove(activity);
        }
    }

    private void finishActivity(Class<? extends Activity> clazz) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(clazz)) {
                finishActivity(activity);
            }
        }
    }

    private void finishAllActivity() {
        for (int i = 0; i < activityStack.size(); i++) {
            if (null != activityStack.get(i)) {
                finishActivity(activityStack.get(i));
            }
        }
    }

    /**
     * 退出应用
     */
    private void exitApp() {
        finishAllActivity(); //关闭所有activity
        android.os.Process.killProcess(android.os.Process.myPid());//android结束进程
        System.exit(0); //0:正常退出

    }


}
