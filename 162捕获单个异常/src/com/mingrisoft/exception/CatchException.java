package com.mingrisoft.exception;

public class CatchException {
    public static void main(String[] args) {
        try {// 定义try块
            System.out.println("进入try块");
            @SuppressWarnings("unused")
            Class<?> clazz = Class.forName("");// 得到一个空的Class对象
            System.out.println("离开try块");
        } catch (ClassNotFoundException e) {// 定义catch块
            System.out.println("进入catch块");
            e.printStackTrace();
            System.out.println("离开catch块");
        } finally {// 定义finally块
            System.out.println("进入finally块");
        }
    }
}
