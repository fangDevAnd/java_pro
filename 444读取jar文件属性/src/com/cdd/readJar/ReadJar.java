package com.cdd.readJar;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
public class ReadJar {

static List process(String fileName){
    List list = new ArrayList();        //创建List集合对象
    try {            
        JarFile jarFile = new JarFile(fileName);    //创建JarFile对象
        Enumeration en = jarFile.entries();            
        while(en.hasMoreElements()){            //测试枚举中是否包含更多的元素
            FileName file = new FileName();     //定义JavaBean对象
            JarEntry entry = (JarEntry)en.nextElement();    //获取集合中的元素
            String name = entry.getName();      //获取文件名称
            long size = entry.getSize();         //获取文件大小     
            file.setName(name);                 
            file.setSize(size+"");
            list.add(file);                     //将对象添加到集合中
        }
    } catch (Exception e) {           
        e.printStackTrace();
    }        
    return list;
}
}
