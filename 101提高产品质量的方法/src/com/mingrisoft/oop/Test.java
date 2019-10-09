package com.mingrisoft.oop;

public class Test {
    public static void main(String[] args) {
        Box box = new Box();
        System.out.println("将箱子的长度设置成-1");
        box.setLength(-1);
        System.out.println("将箱子的宽度设置成-1");
        box.setWidth(-1);
        System.out.println("将箱子的高度设置成-1");
        box.setHeight(-1);
        System.out.println("箱子的长度是：" + box.getLength());
        System.out.println("箱子的宽度是：" + box.getWidth());
        System.out.println("箱子的高度是：" + box.getHeight());
    }
}
