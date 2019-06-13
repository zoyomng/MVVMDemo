package com.zoyo.mvvmdemo.model;

import com.zoyo.net.Response;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface API {

    String BASE_URL = "http://www.baidu.com";

    @GET
    Flowable<Response> request();
}
