import java.io.*;

public class EncryptFile {
    // 文件简单加密方法
public void encry(String frontFile, String backFile) {
    try {
        File f = new File(frontFile);   //根据加密文件地址创建文件对象
        FileInputStream fileInputStream = new FileInputStream(f);   //创建FileInputStream对象
        byte[] buffer = new byte[fileInputStream.available()];  //从流中读取字节
        fileInputStream.read(buffer);       //从流中读取字节
        fileInputStream.close();            //把输出流关闭
        for (int i = 0; i < buffer.length; i++) {   //循环遍历从流中读取的数组
            int ibt = buffer[i];
            ibt += 100;                     //将数组中数据做相加运算
            ibt %= 256;
            buffer[i] = (byte) ibt;
        }
        FileOutputStream fileOutputStream = new FileOutputStream(new File(
                backFile));                 //根据加密后文件保存地址创建输出流对象
        fileOutputStream.write(buffer, 0, buffer.length);   //向输出流中写数据
        fileOutputStream.close();   //将流关闭
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    // 文件简单解密方法

public void unEncry(String frontFile, String backFile) {
    try {
        File f = new File(frontFile);               //创建要解压的文件对象
        FileInputStream fileInputStream = new FileInputStream(f);   //创建文件输入流对象
        byte[] buffer = new byte[fileInputStream.available()];  //从流中获取可读的字节数
        fileInputStream.read(buffer);          //从流中读取字节
        fileInputStream.close();                //关闭流
        for (int i = 0; i < buffer.length; i++) {   
            int ibt = buffer[i];    
            ibt -= 100;             //对从流中读取的数据进行运算处理
            ibt %= 256;
            buffer[i] = (byte) ibt;
        }
        FileOutputStream fileOutputStream = new FileOutputStream(new File(backFile));   //根据要写入的文件地址创建输出流
        fileOutputStream.write(buffer, 0, buffer.length);       //向输出流中写数据
        fileOutputStream.close();       //将流关闭
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
}
