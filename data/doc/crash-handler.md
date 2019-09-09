# Android 全局异常捕获-CrashHandler

```java

/**
 * 程序异常捕获类，捕获异常后重启应用
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    public static final String TAG = "CrashHandler";

    // 系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    // CrashHandler实例
    private static CrashHandler INSTANCE = new CrashHandler();
    // 程序的Context对象
    private Context mContext;
    // 用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<String, String>();
    // 用于格式化日期,作为日志文件名的一部分
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    /**
     * 保证只有一个CrashHandler实例
     */
    private CrashHandler() {
    }

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    /**
     * 初始化
     *
     * @param context 上下文
     */
    public void init(Context context) {
        mContext = context;
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会跳入该方法来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable t) {
        if (!handleException(t) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, t);
        } else {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Log.e(TAG, "error : ", e);
            }
            // 退出并重启
            Intent intent = new Intent(mContext, Activity_Splash.class);

            PendingIntent restartIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            AlarmManager mgr = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 5000, restartIntent); // 5秒钟后重启应用
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param t Throwable对象
     * @return true:表示处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable t) {
        if (t == null) {
            return false;
        }
        collectDeviceInfo(mContext);

        //权限判定
        if (ContextCompat.checkSelfPermission(App.getInstance(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            saveCrashInfo2File(t);
        }

        return true;
    }


    /**
     * 收集设备参数信息
     *
     * @param context 上下文
     */
    public void collectDeviceInfo(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                String packageName = pi.packageName;
                infos.put("packageName", packageName);
                infos.put("appVersionName", versionName);
                infos.put("appVersionCode", versionCode);
            }
        } catch (NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                Log.d(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Log.e(TAG, "an error occured when collect crash info", e);
            }
        }
    }

    /**
     * 保存错误信息到文件中
     *
     * @param t Throwable对象
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    private String saveCrashInfo2File(Throwable t) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        t.printStackTrace(printWriter);
        Throwable cause = t.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        try {
            String time = formatter.format(new Date());
            String fileName = mContext.getPackageName() + "_crash_" + time + ".log";
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String path = Environment.getExternalStorageDirectory() + "/crash/";
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(path + fileName);
                fos.write(sb.toString().getBytes());
                fos.close();
            }
            String path = mContext.getFilesDir() + "/" + fileName;
            FileOutputStream fos = new FileOutputStream(path + fileName);
            fos.write(sb.toString().getBytes());
            fos.close();
            return fileName;
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file...", e);
        }
        return null;
    }
}
```

### 使用方式-MyApplica(MyApplica extends Application)中
```java
//全局捕获异常
CrashHandler crashHandler = CrashHandler.getInstance();
crashHandler.init(this);
```

### 上传捕获的崩溃日志
```java
/**
 * 上传崩溃日志文件
 */
private void uploadCrashLog() {
    ThreadManager.getThreadPoolProxy().execute(() -> {
        final File file = new File(Environment.getExternalStorageDirectory() + "/crash/");
        if (file.list() != null && file.list().length > 0) { // files目录下有文件
            for (int i = 0; i < file.list().length; i++) {
                if (file.list()[i].startsWith(getPackageName() + "_crash_")) {
                    final File crashLogFile = new File(file, file.list()[i]);
                    // 找到崩溃日志文件并上传
                    OkHttpUtils.post()
                            .addFile("crash_log", crashLogFile.getName(), crashLogFile)
                            .url(HttpUrls.CRASH_LOG)
                            .build().execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            LogUtil.e("上传日志请求失败");
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            if (crashLogFile.delete()) {
                                LogUtil.i("删除成功");
                            } else {
                                LogUtil.i("删除失败");
                            }
                        }
                    });
                }
            }
        }
    });
}
```

