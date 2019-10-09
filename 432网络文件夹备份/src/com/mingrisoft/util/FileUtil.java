package com.mingrisoft.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
    public static void copySingleFile(File source, File target) throws IOException {
        FileInputStream input = new FileInputStream(source);// 获得输入流
        FileOutputStream output = new FileOutputStream(target);// 获得输出流
        byte[] b = new byte[1024 * 5];
        int length;
        while ((length = input.read(b)) != -1) {// 利用循环读取输入流中的全部数据
            output.write(b, 0, length);// 将输入流中的内容写入到输出流中
        }
        output.flush();// 刷新输出流
        output.close();// 释放输出流资源
        input.close();// 释放输入流资源
    }
    
    public static void copyDirectory(File source, File target) throws IOException {
        File[] files = source.listFiles();// 将源文件夹转换成File数组
        for (File file : files) {
            if (file.isFile()) {// 如果是一个文件就调用复制文件的方法
                copySingleFile(file, new File(target.getAbsolutePath() + "/" + file.getName()));
            } else if (file.listFiles().length == 0) {// 如果是一个空文件夹就调用创建文件夹的方法
                new File(target.getAbsolutePath() + "/" + file.getName()).mkdir();
            } else {// 如果是一个非空文件夹就调用自身，进行迭代
                new File(target.getAbsolutePath() + "/" + file.getName()).mkdir();
                copyDirectory(file, new File(target.getAbsolutePath() + "/" + file.getName()));
            }
        }
    }
    
}
