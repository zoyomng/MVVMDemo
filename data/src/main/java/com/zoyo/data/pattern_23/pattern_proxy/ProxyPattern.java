package com.zoyo.data.pattern_23.pattern_proxy;

/**
 * @Description: 代理模式
 * @Author: zoyomng
 * @CreateDate: 2019/7/29 10:50
 */
public class ProxyPattern {

    //抽象主题
    interface Subject {
        void request();
    }

    class RealSubject implements Subject {

        @Override
        public void request() {
            System.out.println("访问真实主题方法...");
        }
    }

    class Proxy implements Subject {

        private RealSubject realSubject;

        @Override
        public void request() {
            if (realSubject == null) {
                realSubject = new RealSubject();
            }
            preRequest();
            realSubject.request();
            proRequest();
        }

        private void preRequest() {
            System.out.println("访问真实主题之前的预处理..");
        }

        private void proRequest() {
            System.out.println("访问真实主题之后的后续处理...");
        }
    }

    public static void main(String[] args) {
//        Proxy proxy = new Proxy();
//        proxy.request();
    }

}
