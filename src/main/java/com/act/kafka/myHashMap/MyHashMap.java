package com.act.kafka.myHashMap;

import java.util.HashMap;

/**
 * @ClassName MyHashMap
 * @Description TODO
 * @Autor Administrator
 * @Date 2019/4/22 15:44
 * @Version 1.0
 **/
public class MyHashMap<K> extends HashMap<K, String> {

    @Override
    public String put(K key, String value) {
        String newValue = value;
        if (containsKey(key)) {
            String oldValue = get(key);
            newValue = oldValue + "," + newValue;
        }
        return super.put(key, newValue);
    }
}
