package com.mingrisoft.beanutils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.BeanComparator;

public class Test {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Employee employee1 = new Employee();// 创建employee1对象并初始化
        employee1.setId(1);
        employee1.setName("IBM");
        employee1.setSalary(10000);
        Employee employee2 = new Employee();// 创建employee2对象并初始化
        employee2.setId(2);
        employee2.setName("Oracle");
        employee2.setSalary(1000);
        Employee employee3 = new Employee();// 创建employee3对象并初始化
        employee3.setId(3);
        employee3.setName("Sun");
        employee3.setSalary(100);
        List<Employee> list = new ArrayList<Employee>();// 创建list对象并保存全部员工对象
        list.add(employee1);
        list.add(employee2);
        list.add(employee3);
        System.out.println("排序前：");
        for (Employee employee : list) {
            System.out.println(employee);// 输出所有对象
        }
        Collections.<Employee> sort(list, new BeanComparator("salary"));// 进行排序
        System.out.println("按工资排序后：");
        for (Employee employee : list) {
            System.out.println(employee);// 输出所有对象
        }
    }
}
