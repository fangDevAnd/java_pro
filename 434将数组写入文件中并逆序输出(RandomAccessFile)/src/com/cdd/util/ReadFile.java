package com.cdd.util;
import java.io.*;
public class ReadFile {
    
public static void main(String args[]){
    int bytes[]={1,2,3,4,5};    //定义写入文件的数组
    try {
        //创建RandomAccessFile类的对象
        File file = new File("Example9.txt");
        if(!file.exists()){             //判断该文件是否存在
            file.createNewFile();       //新建文件
        }
        RandomAccessFile raf=new RandomAccessFile(file,"rw");   //定义RandomAccessFile对象
        for(int i=0;i<bytes.length;i++){        //循环遍历数组
            raf.writeInt(bytes[i]);             //将数组写入文件
        }
        System.out.println("逆序输出信息");
        for(int i=bytes.length-1;i>=0;i--){     //反向遍历数组
            raf.seek(i*4);                      //int型数据占4个字节
            System.out.println(+raf.readInt());
        }
        raf.close();                    //关闭流
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
