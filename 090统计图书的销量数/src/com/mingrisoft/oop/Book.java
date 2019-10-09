package com.mingrisoft.oop;

public class Book {
    
    private static int counter = 0;
    
    public Book(String title) {
        System.out.println("ÊÛ³öÍ¼Êé£º" + title);
        counter++;
    }
    
    public static int getCounter() {
        return counter;
    }
}
