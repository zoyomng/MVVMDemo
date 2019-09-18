package com.zoyo.core.common.net;

public class Response<T> {
    public int status;
    public String message;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
