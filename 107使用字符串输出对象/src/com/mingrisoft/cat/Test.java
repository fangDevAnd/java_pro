package com.mingrisoft.cat;

import java.awt.Color;

public class Test {
    public static void main(String[] args) {
        Cat cat1 = new Cat("Java", 12, 21, Color.BLACK);
        Cat cat2 = new Cat("C++", 12, 21, Color.WHITE);
        Cat cat3 = new Cat("Java", 12, 21, Color.BLACK);
        System.out.println("Ã¨ßä1ºÅ£º" + cat1);
        System.out.println("Ã¨ßä2ºÅ£º" + cat2);
        System.out.println("Ã¨ßä3ºÅ£º" + cat3);
    }
}
