package com.zoyo.core.common.rx;

/**
 * ---日期----------维护人---------
 * 2017/11/24     zuoyouming
 */
public class RxBusBaseMessage {

    private int code;
    private Object object;

    public RxBusBaseMessage(int code, Object object) {
        this.code = code;
        this.object = object;
    }

    public RxBusBaseMessage() {
    }

    public int getCode() {
        return code;
    }

    public Object getObject() {
        return object;
    }
}
