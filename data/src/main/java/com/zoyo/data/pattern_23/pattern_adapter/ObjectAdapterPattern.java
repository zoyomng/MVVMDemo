package com.zoyo.data.pattern_23.pattern_adapter;

/**
 * @Description: 对象适配模式
 * @Author: zoyomng
 * @CreateDate: 2019/7/29 14:40
 */
public class ObjectAdapterPattern {
    public static void main(String[] args){
        Adapter adapter = new Adapter();
        Target objectAdapter = new ObjectAdapter(adapter);
        objectAdapter.request();
    }
}

class ObjectAdapter implements Target {

    private Adapter adapter;

    public ObjectAdapter(Adapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void request() {
        adapter.specificRequest();
    }
}

