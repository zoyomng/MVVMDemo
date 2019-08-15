package com.zoyo.data.pattern_23.pattern_factory;

/**
 * @Description: 工厂方法设计模式：定义一个创建产品对象的工厂接口,将产品对象的实际创建工作推迟到具体子工厂类中.
 * 满足创建型模式中所要求的"创建与使用相分离"的特点
 * 客户不关心创建产品的细节,只关心产品的品牌(只知道工厂名,而不知道产品名)
 * @Author: zoyomng
 * @CreateDate: 2019/7/23 14:58
 */
public class FactoryMethodPattern {

    //抽象产品:提供产品的展示方法
    interface Product {
        public void show();
    }

    //具体产品:实现产品的展示方法
    class ConcreteProduct1 implements Product {

        @Override
        public void show() {
            System.out.println("具体产品1展示...");
        }
    }

    class ConcreteProduct2 implements Product {

        @Override
        public void show() {
            System.out.println("具体产品2展示...");
        }
    }

    //抽象工厂:提供产品的生产方法
    interface AbstractFactory {
        public Product newProduct();
    }

    //具体工厂1:实现产品的生产方法
    class ConcreteFactory1 implements AbstractFactory {

        @Override
        public Product newProduct() {
            System.out.println("具体工厂1生产具体产品1...");
            return new ConcreteProduct1();
        }
    }

    //具体工厂2:实现产品的生产方法
    class ConcreteFactory2 implements AbstractFactory {

        @Override
        public Product newProduct() {
            System.out.println("具体工厂2生产具体产品2...");
            return new ConcreteProduct2();
        }
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
//        HenanFram concreteFactory2 = new HenanFram();
//        Animal product = concreteFactory2.newProduct();
//        product.show();
    }

}
