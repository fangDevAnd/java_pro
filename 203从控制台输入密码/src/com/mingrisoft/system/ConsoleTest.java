package com.mingrisoft.system;

import java.io.Console;
import java.util.Arrays;

public class ConsoleTest {
    public static void main(String[] args) {
        Console console = System.console();// 获得Console对象
        String username = console.readLine("请输入用户名："); // 获得用户名
        char[] password = console.readPassword("请输入密码："); // 获得密码
        System.out.println("您的用户名是：" + username);// 输出用户名
        System.out.print("您的密码是：");
        for (char c : password) {
            System.out.print(c);// 输出密码
        }
        Arrays.fill(password, 'a');// 将保存密码的数组元素全部复制为’a’
    }
}
