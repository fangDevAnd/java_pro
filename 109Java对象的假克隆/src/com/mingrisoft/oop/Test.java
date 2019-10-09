package com.mingrisoft.oop;

public class Test {
    public static void main(String[] args) {
        System.out.println("克隆之前：");
        Employee employee1 = new Employee();
        employee1.setName("明日科技");
        employee1.setAge(12);
        System.out.println("员工1的信息：");
        System.out.println(employee1);
        System.out.println("克隆之后：");
        Employee employee2 = employee1;
        employee2.setName("西南交通大学");
        employee2.setAge(114);
        System.out.println("员工2的信息：");
        System.out.println(employee2);
        System.out.println("员工1的信息：");
        System.out.println(employee1);
    }
}
