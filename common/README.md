# Common 
### Android 7.0 FileProvider


AndroidManifest.xml
```xml
<application>
    <provider
        android:name="androidx.core.content.FileProvider"
        android:authorities="${applicationId}.provider"
        android:exported="false"
        android:grantUriPermissions="true">
        <meta-data
            android:name="android.support.FILE_PROVIDER_PATHS"
            android:resource="@xml/provider_paths" />
    </provider>
</application>
```

xml/provider_paths 
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <paths>
        <cache-path
            name="cache_path"
            path="." />
        <files-path
            name="files_path"
            path="." />
        <external-path
            name="external_storage_root"
            path="." />

        <external-files-path
            name="external_files_path"
            path="." />
        <external-cache-path
            name="external_cache_path"
            path="." />

        <!--配置root-path,这样可以读取sd卡和一些分身的目录,否则微信分身保存图片导致 
        java.lang.IllegalArgumentException: Failed to find configured root that contains /storage/emulated/999/tencent/MicroMsg/WeiXin/export1544062754693.jpg，
        在小米6的手机上微信分身有这个crash，华为没有-->
        <root-path
            name="root-path"
            path="" />

    </paths>

</resources>

    <!--root-path 对应DEVICE_ROOT,也就是File DEVICE_ROOT = new File("/")，即根目录，一般不需要配置。-->
    <!--files-path对应 content.getFileDir() 获取到的目录。-->
    <!--cache-path对应 content.getCacheDir() 获取到的目录-->
    <!--external-path对应 Environment.getExternalStorageDirectory() 指向的目录。-->
    <!--external-files-path对应 ContextCompat.getExternalFilesDirs() 获取到的目录。-->
    <!--external-cache-path对应 ContextCompat.getExternalCacheDirs() 获取到的目录。-->

```
使用方式(如应用安装):

```android
    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    if (Build.VERSION.SDK_INT >= 24) {
        Uri apkUri = FileProvider.getUriForFile(this, getPackageName(), file);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
    } else {
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
    }
    startActivityForResult(intent, 0);

```
