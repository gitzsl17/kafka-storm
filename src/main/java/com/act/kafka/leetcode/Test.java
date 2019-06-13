package com.act.kafka.leetcode;

import java.util.*;

public class Test {

    public static void main(String[] args) {
//        TestModelUser notNullUser = new TestModelUser();
//        TestModelUser user = notNullUser;
//        Optional<TestModelUser> user1 = Optional.ofNullable(user);
//        user1.ifPresent(u -> System.out.println("张三"));

//        getSum();

        int[] numbers = {1, 3, 2, 11, 5, 6, 7, 9, 8, 10};
        int sum = 12;
        System.out.println("输入的数组是：" + Arrays.toString(numbers) + " ;给定的和是：" + sum);
        ArrayList<NumList> indexsAndNum = twoSumSolution2(numbers, sum);
        testForEach(indexsAndNum);
    }

    // [2,9,3,...]  用一个for循环找出两个数相加之和为12.

    public static void getSum() {
        int[] arrs = {2, 9, 3, 8, 7, 5, 4, 6, 10, 11, 1};
        int sum = 12;

        Map<Integer, Integer> map = new HashMap<>();
        for (int arr : arrs) {
            if (!map.containsKey(arr)) {
                map.put(sum - arr, arr);
            }
        }
        System.out.println(map);
    }

    public static ArrayList<NumList> twoSumSolution2(int[] numbers, int sum) {
        //建一个ArrayList，里面有两个int[2]数组（num1[]和num2[]）
        ArrayList<NumList> indexsAndNum = new ArrayList<NumList>();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(numbers[i])) {
                //num1[0]和num1[1]存储第一个数的位置和值，num2[0]和num2[1]存储第二个数的位置和值。
                int num1[] = new int[2];
                int num2[] = new int[2];
                int index = map.get(numbers[i]);
                num1[0] = index + 1;
                num1[1] = numbers[index];
                num2[0] = i + 1;
                num2[1] = numbers[i];
                NumList numList = new NumList(num1, num2);//把符合要求的数对存储到ArrayList里面
                indexsAndNum.add(numList);
            } else {
                map.put(sum - numbers[i], i);//把总和减去当前数作为key放入map，然后与新进来的数字比对
                continue;
            }
        }
        return indexsAndNum;
    }

    /**
     * 输出满足条件的数的位置和数值
     */
    public static void testForEach(ArrayList<NumList> indexsAndNum) {
        System.out.println("符合条件的数对有：");
        for (Object obj : indexsAndNum) {
            NumList temp = (NumList) obj;
            System.out.println("第" + temp.num1[0] + "个数字 ‘" + temp.num1[1] + "’ 与 " + "第" + temp.num2[0] + "个数字 ‘" + temp.num2[1] + "’。");
        }
    }

    public static class NumList {
        public int[] num1;//第一个加数的位置和值
        public int[] num2;//第二个加数的位置和值

        public NumList(int[] num1, int[] num2) {
            this.num1 = num1;
            this.num2 = num2;
        }

    }
}
