import java.io.*;
import java.util.*;
public class UniteFile {
    byte b1[];
    FileInputStream fi1;
    FileOutputStream fo;    
public void writeFiles(List<File>  files, String fileName) {      
    try {
        fo = new FileOutputStream(fileName, true);  //根据文件保存地址创建FileOutputStream对象
        for (int i = 0; i < files.size(); i++) {    //循环遍历要复制的文件集合        
            File file =  files.get(i);  //获取集合中文件对象
            fi1 = new FileInputStream(file);    //创建FileInputStream对象
            b1 = new byte[fi1.available()];     //从流中获取字节数
            fi1.read(b1);               //读取数据
            fo.write(b1);               //向文件中写数据
        }
    } catch (Exception e) {            
        e.printStackTrace();
    }        
} 
}
