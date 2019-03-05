package com.itstyle.seckill.common.design;

public class SingletonTest {

    public static void main(String[] args) {
        Singleton1 singleton1 = Singleton1.getInstance1();
        Singleton2 singleton2 = Singleton2.getInstance();
        Singleton3 singleton3 = Singleton3.getInstance();
        Singleton4 singleton4 = Singleton4.INSTANCE;
    }
}
