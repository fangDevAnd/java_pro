package com.mingrisoft.system;

public class Timing {
    public static double round(double value) {
        return Math.round(value * 10.0) / 10.0;// 利用Math类的round方法进行四舍五入计算
    }
    
    public static String getElapsedText(long elapsedMillis) {
        if (elapsedMillis < 60000) {
            double unit = round(elapsedMillis / 1000.0);// 将时间转换成秒
            return unit + "秒";// 在转换完的时间后增加单位
        } else if (elapsedMillis < 60000 * 60) {
            double unit = round(elapsedMillis / 60000.0);// 将时间转换成分
            return unit + "分";// 在转换完的时间后增加单位
        } else if (elapsedMillis < 60000 * 60 * 24) {
            double unit = round(elapsedMillis / (60000.0 * 60));// 将时间转换成时
            return unit + "时";// 在转换完的时间后增加单位
        } else {
            double unit = round(elapsedMillis / (60000.0 * 60 * 24));// 将时间转换成天
            return unit + "天";// 在转换完的时间后增加单位
        }
    }
    
    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        System.out.println("程序开始运行时间：" + begin);
        for (int i = 0; i < 1000000000; i++) {
            Math.random();
        }
        long end = System.currentTimeMillis();
        System.out.println("程序结束运行时间：" + end);
        System.out.println("程序运行时间：" + getElapsedText(end - begin));
    }
}
