package com.mingrisoft.exceptions;

public class ExceptionTest {
    public static void main(String[] args) {
        Object array[] = new String[3]; // 声明一个长度为3的Object类型的数组
        array[0] = new Integer(1); // 将数组的第一个元素赋值为整数对象1
        System.out.println(array[0]); // 输出数组的第一个元素
    }
}
