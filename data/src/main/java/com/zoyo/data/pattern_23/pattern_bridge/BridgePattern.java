package com.zoyo.data.pattern_23.pattern_bridge;

/**
 * @Description: 桥接模式
 * 将抽象与实现分离,使他们可以独立变化.
 * 它是用组合关系代替继承关系来实现,从而降低了抽象和实现这两个可变维度的耦合度
 * @Author: zoyomng
 * @CreateDate: 2019/7/30 13:07
 */
public class BridgePattern {
    public static void main(String[] args) {
        Implementor imple = new ConcreteImplementA();
        Abstraction abs = new RefinedAbstraction(imple);
        abs.operation();
    }
}

//实现化角色
interface Implementor {
    void operationImpl();
}

//具体实现化角色
class ConcreteImplementA implements Implementor {

    @Override
    public void operationImpl() {
        System.out.println("具体实现化角色被访问");
    }
}

//抽象化角色
abstract class Abstraction {
    Implementor imple;

    public Abstraction(Implementor imple) {
        this.imple = imple;
    }

    abstract void operation();
}

//扩展抽象化角色
class RefinedAbstraction extends Abstraction {

    public RefinedAbstraction(Implementor imple) {
        super(imple);
    }

    @Override
    void operation() {
        System.out.println("扩展抽象化角色被访问");
        imple.operationImpl();
    }
}
