package com.act.kafka.singleton;

import java.lang.reflect.Constructor;

public class ReflectionDemo {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class clazz = Class.forName("com.act.kafka.singleton.User");
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            Class[] parameterTypes = constructor.getParameterTypes();
            for (Class c : parameterTypes) {
                System.out.println("1" + c.getName());
            }
        }

        Class clazz2 = Class.forName("com.act.kafka.singleton.Singleton2");
        Constructor[] constructors1 = clazz2.getConstructors();
        for (Constructor constructor : constructors1) {
            Class[] parameterTypes = constructor.getParameterTypes();
            for (Class c : parameterTypes) {
                System.out.println("2" + c.getName());
            }
        }
//        Singleton2 s = (Singleton2)clazz2.newInstance();


        System.out.println("a:" + Singleton2.getSingleton2());
//        System.out.println("s:" + s);
    }
}
