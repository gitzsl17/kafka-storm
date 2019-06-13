package com.act.kafka.leetcode.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @ClassName Sort
 * @Description TODO
 * @Autor Administrator
 * @Date 2019-6-5 11:28
 * @Version 1.0
 **/
public class Sort {
    public static void main(String[] args) {
        String[] players = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka", "David Ferrer",
                "Roger Federer", "Andy Murray",
                "Tomas Berdych", "Juan Martin Del Potro",
                "Richard Gasquet", "John Isner"};
        Arrays.sort(players, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        List<String> strings = Arrays.asList(players);
        strings.forEach(o -> System.out.println(o));
        System.out.println("-------");
        for(String str : players){
            System.out.println(str);
        }
    }
}
