package com.mingrisoft.generic;

public class StackTest {
    public static void main(String[] args) {
        Stack stack = new Stack();
        System.out.println("向栈中增加字符串：");
        System.out.println("视频学Java");
        System.out.println("细说Java");
        System.out.println("Java从入门到精通(第2版)");
        stack.push("视频学Java");
        stack.push("细说Java");
        stack.push("Java从入门到精通(第2版)");
        System.out.println("从栈中取出字符串：");
        while (!stack.empty()) {
            System.out.println((String) stack.pop());
        }
    }
}
