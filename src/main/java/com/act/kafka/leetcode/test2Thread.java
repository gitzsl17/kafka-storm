package com.act.kafka.leetcode;

/**
 * @ClassName test2Thread
 * @Description TODO
 * @Autor Administrator
 * @Date 2019/4/2 14:28
 * @Version 1.0
 **/
public class test2Thread implements Runnable {
    int b = 100;

    synchronized void m1() throws InterruptedException {
        b = 1000;
        Thread.sleep(500); //6
        System.out.println("b=" + b);
    }

    synchronized void m2() throws InterruptedException {
        Thread.sleep(2500); //5
        b = 2000;
    }

    public static void main(String[] args) throws InterruptedException {
        test2Thread tt = new test2Thread();
        Thread t = new Thread(tt); //1
        t.start(); //2

        tt.m2(); //3
        System.out.println("main thread b=" + tt.b); //4
    }

    @Override
    public void run() {
        try {
            m1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
