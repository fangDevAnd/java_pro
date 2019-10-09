package com.mingrisoft.cat;

import java.awt.Color;

public class Test {
    public static void main(String[] args) {
        Cat cat1 = new Cat("Java", 12, 21, Color.BLACK);
        Cat cat2 = new Cat("C++", 12, 21, Color.WHITE);
        Cat cat3 = new Cat("Java", 12, 21, Color.BLACK);
        System.out.println("Ã¨ßä1ºÅµÄ¹şÏ£Âë£º" + cat1.hashCode());
        System.out.println("Ã¨ßä2ºÅµÄ¹şÏ£Âë£º" + cat2.hashCode());
        System.out.println("Ã¨ßä3ºÅµÄ¹şÏ£Âë£º" + cat3.hashCode());
        System.out.println("Ã¨ßä1ºÅÊÇ·ñÓëÃ¨ßä2ºÅÏàÍ¬£º" + cat1.equals(cat2));
        System.out.println("Ã¨ßä1ºÅÊÇ·ñÓëÃ¨ßä3ºÅÏàÍ¬£º" + cat1.equals(cat3));
    }
}
