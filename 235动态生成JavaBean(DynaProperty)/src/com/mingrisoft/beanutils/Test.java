package com.mingrisoft.beanutils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;

public class Test {
    public static void main(String[] args) {
        DynaProperty[] properties = new DynaProperty[3];// 声明保存3个属性值的数组
        // 指定属性名称和类型
        properties[0] = new DynaProperty("name", String.class);
        properties[1] = new DynaProperty("phoneNumber", String[].class, String.class);
        properties[2] = new DynaProperty("address", Map.class, String.class);
        BasicDynaClass dynaClass = new BasicDynaClass("employee", null, properties);
        DynaBean employee = null;
        try {
            employee = dynaClass.newInstance();// 获得DynaBean的实例
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        // 为属性赋值
        employee.set("name", "明日科技");
        employee.set("phoneNumber", new String[10]);// 索引类型要先初始化
        employee.set("phoneNumber", 0, "1234567");
        employee.set("address", new HashMap<String, String>());// 映射类型要先初始化
        employee.set("address", "home", "中国");
        String name = (String) employee.get("name");
        String phoneNumber = (String) employee.get("phoneNumber", 0);
        String address = (String) employee.get("address", "home");
        // 输出属性值
        System.out.println("新建JavaBean的name属性：" + name);
        System.out.println("新建JavaBean的phoneNumber属性的第一个值：" + phoneNumber);
        System.out.println("新建JavaBean的address属性home键所对应的值：" + address);
    }
}
