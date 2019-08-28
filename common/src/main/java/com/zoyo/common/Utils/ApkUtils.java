package com.zoyo.common.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.content.FileProvider;

import java.io.File;

/**
 * Created by cheng on 2017/8/1.
 */

public class ApkUtils {
    /**
     * 获取当前程序的版本名称
     */
    public static String getVersionName(Context context) {
        // 包的管理者 获取应用程序清单文件中所有信息
        PackageManager pm = context.getPackageManager(); // 初始化PackageManager
        // 根据应用程序的包名 获取应用程序的信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取当前程序的版本号
     */
    public static int getVersionCode(Context context) {
        // 包的管理者 获取应用程序清单文件中所有信息
        PackageManager pm = context.getPackageManager(); // 初始化PackageManager
        // 根据应用程序的包名 获取应用程序的信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * install an apk bu apkPath
     *
     * @param context Context
     * @param apkPath apkPath
     */
    public static final void installApk(Activity context, String apkPath) {
        File apkFile = new File(apkPath);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // 判断版本是否在7.0以上
            // 在AndroidManifest.xml中声明的的android:authorities值
            Uri apkUri = FileProvider.getUriForFile(context, "com.zoyo", apkFile);
            Intent install = new Intent(Intent.ACTION_VIEW);
            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            install.setDataAndType(apkUri, "application/vnd.android.package-archive");
            context.startActivity(install);
        } else {
            Intent install = new Intent(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(install);
        }

        context.finish();
    }
}
