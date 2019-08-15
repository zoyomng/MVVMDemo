package com.zoyo.data.pattern_23.pattern_adapter;

/**
 * @Description: 类适配器模式
 * @Author: zoyomng
 * @CreateDate: 2019/7/29 14:15
 */
public class ClassAdapterPattern {
    public static void main(String[] args) {
        Target target = new ClassAdapter();
        target.request();
    }
}

//目标接口
interface Target {
    void request();
}

//适配者接口
class Adapter {
    void specificRequest() {
        System.out.println("适配者中的业务代码被调用");
    }
}

//类适配器类
class ClassAdapter extends Adapter implements Target {

    @Override
    public void request() {
        specificRequest();
    }
}
