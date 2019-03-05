package com.itstyle.seckill.common.design;

/**
 * 单例 饿汉式
 * @author 周瑞忠
 * @date
 */
public class Singleton1 {

    private static Singleton1 singleton1 = new Singleton1();

    private Singleton1(){}

    /**
     * 线程安全
     * @return
     */
    public static synchronized Singleton1 getInstance1(){
        return singleton1;
    }

    /**
     * 线程不安全
     * @return
     */
    public static Singleton1 getInstance2(){
        return singleton1;
    }
}
