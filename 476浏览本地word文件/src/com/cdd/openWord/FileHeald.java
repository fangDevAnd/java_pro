package com.cdd.openWord;

import java.io.*;
import java.text.*;
import java.util.*;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class FileHeald {
    
    private Dispatch doc;
    private ActiveXComponent word; // word运行程序对象
    private Dispatch documents; // 所有word文档集合
    
    public FileHeald() {
        if (word == null) {
            word = new ActiveXComponent("Word.Application"); // 启动word
            word.setProperty("Visible", new Variant(true)); // 设置word为可视状态
        }
        if (documents == null)
            documents = word.getProperty("Documents").toDispatch();// 读取属性值
    }
    
    // 读取整个文件夹内容
    public List getFileList(String strPath) {
        LinkedList filelist = new LinkedList();
        File dir = new File(strPath);
        File[] file = dir.listFiles();
        if ((file != null) && file.length > 0) {
            for (int i = 0; i < file.length; i++) {
                if (file[i].isDirectory()) {
                    getFileList(file[i].getAbsolutePath());
                } else {
                    filelist.add(file[i]);
                }
            }
        }
        return filelist;
    }
    
    // 获取磁盘所有文件方法
    public List getList(String path) {
        LinkedList<File> list = new LinkedList<File>();
        ArrayList<String> listPath = new ArrayList<String>();
        File dir = new File(path);
        File file[] = dir.listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isDirectory())
                list.add(file[i]);
            else {
                listPath.add(file[i].getAbsolutePath());
            }
        }
        File tmp;
        while (!list.isEmpty()) {
            tmp = list.removeFirst(); // 移除并返回集合中第一项
            if (tmp.isDirectory()) {
                file = tmp.listFiles();
                if (file == null)
                    continue;
                for (int i = 0; i < file.length; i++) {
                    if (file[i].isDirectory())
                        list.add(file[i]);
                    else {
                        listPath.add(file[i].getAbsolutePath());
                    }
                    
                }
            } else {
                
            }
        }
        return listPath;
    }    

    /**
     * 打开一个已存在的文档
     * 
     * @param docPath
     */
    
public void openDocument(String docPath) {  
    doc = Dispatch.call(documents, "Open", docPath).toDispatch(); // 调用打开word文档命令        
}

}
