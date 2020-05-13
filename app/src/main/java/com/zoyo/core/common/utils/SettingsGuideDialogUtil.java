package com.zoyo.core.common.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import androidx.appcompat.app.AlertDialog;

/**
 * 引导用户至系统设置界面
 *
 * @date 2018/8/14
 */
public class SettingsGuideDialogUtil {


    /**
     * 引导用户设置权限允许
     */
    public static void guideUserToSetting(Context mContext, String permissionName) {
        // 弹出对话框告诉用户需要权限的原因, 并引导用户去应用权限管理中手动打开权限按钮
        String message = permissionName + "打开才可正常使用app,请到设置中开启\n\n设置路径:系统设置->餐宴网->权限";
        showPermissionDialog(mContext, "温馨提示", message, "设置", (dialog, which) -> {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    intent.setData(Uri.parse("package:" + mContext.getPackageName()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                    mContext.startActivity(intent);
                    dialog.dismiss();
                }, "取消", (dialog, which) -> System.out.println("退出程序")
//                BaseApplication.getAppContext().exitApp()
        );
    }

    private static void showPermissionDialog(Context mContext, String title, String message, String positiveButton
            , DialogInterface.OnClickListener positiveButtonListener, String negativeButton
            , DialogInterface.OnClickListener negativeButtonListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButton, positiveButtonListener)
                .setNegativeButton(negativeButton, negativeButtonListener)
                .setCancelable(false);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
