package com.mingrisoft.lang;

import java.util.Arrays;

import org.apache.commons.lang.ArrayUtils;

public class ArrayUtilsTest {
    public static void main(String[] args) {
        int[] array0 = new int[5]; // 创建长度为5的int类型数组
        Arrays.fill(array0, 8); // 将数组中的元素全部初始化为8
        System.out.println("数组中的元素是：");
        System.out.println(Arrays.toString(array0)); // 输出数组中的全部元素
        System.out.println("在数组的最后增加元素10");
        int[] array1 = ArrayUtils.add(array0, 10); // 在数组的最后增加元素10
        System.out.println("数组中的元素是：");
        System.out.println(Arrays.toString(array1)); // 输出新数组中的全部元素
        System.out.println("在数组的开头增加元素10");
        int[] array2 = ArrayUtils.add(array0, 0, 10); // 在数组的开头增加元素10
        System.out.println("数组中的元素是：");
        System.out.println(Arrays.toString(array2)); // 输出新数组中的全部元素
        System.out.println("将新生成的两个数组合并");
        int[] array3 = ArrayUtils.addAll(array1, array2);// 合并新生成的两个数组
        System.out.println("数组中的元素是：");
        System.out.println(Arrays.toString(array3)); // 输出新数组中的全部元素
    }
}
