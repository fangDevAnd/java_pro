package com.mingrisoft.io;

import java.io.File;

import org.apache.commons.io.filefilter.SizeFileFilter;

public class SizeFileFilterTest {
    public static void main(String[] args) {
        File dir = new File("d:\\明日科技");// 创建一个文件夹对象
        System.out.println("过滤前文件夹中的文件：");
        File[] files = dir.listFiles();// 获得该文件夹中所有文件和子文件夹
        for (File file : files) {// 输出文件夹中文件的名字和大小
            System.out.println(file.getName() + "的大小是：" + file.length());
        }
        System.out.println("过滤后文件夹中的文件：");
        String[] fileNames = dir.list(new SizeFileFilter(1024 * 1024));// 过滤掉<1M的文件
        for (int i = 0; i < fileNames.length; i++) {
            System.out.println(fileNames[i]);
        }
    }
}
