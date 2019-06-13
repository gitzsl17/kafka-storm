package com.act.kafka.leetcode;

/**
 * @ClassName addAnyParams
 * @Descriptio 实现任意个数数据的相加操作
 * @Autor Administrator
 * @Date 2019/4/13 16:18
 * @Version 1.0
 **/
public class addAnyParams {

    public static void main(String[] args) {
        System.out.println(add(new int[]{1, 2, 3}));
        System.out.println(add(1, 2, 3, 4));
    }

    public static int add(int... data) {
        int sum = 0;
        for (int i : data) {
            sum += i;
        }
        return sum;
    }
}
