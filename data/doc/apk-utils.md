# Android PackageManager 应用apk解析

## apk版本检查更新与安装

```android
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
            return  packageInfo.versionName;
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
            Uri apkUri = FileProvider.getUriForFile(context,
                    "com.tangchaoke.hrhj.huarunhaojing", apkFile);
            Intent install = new Intent(Intent.ACTION_VIEW);
            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            install.setDataAndType(apkUri,
                    "application/vnd.android.package-archive");
            context.startActivity(install);
        } else {
            Intent install = new Intent(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(apkFile),
                    "application/vnd.android.package-archive");
            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(install);
        }

        context.finish();
    }
}
```

### 后台下载apk

```android 
/**
 * 后台下载apk
 *
 * @param downloadUrl apk下载地址
 * @param versionCode 新版本号
 */
private void downloadBackground(String downloadUrl, final int versionCode) {
    File file = new File(Environment.getExternalStorageDirectory() + "/tck/", getPackageName() + "_" + versionCode + ".apk");
    if (file.exists()) {
//            ApkUtils.installApk(this, file.getAbsolutePath());
        file.delete();
    }

    showDownloadProgressDialog();
    OkHttpUtils.get().url(downloadUrl).build()
            .execute(new FileCallBack(Environment.getExternalStorageDirectory() + "/tck/",
                    getPackageName() + "_" + versionCode + ".temp") {

                @Override
                public void inProgress(float progress, long total, int id) {
                    super.inProgress(progress, total, id);
                    //下载进度提示

                    //判断是否为null
                    if (mTvDownloadPercent == null) {
                        mTvDownloadPercent = dialogView.findViewById(R.id.tv_download_percent);
                    }

                    mTvDownloadPercent.setText((int) (progress * 100) + "%");
                    String fileSize = Formatter.formatFileSize(mContext, total);
                    mProgressBar.setProgress((int) (progress * 100));

                    //判断是否为null
                    if (mTvDownloadProgress == null) {
                        mTvDownloadProgress = dialogView.findViewById(R.id.tv_progress);
                    }

                    mTvDownloadProgress.setText(fileSize);
                }

                @Override
                public void onError(Call call, Exception e, int id) {
                    LogUtil.e("下载出错：" + e.getMessage());
                }

                @Override
                public void onResponse(File response, int id) {
                    mDownloadProgressDialog.dismiss();
                    mDownloadProgressDialog = null;
                    mProgressBar = null;
                    mTvDownloadProgress = null;
                    mTvDownloadPercent = null;
                    boolean isRenameSuccess = FileUtils.renameFile(
                            Environment.getExternalStorageDirectory() + "/tck",
                            getPackageName() + "_" + versionCode + ".temp",
                            getPackageName() + "_" + versionCode + ".apk");
                    if (isRenameSuccess) {
                        // 重命名之后进行安装
                        ApkUtils.installApk(mContext,
                                Environment.getExternalStorageDirectory() + "/tck/" +
                                        getPackageName() + "_" + versionCode + ".apk");
                    }
                }
            });
}
```

#### 下载进度条

```android
/**
 * 下载进度提示框
 */
private void showDownloadProgressDialog() {
    dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_download_progress, null);
    mDownloadProgressDialog = new AlertDialog.Builder(this).setView(dialogView).create();
    mDownloadProgressDialog.show();
    mDownloadProgressDialog.setCancelable(false);
    mProgressBar = dialogView.findViewById(R.id.progressBar);
    mTvDownloadPercent = dialogView.findViewById(R.id.tv_download_percent);
    mTvDownloadProgress = dialogView.findViewById(R.id.tv_progress);
}
```

### 上传崩溃日志