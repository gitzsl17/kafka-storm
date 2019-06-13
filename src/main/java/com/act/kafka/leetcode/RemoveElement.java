package com.act.kafka.leetcode;

import java.util.Arrays;

/**
 * @ClassName RemoveElement
 * @Description TODO
 * @Autor Administrator
 * @Date 2019/4/22 11:34
 * @Version 1.0
 **/
public class RemoveElement {

    public static void main(String[] args) {
        removeElement(new int[]{1, 2, 3, 3, 4, 4, 5, 6, 3}, 3);
    }

    public static int removeElement(int[] nums, int val) {
        int j = 0;
        for (int i = 0; i <= nums.length - 1; i++) {
            if (nums[i] != val) {
                nums[j] = nums[i];
                j++;
            }
        }
        int[] ints = Arrays.copyOfRange(nums, 0, j);
        System.out.println(j);
        for (int is : ints) {
            System.out.println(is);
        }
        return j;
    }
}
