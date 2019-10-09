package com.mingrisoft.io;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.comparator.SizeFileComparator;

public class SizeFileComparatorTest {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        File rootFile = new File("D:\\明日科技");// 创建一个文件夹对象
        File[] files = rootFile.listFiles();// 获得该文件夹中所有文件（夹）
        System.out.println("文件（夹）的原始排序：");
        for (File file : files) {
            System.out.print(file.getName() + "\t");// 输出文件夹中文件（夹）的名称
        }
        System.out.println();
        Arrays.sort(files, SizeFileComparator.SIZE_COMPARATOR);// 对files数组进行排序
        System.out.println("文件（夹）的SIZE_COMPARATOR排序：");
        for (File file : files) {
            System.out.print(file.getName() + "\t");// 输出文件夹中文件（夹）的名称
        }
        System.out.println();
        Arrays.sort(files, SizeFileComparator.SIZE_REVERSE); // 对files数组进行排序
        System.out.println("文件（夹）的SIZE_REVERSE排序：");
        for (File file : files) {
            System.out.print(file.getName() + "\t");// 输出文件夹中文件（夹）的名称
        }
        System.out.println();
        Arrays.sort(files, SizeFileComparator.SIZE_SUMDIR_COMPARATOR);
        System.out.println("文件（夹）的SIZE_SUMDIR_COMPARATOR排序：");
        for (File file : files) {
            System.out.print(file.getName() + "\t");// 输出文件夹中文件（夹）的名称
        }
        System.out.println();
        Arrays.sort(files, SizeFileComparator.SIZE_SUMDIR_REVERSE);
        System.out.println("文件（夹）的SIZE_SUMDIR_REVERSE排序：");
        for (File file : files) {
            System.out.print(file.getName() + "\t");// 输出文件夹中文件（夹）的名称
        }
    }
}
