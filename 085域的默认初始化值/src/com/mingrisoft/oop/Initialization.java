package com.mingrisoft.oop;

public class Initialization {
    private byte b;
    private short s;
    private int i;
    private long l;
    private float f;
    private double d;
    private boolean bl;
    private char c;
    private String string;
    
    public static void main(String[] args) {
        Initialization init = new Initialization();
        System.out.println("比特类型的初始值：" + init.b);
        System.out.println("短整型类型的初始值：" + init.s);
        System.out.println("整型类型的初始值：" + init.i);
        System.out.println("长整型类型的初始值：" + init.l);
        System.out.println("单精度浮点类型的初始值：" + init.f);
        System.out.println("双精度浮点类型的初始值：" + init.d);
        System.out.println("布尔类型的初始值：" + init.bl);
        System.out.println("字符类型的初始值：" + init.c);
        System.out.println("引用类型的初始值：" + init.string);
    }
}
