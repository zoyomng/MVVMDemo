package com.zoyo.core.common.net.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Description: 下载管理类
 * @Author: zoyomng
 * @CreateDate: 2019/7/2 16:41
 */
public class DownloadManager {

    //文件下载一般为get请求,直接使用url,baseUrl无关紧要
    private static final String BASE_URL = "http://www.baidu.com/";
    private static final long DEFAULT_TIMEOUT = 10;
    private OkHttpClient.Builder builder;

    private DownloadManager() {
    }

    public static DownloadManager getInstance() {
        return ManagerHolder.instance;
    }

    private static class ManagerHolder {
        static DownloadManager instance = new DownloadManager();
    }

    /**
     * 下载文件
     *
     * @param url
     * @param filePath
     * @param progressSubscriber
     */
    public void downloadFile(String url, final String filePath, ProgressSubscriber progressSubscriber) {

        DownloadIntercepter downloadIntercepter = new DownloadIntercepter(progressSubscriber);

        if (builder != null) {
            builder.addInterceptor(downloadIntercepter);
        } else {
            builder = new OkHttpClient.Builder()
                    .addInterceptor(downloadIntercepter)
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        DownloadService downloadService = retrofit.create(DownloadService.class);

        downloadService.download(url)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map(new Function<ResponseBody, Object>() {
                    @Override
                    public Object apply(ResponseBody responseBody) throws Exception {
                        return writeFile(responseBody, filePath);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(progressSubscriber);
    }

    private boolean writeFile(ResponseBody responseBody, String filePath) {
        // todo change the file location/name according to your needs
        File futureStudioIconFile = new File(filePath);

        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            byte[] fileReader = new byte[1024];
            inputStream = responseBody.byteStream();
            outputStream = new FileOutputStream(futureStudioIconFile);
            while (true) {
                int read = inputStream.read(fileReader);

                if (read == -1) {
                    break;
                }
                outputStream.write(fileReader, 0, read);
            }
            outputStream.flush();
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
