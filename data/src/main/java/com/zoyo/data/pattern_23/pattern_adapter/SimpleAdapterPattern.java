package com.zoyo.data.pattern_23.pattern_adapter;

/**
 * @Description: 实例
 * 例1】用适配器模式（Adapter）模拟新能源汽车的发动机。
 * <p>
 * 分析：新能源汽车的发动机有电能发动机（Electric Motor）和光能发动机（Optical Motor）等，各种发动机的驱动方法不同，
 * 例如，电能发动机的驱动方法 electricDrive() 是用电能驱动，而光能发动机的驱动方法 opticalDrive() 是用光能驱动，它们是适配器模式中被访问的适配者。
 * <p>
 * 客户端希望用统一的发动机驱动方法 drive() 访问这两种发动机，所以必须定义一个统一的目标接口 Motor，
 * 然后再定义电能适配器（Electric Adapter）和光能适配器（Optical Adapter）去适配这两种发动机。
 * @Author: zoyomng
 * @CreateDate: 2019/7/30 9:15
 */
public class SimpleAdapterPattern {
    public static void main(String[] args) {
        //客户端希望用统一的发动机驱动方法 drive() 访问这两种发动机
        Motor opticalAdapter = new OpticalAdapter();
        opticalAdapter.drive();
    }
}

//目标:发动机
interface Motor {
    void drive();
}

//适配者1:电能发动机
class ElectricMotor {
    void electricDrive() {
        System.out.println("电能发动机驱动汽车");
    }
}

//适配者2:光能发动机
class OpticalMotor {
    void opticalDrive() {
        System.out.println("光能发动机驱动汽车");
    }
}

//电能适配器
class ElectricAdapter implements Motor {

    private final ElectricMotor electricMotor;

    public ElectricAdapter() {
        electricMotor = new ElectricMotor();
    }

    @Override
    public void drive() {
        electricMotor.electricDrive();
    }
}

//光能适配器
class OpticalAdapter implements Motor {

    private final OpticalMotor opticalMotor;

    public OpticalAdapter() {
        opticalMotor = new OpticalMotor();
    }

    @Override
    public void drive() {
        opticalMotor.opticalDrive();
    }
}
