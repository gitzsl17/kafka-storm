package com.act.kafka.singleton;

import java.io.Serializable;

public class Singleton2 implements Serializable {

    private volatile static Singleton2 instance;

    private Singleton2() {
    }

    public static Singleton2 getSingleton2() {
        if (instance == null) {
            synchronized (Singleton2.class) {
                if (instance == null) {
                    instance = new Singleton2();
                }
            }
        }
        return instance;
    }

//    private Object readResolve(){
//        return instance;
//    }
}
