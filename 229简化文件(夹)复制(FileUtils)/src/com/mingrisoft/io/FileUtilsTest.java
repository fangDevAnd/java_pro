package com.mingrisoft.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileUtilsTest {
    public static void main(String[] args) throws IOException {
        File srcDir = new File("D:\\明日科技");
        File destDir = new File("E:\\明日科技");
        List<String> list = new ArrayList<String>();
        System.out.println("复制之前文件夹中的文件：");
        getFilePath(list, destDir);
        for (String string : list) {
            System.out.println(string);// 输出复制前文件夹中所有文件
        }
        System.out.println();
        System.out.println("复制之后文件夹中的文件：");
        FileUtils.copyDirectory(srcDir, destDir);
        getFilePath(list, destDir);
        for (String string : list) {
            System.out.println(string); // 输出复制后文件夹中所有文件
        }
    }
    
    // 获得rootFile文件夹中所有文件的绝对路径并将其保存在list中
    private static List<String> getFilePath(List<String> list, File rootFile) {
        File[] files = rootFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                getFilePath(list, file);
            } else {
                list.add(file.getAbsolutePath().replace("\\", File.separator));
            }
        }
        return list;
    }
}
