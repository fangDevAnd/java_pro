package com.mingrisoft.reflection;

import java.util.Date;

public class ClassNameTest {
    public static void main(String[] args) {
        String dateName = new Date().getClass().getName();// 获得引用类型名称
        System.out.println("非数组引用类型的名称：" + dateName);// 输出引用类型名称
        String byteName = byte.class.getName();// 获得原始类型名称
        System.out.println("基本类型的名称：" + byteName);// 输出原始类型名称
        String oneDimensionArray = new Date[4].getClass().getName();// 获得一维引用类型数组
        System.out.println("一维引用类型数组：" + oneDimensionArray);// 输出一维引用类型数组名称
        String twoDimensionArray = new int[4][4].getClass().getName();// 获得二维原始类型
        System.out.println("二维基本类型数组：" + twoDimensionArray);// 输出二维引用类型数组名称
    }
}
