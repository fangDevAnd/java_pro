package com.mingrisoft.oop;

public class PersonTest {
    public static void main(String[] args) {
        Person person1 = new Person();
        Person person2 = new Person("明日科技", "男", 11);
        System.out.println("员工1的信息");
        System.out.println("员工姓名：" + person1.getName());
        System.out.println("员工性别：" + person1.getGender());
        System.out.println("员工年龄：" + person1.getAge());
        System.out.println("员工2的信息");
        System.out.println("员工姓名：" + person2.getName());
        System.out.println("员工性别：" + person2.getGender());
        System.out.println("员工年龄：" + person2.getAge());
    }
}
