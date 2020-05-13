package com.zoyo.core.common.net.Interceptor;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BearerTokenHeaderInterceptor implements Interceptor {
    private String token;

    BearerTokenHeaderInterceptor(String token) {
        this.token = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        if (TextUtils.isEmpty(token)) {
            return chain.proceed(originalRequest);
        } else {
            Request updateRequest = originalRequest.newBuilder().header("Authorization", "Bearer " + token).build();
            return chain.proceed(updateRequest);
        }
    }
}
