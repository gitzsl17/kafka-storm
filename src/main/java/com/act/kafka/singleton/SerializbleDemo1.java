package com.act.kafka.singleton;

import java.io.*;

public class SerializbleDemo1 {

    public static void main(String[] args) throws Exception {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("testTemplate"));
        objectOutputStream.writeObject(Singleton2.getSingleton2());

        File file = new File("testTemplate");
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
        Singleton2 newInstance = (Singleton2) objectInputStream.readObject();
        System.out.println("getInstance1:" + Singleton2.getSingleton2());
        System.out.println("newInstance :" + newInstance);
        System.out.println("getInstance2:" + Singleton2.getSingleton2());

    }

}
