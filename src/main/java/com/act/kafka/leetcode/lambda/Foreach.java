package com.act.kafka.leetcode.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Foreach
 * @Description TODO
 * @Autor Administrator
 * @Date 2019-6-5 11:16
 * @Version 1.0
 **/
public class Foreach {

    public static void main(String[] args) {
        String[] atp = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer","Roger Federer",
                "Andy Murray","Tomas Berdych",
                "Juan Martin Del Potro"};
        List<String> players =  Arrays.asList(atp);
        // 以前的循环方式
        for (String player : players) {
            System.out.println(player + "; ");
        }
        players.forEach((player) -> System.out.println("得到的参数:" + player));
    }
}
