package com.zoyo.data.pattern_23.pattern_factory;

/**
 * @Description: 抽象工厂模式
 * 工厂方法模式只生产一个等级产品,而抽象工厂模式可生产多个等级的产品
 * @Author: zoyomng
 * @CreateDate: 2019/7/23 15:33
 */
public class AbstractFactoryPattern {

    //产品族1
    interface Animal {
        void show();
    }

    //马
    class Horse implements Animal {

        @Override
        public void show() {

        }
    }

    //牛
    class Cattle implements Animal {

        @Override
        public void show() {

        }
    }

    //产品族2
    interface Plant {
        void show();
    }

    class Fruitage implements Plant {

        @Override
        public void show() {

        }
    }

    class Vagetables implements Plant {

        @Override
        public void show() {

        }
    }

    //农场
    interface Farm {
        public Animal newAnimal();

        public Plant newPlant();
    }

    //北京农厂
    class BeijingFarm implements Farm {

        @Override
        public Animal newAnimal() {
            //新马出生
            return new Horse();
        }

        @Override
        public Plant newPlant() {
            //蔬菜长成
            return new Fruitage();
        }
    }

    //河南农场
    class HenanFram implements Farm {

        @Override
        public Animal newAnimal() {
            //新牛出生
            return new Cattle();
        }

        @Override
        public Plant newPlant() {
            //蔬菜长成
            return new Vagetables();
        }
    }

    public static void main(String[] args) {
//        BeijingFarm beijingFarm = new BeijingFarm();
//        Animal horse = beijingFarm.newAnimal();
//        Plant fruit = beijingFarm.newPlant();
//        horse.show();
//        fruit.show();
    }
}
