package com.mingrisoft.oop;

public class Test {
    public static void main(String[] args) {
        System.out.println("克隆之前：");
        Address address = new Address("中国", "吉林", "长春");
        Employee employee1 = new Employee("明日科技", 12, address);
        System.out.println("员工1的信息：");
        System.out.println(employee1);
        System.out.println("克隆之后：");
        Employee employee2 = employee1.clone();
        employee2.getAddress().setState("中国");
        employee2.getAddress().setProvince("四川");
        employee2.getAddress().setCity("成都");
        employee2.setName("西南交通大学");
        employee2.setAge(114);
        System.out.println("员工2的信息：");
        System.out.println(employee2);
        System.out.println("员工1的信息：");
        System.out.println(employee1);
    }
}