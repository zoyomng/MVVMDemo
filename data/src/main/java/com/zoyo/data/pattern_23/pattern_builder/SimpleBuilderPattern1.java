package com.zoyo.data.pattern_23.pattern_builder;

/**
 * @Description: 建造者模式:将一个复杂对象的构造与它的表示分离,使同样的构造过程可以创建不同的表示
 * 即产品的组成部分是不变的,但每一部分是可以灵活选择的
 * 主要角色:1.产品角色 2.抽象建造者 3.具体建造者 4.指挥者
 * <p>
 * 如果创建的产品种类只用一种,只需要一个具体建造者,这是可以省略抽象创建者,甚至可以省略指挥者
 * @Author: zoyomng
 * @CreateDate: 2019/7/26 14:04
 */
public class SimpleBuilderPattern1 {

    public static void main(String[] args) {
//        Product1 product1 = new Product1.ConcreteBuilder().buildPartA("A").buildPartB("B").build();
    }

}

//1.产品角色
class Product1 {
    String partA;
    String partB;

    //3.具体建造者
    class ConcreteBuilder {

        ConcreteBuilder buildPartA(String partA) {
            Product1.this.partA = partA;
            return this;
        }

        ConcreteBuilder buildPartB(String partB) {
            Product1.this.partB = partB;
            return this;
        }

        public Product1 build() {
            return new Product1();
        }
    }
}
