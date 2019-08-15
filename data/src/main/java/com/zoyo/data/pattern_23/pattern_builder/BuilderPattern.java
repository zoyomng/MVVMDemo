package com.zoyo.data.pattern_23.pattern_builder;

/**
 * @Description: 建造者模式:将一个复杂对象的构造与它的表示分离,使同样的构造过程可以创建不同的表示
 * 即产品的组成部分是不变的,但每一部分是可以灵活选择的
 * 主要角色:1.产品角色 2.抽象建造者 3.具体建造者 4.指挥者
 * @Author: zoyomng
 * @CreateDate: 2019/7/26 14:04
 */
public class BuilderPattern {

    public static void main(String[] args) {
        ConcreteBuilder concreteBuilder = new ConcreteBuilder();
        concreteBuilder.buildPartA("A");
        concreteBuilder.buildPartB("B");
        Director director = new Director(concreteBuilder);
        Product construct = director.construct();
        construct.show();
    }
}

//1.产品角色
class Product {
    String partA;
    String partB;

    public void setPartA(String partA) {
        this.partA = partA;
    }

    public void setPartB(String partB) {
        this.partB = partB;
    }

    public void show() {

    }
}

//2.抽象建造者
abstract class Builder {
    Product product = new Product();

    abstract void buildPartA(String partA);

    abstract void buildPartB(String partB);

    public Product getResult() {
        return product;
    }
}

//3.具体建造者
class ConcreteBuilder extends Builder {

    @Override
    void buildPartA(String partA) {
        product.setPartA(partA);
    }

    @Override
    void buildPartB(String partB) {
        product.setPartB(partB);
    }
}

//4.指挥者:调用建造者对象中部件构造与装配方法完成复杂对象的创建,在指挥者中不涉及具体产品信息
class Director {
    Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public Product construct() {
        return builder.getResult();
    }
}
