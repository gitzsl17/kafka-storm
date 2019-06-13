package com.act.kafka.leetcode;

/**
 * @ClassName Reverse
 * @Description 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 * @Autor Administrator
 * @Date 2019/4/11 17:03
 * @Version 1.0
 **/
public class Reverse {
    public static int reverse(int x) {
        String str = x + "";
        char[] chars = str.toCharArray();
        StringBuffer _tempStr = new StringBuffer();
        for (int i = chars.length - 1; i >= 0; i--) {
            _tempStr.append(chars[i]);
        }
        String s = _tempStr.toString();
        if ((s.toCharArray()[0] + "").equals("0")) {
            s = s.replaceFirst("0", "");
        }
        System.out.println(Integer.parseInt(s));
        return 0;
    }

    public static void main(String[] args) {
//        int x = 123;
//        int pop = x % 10;
//        x /= 10;
//        System.out.println(pop + "---" + x);
        reverse(120);
    }
}
