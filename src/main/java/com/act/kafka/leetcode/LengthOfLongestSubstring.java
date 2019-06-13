package com.act.kafka.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @ClassName LengthOfLongestSubstring
 * @Description 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * @Autor Administrator
 * @Date 2019/4/10 14:48
 * @Version 1.0
 **/
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
//        int length = lengthOfLongestSubstring("assss");
//        System.out.println(length);
        moveDisc(5);
    }

    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        Set<Character> set = new HashSet<>();
        for (char c : chars) {
            set.add(c);
        }
        return set.size();
    }


    public static void moveDisc(int DiscNum) {
        move(DiscNum, 'A', 'B', 'C');
    }

    private static void move(int discNum, char a, char b, char c) {
        if (discNum == 1) {
            System.out.println("盘" + discNum + "由" + a + "移至" + c);
        } else {
            // a c b
            move(discNum - 1, a, c, b);
            System.out.println("盘" + discNum + "由" + a + "移至" + c);
            // b a c
            move(discNum - 1, b, a, c);
        }
    }
}
