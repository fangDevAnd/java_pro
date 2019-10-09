package com.mingrisoft.oop;

import java.util.Scanner;

public class TemperatureConverter {
    
    public double toFahrenheit(double centigrade) {
        double fahrenheit = 1.8 * centigrade + 32;
        return fahrenheit;
    }
    
    public static void main(String[] args) {
        System.out.println("请输入要转换的温度（单位：摄氏度）");
        Scanner in = new Scanner(System.in);
        double centigrade = in.nextDouble();
        TemperatureConverter tc = new TemperatureConverter();
        double fahrenheit = tc.toFahrenheit(centigrade);
        System.out.println("转换完成的温度（单位：华氏度）：" + fahrenheit);
    }
}
