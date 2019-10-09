package com.cdd.util;
import java.io.*;
public class Employ {
public static void main(String args[]) {
    File file = new File("Example8.txt");
    try {
        if (!file.exists())                      // 如果文件不存在
            file.createNewFile();                // 创建新文件
        InputStreamReader isr = new InputStreamReader(System.in);   //定义输入流对象
        BufferedReader br = new BufferedReader(isr);            
        System.out.println("请输入：");
        String str = br.readLine();             //读取用户输入的信息
        System.out.println("您输入的内容是：" + str);
        FileWriter fos = new FileWriter(file, true);         // 创建文件输出流
        BufferedWriter bw = new BufferedWriter(fos);
        bw.write(str);                          //向文件写入信息
        br.close();
        bw.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
