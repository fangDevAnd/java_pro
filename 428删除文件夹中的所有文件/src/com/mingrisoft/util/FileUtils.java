package com.mingrisoft.util;

import java.io.File;
import java.util.List;

public class FileUtils {
    // 删除文件夹下所有文件
    public static void deleteFiles(File rootFile) {
        if (rootFile.listFiles().length == 0) {// 如果用户给定的是空文件夹就退出方法
            return;
        } else {
            File[] files = rootFile.listFiles();// 将非空文件夹转换成File数组
            for (File file : files) {
                if (file.isFile()) {
                    file.delete();// 删除指定文件夹下的所有文件
                } else {
                    if (file.listFiles().length == 0) {
                        file.delete();// 删除指定文件夹下的所有空文件夹
                    } else {
                        deleteDirectories(file);// 删除指定文件夹下的所有非空文件夹
                    }
                }
            }
        }
    }
    
    // 删除文件夹及文件夹下所有文件
    public static void deleteDirectories(File rootFile) {
        if (rootFile.isFile()) {
            rootFile.delete();// 如果给定的File对象是文件就直接删除
        } else {// 如果是一个文件夹就将其转换成File数组
            File[] files = rootFile.listFiles();
            for (File file : files) {
                deleteDirectories(file);// 如果不是空文件夹则就迭代deleteDictionary()方法
            }
            rootFile.delete();// 如果是空文件夹就直接删除
        }
        
    }
    
    // 获得指定目录下的所有文件的路径
    public static List<String> getFilePath(List<String> list, File rootFile) {
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
