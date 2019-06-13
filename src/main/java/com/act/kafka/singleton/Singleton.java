package com.act.kafka.singleton;


/**
 * 单例
 */
public class Singleton {

    // 空参构造,防止其他类创建本类对象
    private Singleton() {
    }

    // static可以通过类名点调用,   final防止成员变量被改变,调用多次也是只有一个对象.
//    public static final Singleton instance = new Singleton();

    // 改成静态内部类
    private static class instance {
        public static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return instance.INSTANCE;
    }

}
