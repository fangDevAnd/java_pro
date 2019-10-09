package com.mingrisoft.math;

import java.util.Random;

import org.apache.commons.math.stat.Frequency;

public class FrequencyTest {
    public static void main(String[] args) {
        Frequency frequency = new Frequency();
        for (int i = 0; i < 100; i++) {
            frequency.addValue(new Random().nextInt(10));// 增加100个小于10的随机数
        }
        System.out.println("频度分布直方图");
        for (int i = 0; i < 10; i++) {// 对于0~9每个数值绘制直方图
            System.out.print("数值" + i + "的频度：");
            for (int j = 0; j < frequency.getCount(i); j++) {// 输入不同个星号来表示不同的频度
                System.out.print("*");
            }
            System.out.println("\t" + frequency.getCumFreq(i));// 输出累计频度
        }
    }
}
