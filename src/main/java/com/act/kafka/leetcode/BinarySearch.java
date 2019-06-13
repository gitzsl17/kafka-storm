package com.act.kafka.leetcode;

/**
 * @ClassName BinarySearch
 * @Description 从一个排好序的数组查找一个key在array的下标
 * @Autor Administrator
 * @Date 2019/4/12 9:19
 * @Version 1.0
 **/
public class BinarySearch {
    public static void main(String[] args) {
        int[] ints = new int[]{1, 2, 3, 4, 5, 6};
        binarySearch(ints, 3);
    }

    static int binarySearch(int[] array, int key) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2;
            if (array[mid] == key) {
                System.out.println(mid);
                return mid;
            } else if (array[mid] < key) {
                left = mid;
            } else {
                right = mid + 1;
            }
        }
        return -1;
    }
}
