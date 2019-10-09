package com.mingrisoft.reflection;

import java.awt.Container;
import java.util.TreeSet;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Test {
    public static void main(String[] args) {
        TreeSet<Class<?>> treeSet = new TreeSet<Class<?>>(new ClassComparator());
        System.out.println("向树集中添加JPanel.class");
        treeSet.add(JPanel.class);// 向树集中添加JPanel.class
        System.out.println("向树集中添加JComponent.class");
        treeSet.add(JComponent.class);// 向树集中添加JComponent.class
        System.out.println("向树集中添加Container.class");
        treeSet.add(Container.class);// 向树集中添加Container.class
        System.out.print("获得树集的最后一个元素：");
        System.out.println(treeSet.last());// 获得树集的最后一个元素
    }
}
