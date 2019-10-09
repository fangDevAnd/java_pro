package com.mingrisoft.proxy;

import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
        Seller seller = new HouseSeller();
        System.out.println("不适用代理方法");
        seller.sell();// ��ͨ��ʽ����sell()����
        System.out.println("ʹ使用代理方法");
        ClassLoader loader = Test.class.getClassLoader();// ���Seller����������
        seller = (Seller) Proxy.newProxyInstance(loader, new Class[] { Seller.class }, new Agency());
        seller.sell();// ����ʽ����sell()����
    }
}
