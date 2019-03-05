package com.itstyle.seckill.common.design;

/**
 * 单例懒汉式
 * @author 周瑞忠
 * @date
 */
public class Singleton2 {

    private static Singleton2 singleton2;

    private Singleton2(){}

    /**
     * 双重锁
     * @return
     */
    public static Singleton2 getInstance(){
        if (null == singleton2){
            synchronized (singleton2){
                if (null == singleton2){
                    singleton2 = new Singleton2();
                }
            }
        }
        return singleton2;
    }

    /**
     * 线程不安全
     * @return
     */
    public static Singleton2 getInstance2(){
        if (null == singleton2){
            singleton2 = new Singleton2();
        }
        return singleton2;
    }

    /**
     * 线程安全
     * @return
     */
    public static synchronized Singleton2 getInstance3(){
        if (null == singleton2){
            singleton2 = new Singleton2();
        }
        return singleton2;
    }
}
