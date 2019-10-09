package com.mingrisoft.oop;

public class BookTest {
    public static void main(String[] args) {
        Book book = new Book("《Java从入门到精通（第2版）》", "明日科技", 59.8);
        System.out.println("书名：" + book.getTitle());
        System.out.println("作者：" + book.getAuthor());
        System.out.println("价格：" + book.getPrice() + "元");
    }
}
