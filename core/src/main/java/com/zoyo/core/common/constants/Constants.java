package com.zoyo.core.common.constants;


import com.zoyo.core.BaseApplication;

import java.io.File;

public class Constants {
    public static final String PATH_CACHE = BaseApplication.getAppContext().getCacheDir().getAbsolutePath() + File.separator + "cache";
    public static final File FILE_DOWNLOAD = BaseApplication.getAppContext().getExternalFilesDir("download");

    //加载状态值
    public static final int STAUTS_LOADING = 0;
    public static final int STATUS_SUCCESS = 1;
    public static final int STATUS_NETWORK_ERROR = 2;
    public static final int STATUS_SERVER_ERROR = 3;

}
