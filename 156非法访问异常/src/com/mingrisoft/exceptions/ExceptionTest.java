package com.mingrisoft.exceptions;

import java.lang.reflect.Field;

public class ExceptionTest {
    public static void main(String[] args) {
        Class<?> clazz = String.class;// 获得代表String类的类对象
        Field[] fields = clazz.getDeclaredFields();// 获得String类的所有域
        for (Field field : fields) {// 遍历所有域
            if (field.getName().equals("hash")) {// 如果域的名字是hash
                try {
                    System.out.println(field.getInt("hash"));// 输出hash的值
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
