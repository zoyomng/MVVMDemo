# Data 资料

## 存储路径
` 

System.out.println(Environment.getDataDirectory());

System.out.println(Environment.getDownloadCacheDirectory());

System.out.println(Environment.getRootDirectory());

System.out.println(getApplication().getPackageCodePath());

System.out.println(getApplication().getPackageResourcePath());

System.out.println(getApplication().getCacheDir());

System.out.println(getApplication().getFilesDir());

System.out.println(getApplication().getDatabasePath("test"));

System.out.println(getApplication().getDir("test", Context.MODE_PRIVATE));


System.out.println(Environment.getExternalStorageDirectory());

System.out.println(Environment.getExternalStoragePublicDirectory("test"));

System.out.println(getApplication().getExternalCacheDir());

System.out.println(getApplication().getExternalFilesDir("test") );

System.out.println(getApplication().getExternalFilesDir(null));

`
 

*  Environment.getDataDirectory() -> /data

*  Environment.getDownloadCacheDirectory() -> /data/cache
 
*  Environment.getRootDirectory() -> /system
 
*  getApplication().getPackageCodePath() -> /data/app/com.zoyo.mvvmdemo-2/base.apk
 
*  getApplication().getPackageResourcePath() -> /data/app/com.zoyo.mvvmdemo-2/base.apk
 
*  getApplication().getCacheDir() -> /data/user/0/com.zoyo.mvvmdemo/cache
 
*  getApplication().getFilesDir() -> /data/user/0/com.zoyo.mvvmdemo/files
 
*  getApplication().getDatabasePath("test") -> /data/user/0/com.zoyo.mvvmdemo/databases/test
 
*  getApplication().getDir("test", Context.MODE_PRIVATE) -> /data/user/0/com.zoyo.mvvmdemo/app_test

*  Environment.getExternalStorageDirectory() -> /storage/emulated/0

*  Environment.getExternalStoragePublicDirectory("test") -> /storage/emulated/0/test

*  getApplication().getExternalCacheDir() -> /storage/emulated/0/Android/data/com.zoyo.mvvmdemo/cache

*  getApplication().getExternalFilesDir("test") -> /storage/emulated/0/Android/data/com.zoyo.mvvmdemo/files/test

*  getApplication().getExternalFilesDir(null) -> /storage/emulated/0/Android/data/com.zoyo.mvvmdemo/files


