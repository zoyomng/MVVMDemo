package com.zoyo.core.common.constants;


import com.zoyo.core.BaseApplication;

import java.io.File;

public class Constants {
    public static final String PATH_CACHE = BaseApplication.getAppContext().getCacheDir().getAbsolutePath() + File.separator + "cache";
    public static final File FILE_DOWNLOAD = BaseApplication.getAppContext().getExternalFilesDir("download");
}