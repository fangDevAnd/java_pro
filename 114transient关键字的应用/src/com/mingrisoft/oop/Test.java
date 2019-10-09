package com.mingrisoft.oop;

public class Test {
    
    public static void main(String[] args) {
        Login login1 = new Login("mingrisoft", "mr");
        System.out.println("输出原始对象的信息：");
        System.out.println(login1);
        System.out.println("输出克隆对象的信息：");
        Login login2 = login1.clone();
        System.out.println(login2);
    }
}
