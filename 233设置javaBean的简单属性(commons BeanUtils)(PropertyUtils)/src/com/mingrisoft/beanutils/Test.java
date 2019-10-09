package com.mingrisoft.beanutils;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;

public class Test {
    public static void main(String[] args) {
        Employee employee = new Employee();// 获得一个Employee对象
        // 获得Employee对象的属性值，由于事先并未对其赋值，所以应该为空
        String name = employee.getName();
        String phoneNumber = employee.getPhoneNumber()[0];
        String address = employee.getAddress().get("home");
        // 输出刚获得的属性值
        System.out.println("设置属性值之前：");
        System.out.println("name属性：" + name);
        System.out.println("phoneNumber属性的第一个值：" + phoneNumber);
        System.out.println("address属性home键所对应的值：" + address);
        try {// 使用PropertyUtils类的相关方法对Employee对象的域赋值
            PropertyUtils.setSimpleProperty(employee, "name", "明日科技");
            PropertyUtils.setIndexedProperty(employee, "phoneNumber", 0, "1234567");
            PropertyUtils.setMappedProperty(employee, "address", "home", "中国");
            // 获得Employee对象的属性值，由于刚刚对其赋值，所以应该为不空
            name = (String) PropertyUtils.getSimpleProperty(employee, "name");
            phoneNumber = (String) PropertyUtils.getIndexedProperty(employee, "phoneNumber", 0);
            address = (String) PropertyUtils.getMappedProperty(employee, "address", "home");
            // 输出刚获得的属性值
            System.out.println("设置属性值之后：");
            System.out.println("name属性：" + name);
            System.out.println("phoneNumber属性的第一个值：" + phoneNumber);
            System.out.println("address属性home键所对应的值：" + address);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
