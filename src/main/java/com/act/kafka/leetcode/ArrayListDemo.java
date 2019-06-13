package com.act.kafka.leetcode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ArrayListDemo
 * @Description TODO
 * @Autor Administrator
 * @Date 2019/4/8 11:34
 * @Version 1.0
 **/
public class ArrayListDemo {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        System.out.println(list);
        int i = 15 >> 1;
        System.out.println(i);
//        lists.add("1");
        Class clazz = list.getClass();
        Method method = clazz.getMethod("add", Object.class);
        method.invoke(list, "hello");
        method.invoke(list, "word");
        System.out.println(list);

        Singletion singletion = Singletion.SINGLETION;
        System.out.println(singletion.hashCode());
        Singletion singletion1 = Singletion.SINGLETION;
        System.out.println(singletion1.hashCode());
        Singletion singletion2 = Singletion.SINGLETION;
        System.out.println(singletion2.hashCode());
    }
}
