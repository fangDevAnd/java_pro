package com.mingrisoft.generic;

public class GenericComparisonTest {
    public static void main(String[] args) {
        String[] books = { "Java从入门到精通（第2版）", "Java编程宝典", "细说Java", "视频学Java" };
        System.out.println("明日科技新书列表：");
        for (String book : books) {
            System.out.println(book);
        }
        String min = GenericComparison.getMin(books);
        System.out.println("按名称排序的第一本书：");
        System.out.println(min);
    }
}
