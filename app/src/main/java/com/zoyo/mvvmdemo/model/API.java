package com.zoyo.mvvmdemo.model;

import com.zoyo.core.common.net.Response;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface API {

//    String BASE_URL = "http://192.168.97.36:8088/";
    String BASE_URL = "http://192.168.88.11:8088/";
    @GET("info/configs/app/version/")
    Flowable<Response> request();
}
