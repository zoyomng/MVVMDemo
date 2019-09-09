# Android 版本之间的差异及特性

## 6.0
------

> * 动态申请权限


## 7.0
------

> * 应用间共享文件

> 在Android7.0系统上。Android 框架强制运行了 StrictMode API 政策禁止向你的应用外公开 file:// URI。

假设一项包含文件 file:// URI类型 的 Intent 离开你的应用，应用失败，并出现 FileUriExposedException 异常，如调用系统相机拍照，或裁切照片。

应对策略：若要在应用间共享文件。能够发送 content:// URI类型的Uri，并授予 URI 暂时訪问权限。 进行此授权的最简单方式是使用 FileProvider类。 如需有关权限和共享文件的很多其它信息，请參阅共享文件。


● 给其它应用传递 file:// URI 类型的Uri。可能会导致接受者无法訪问该路径。

因此。在Android7.0中尝试传递 file:// URI 会触发 FileUriExposedException。

> 实例问题
调用系统相机拍照，裁切照片。
在Android7.0之前，假设你想调用系统相机拍照能够通过下面代码来进行：
```android

File file=new File(Environment.getExternalStorageDirectory(), "/temp/"+System.currentTimeMillis() + ".jpg");

if (!file.getParentFile().exists()){
    file.getParentFile().mkdirs();
}

Uri imageUri = Uri.fromFile(file);

Intent intent = new Intent();
intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//将拍取的照片保存到指定URI
startActivityForResult(intent,1006);
```
> 在Android7.0上使用上述方式调用系统相拍照会抛出例如以下异常：

```
android.os.FileUriExposedException: file:////storage/emulated/0/temp/1474956193735.jpg exposed beyond app through Intent.getData()
at android.os.StrictMode.onFileUriExposed(StrictMode.java:1799)
at android.net.Uri.checkFileUriExposed(Uri.java:2346)
at android.content.Intent.prepareToLeaveProcess(Intent.java:8933)
at android.content.Intent.prepareToLeaveProcess(Intent.java:8894)
at android.app.Instrumentation.execStartActivity(Instrumentation.java:1517)
at android.app.Activity.startActivityForResult(Activity.java:4223)
...
at android.app.Activity.startActivityForResult(Activity.java:4182)
```

闪退截图例如以下：

这里写图片描写叙述

这是由于Android7.0运行了“StrictMode API 政策禁”的原因，只是小伙伴们不用操心，上文讲到了能够用FileProvider来解决这一问题，
如今我们就来一步一步的解决问题。

使用FileProvider
使用FileProvider的大致过程例如以下：

* 第一步：在manifest清单文件里注冊provider
```xml
<provider
    android:name="android.support.v4.content.FileProvider"
    android:authorities="com.tangchaoke.hrhj.huarunhaojing"
    android:exported="false"
    android:grantUriPermissions="true">
    <meta-data
        android:name="android.support.FILE_PROVIDER_PATHS"
        android:resource="@xml/file_paths"/>
</provider>
```

> * exported:要求必须为false，为true则会报安全异常。
> * grantUriPermissions:true，表示授予 URI 暂时訪问权限。

* 第二步：指定共享的文件夹

为了指定共享的文件夹我们须要在资源(res)文件夹下创建一个xml文件夹，然后创建一个名为“file_paths”(名字能够随便起，仅仅要和在manifest注冊的provider所引用的resource保持一致就可以)的资源文件。内容例如以下：

```xml
<?xml version="1.0" encoding="utf-8"?>
<paths xmlns:android="http://schemas.android.com/apk/res/android">
    <external-path
        name="files_root"
        path="Android/data/com.tangchaoke.hrhj.huarunhaojing/" />
</paths>
```
> * 代表的根文件夹： Context.getFilesDir()
> * 代表的根文件夹: Environment.getExternalStorageDirectory()
> * 代表的根文件夹: getCacheDir()

> 心得：上述代码中path=”“，是有特殊意义的，它代码根文件夹。也就是说你能够向其它的应用共享根文件夹及其子文件夹下不论什么一个文件了，假设你将path设为path=”pictures”，
那么它代表着根文件夹下的pictures文件夹(eg:/storage/emulated/0/pictures)，假设你向其它应用分享pictures文件夹范围之外的文件是不行的。

* 第三步：使用FileProvider
上述准备工作做完之后，如今我们就能够使用FileProvider了。


还是以调用系统相机拍照为例，我们须要将上述拍照代码改动为例如以下：

```android
File file=new File(Environment.getExternalStorageDirectory(), "/temp/"+System.currentTimeMillis() + ".jpg");

if (!file.getParentFile().exists())
    file.getParentFile().mkdirs();

Uri imageUri = FileProvider.getUriForFile(context, "com.jph.takephoto.fileprovider", file);//通过FileProvider创建一个content类型的Uri

Intent intent = new Intent();
intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //加入这一句表示对目标应用暂时授权该Uri所代表的文件
intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//将拍取的照片保存到指定URI
startActivityForResult(intent,1006);
```

上述代码中主要有两处改变：

> * 1、将之前Uri的scheme类型为file的Uri改成了有FileProvider创建一个content类型的Uri。
> * 2、加入了intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);来对目标应用暂时授权该Uri所代表的文件。

心得：上述代码通过FileProvider的Uri getUriForFile (Context context, String authority, File file)
静态方法来获取Uri，该方法中authority參数就是清单文件里注冊provider的android:authorities=”com.jph.takephoto.fileprovider”。


对Webserver如tomcat。IIS比較熟悉的小伙伴，都仅仅知道为了站点内容的安全和高效，Webserver都支持为站点内容设置一个虚拟文件夹，事实上FileProvider也有异曲同工之处。

将getUriForFile方法获取的Uri打印出来例如以下:

content://com.jph.takephoto.fileprovider/camera_photos/temp/1474960080319.jpg。
当中camera_photos就是file_paths.xml中paths的name。

由于上述指定的path为path=”“，所以content://com.jph.takephoto.fileprovider/camera_photos/代表的真实路径就是根文件夹，即：/storage/emulated/0/。
content://com.jph.takephoto.fileprovider/camera_photos/temp/1474960080319.jpg代表的真实路径是：/storage/emulated/0/temp/1474960080319.jpg。

裁切照片代码：

```android
File file=new File(Environment.getExternalStorageDirectory(), "/temp/"+System.currentTimeMillis() + ".jpg");

if (!file.getParentFile().exists())
    file.getParentFile().mkdirs();

Uri outputUri = Uri.fromFile(file);
Uri imageUri=Uri.fromFile(new File("/storage/emulated/0/temp/1474960080319.jpg"));

Intent intent = new Intent("com.android.camera.action.CROP");
intent.setDataAndType(imageUri, "image/*");
intent.putExtra("crop", "true");
intent.putExtra("aspectX", 1);
intent.putExtra("aspectY", 1);
intent.putExtra("scale", true);
intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
intent.putExtra("noFaceDetection", true); // no face detection
startActivityForResult(intent,1008);
```

和拍照一样。上述代码在Android7.0上相同会引起android.os.FileUriExposedException异常，解决的方法就是上文说说的使用FileProvider。

然后。将上述代码改为例如以下就可以：
```android
File file=new File(Environment.getExternalStorageDirectory(), "/temp/"+System.currentTimeMillis() + ".jpg");

if (!file.getParentFile().exists())
    file.getParentFile().mkdirs();

Uri outputUri = FileProvider.getUriForFile(context, "com.jph.takephoto.fileprovider",file);
Uri imageUri=FileProvider.getUriForFile(context, "com.jph.takephoto.fileprovider", new File("/storage/emulated/0/temp/1474960080319.jpg");//通过FileProvider创建一个content类型的Uri

Intent intent = new Intent("com.android.camera.action.CROP");
intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
intent.setDataAndType(imageUri, "image/*");
intent.putExtra("crop", "true");
intent.putExtra("aspectX", 1);
intent.putExtra("aspectY", 1);
intent.putExtra("scale", true);
intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
intent.putExtra("noFaceDetection", true); // no face detection
startActivityForResult(intent,1008);

```

另外，裁切照片推荐大家使用开源工具库TakePhoto，
TakePhoto是一款在Android设备上获取照片（拍照或从相冊、文件里选择）、裁剪图片、压缩图片的开源工具库。



## 8.0
------
> * apk 安装过程中需要检查"外来应用安装权限"

* 解决方案 : 清单文件添加权限申请
```xml
    <uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES"/>
```
```
//检查是否有"外来应用安装权限"
context.getPackageManager().canRequestPackageInstalls()
//引导用户跳转系统设置
 Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
((Activity) context).startActivityForResult(intent, 2000);
```




