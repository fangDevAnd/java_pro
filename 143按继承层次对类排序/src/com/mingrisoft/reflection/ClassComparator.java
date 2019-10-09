package com.mingrisoft.reflection;

import java.util.Comparator;

public class ClassComparator implements Comparator<Class<?>> {
    @Override
    // 通过实现Comparator接口来实现比较功能
    public int compare(Class<?> clazz1, Class<?> clazz2) {
        if (clazz1.equals(clazz2)) {// 如果两个类对象相同则返回0
            return 0;
        }
        if (clazz1.isAssignableFrom(clazz2)) {
            return -1; // 如果clazz1所表示的类是clazz2所表示的类的父类则返回-1
        }
        if (clazz2.isAssignableFrom(clazz1)) {
            return 1; // 如果clazz1所表示的类是clazz2所表示的类的子类则返回1
        }
        throw new IllegalArgumentException("两个类之间没有关系");// 其他情况抛出异常
    }
}