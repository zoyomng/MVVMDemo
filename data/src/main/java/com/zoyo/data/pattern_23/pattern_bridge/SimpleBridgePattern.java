package com.zoyo.data.pattern_23.pattern_bridge;

/**
 * @Description: 桥接模式应用场景
 * 1.当一个类存在两个独立变化的维度,且这两个维度都需要进行扩展时
 * 2.当一个系统不希望使用继承或因为多层次继承导致系统类的个数急剧增加时
 * 3.当一个系统需要在构件的抽象化角色和具体角色之间添加更多的灵活性时
 * @Author: zoyomng
 * @CreateDate: 2019/7/30 13:27
 */
public class SimpleBridgePattern {
    public static void main(String[] args) {
        Yellow yellow = new Yellow();
        HandBag handBag = new HandBag();
        handBag.setColor(yellow);
        System.out.println(handBag.getName());
    }
}

//实现化角色
interface Color {
    String getColor();
}

//具体实现化角色
class Yellow implements Color {

    @Override
    public String getColor() {
        return "yellow";
    }
}

class Red implements Color {

    @Override
    public String getColor() {
        return "red";
    }
}

//抽象化角色
abstract class Bag {
    Color color;

    void setColor(Color color) {
        this.color = color;
    }

    abstract String getName();
}

//扩展抽象化角色
class HandBag extends Bag {

    @Override
    String getName() {
        return color.getColor() + "HandBag";
    }
}

class Wallet extends Bag {

    @Override
    String getName() {
        return color.getColor() + "WalletBag";
    }
}
