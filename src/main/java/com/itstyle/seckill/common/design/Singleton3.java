package com.itstyle.seckill.common.design;

/**
 * 单例 静态内部类
 *
 * @author 周瑞忠
 * @date
 */
public class Singleton3 {

    private Singleton3() {
    }

    public static Singleton3 getInstance() {
        return SingletonHolder.singleton3;
    }

    private static class SingletonHolder {
        private static final Singleton3 singleton3 = new Singleton3();
    }
}
