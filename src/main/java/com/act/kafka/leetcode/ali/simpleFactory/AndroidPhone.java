package com.act.kafka.leetcode.ali.simpleFactory;


public class AndroidPhone implements Phone {
    @Override
    public void createPhone() {
        System.out.print("Android");
    }
}
