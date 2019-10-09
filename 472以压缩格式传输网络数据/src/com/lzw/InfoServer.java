package com.lzw;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class InfoServer {
    
    /**
     * 处理Socket连接的线程
     * 
     * @author 李钟尉
     */
    private static final class SocketThread extends Thread {
        private static final String TEXT_FILE_PATH = "/com/textFile/";
        private final Socket socket;
        
        private SocketThread(Socket socket) {
            this.socket = socket;
        }
        
        public void run() {
            try {
                // 创建Socket输入流扫描器
                final Scanner scanner = new Scanner(socket.getInputStream());
                // 创建存放文本文件的文件夹对象
                File dir = new File(getClass().getResource(TEXT_FILE_PATH)
                        .toURI());
                String[] files = dir.list();// 获取文件列表数组
                ObjectOutputStream dout = new ObjectOutputStream(socket
                        .getOutputStream());// 创建对象输出流
                dout.writeObject(files);// 把文件列表数组输出到socket
                while (scanner.hasNext()) {// 遍历socket输入流的扫描器数据
                    String line = scanner.nextLine();// 读取一行文本
                    InputStream is = getClass().getResourceAsStream(
                            TEXT_FILE_PATH + line);// 加载文本文件输入流
                    ZipOutputStream zout = new ZipOutputStream(socket
                            .getOutputStream());// 创建socket的ZIP输出流
                    byte[] data = new byte[1024];// 创建数据缓冲
                    int readNum;
                    // 为ZIP输出流添加一个压缩条目
                    zout.putNextEntry(new ZipEntry("one"));
                    while (is != null && (readNum = is.read(data)) > 0) {
                        zout.write(data, 0, readNum);// 向ZIP流写数据
                    }
                    zout.closeEntry();// 关闭压缩条目
                    is.close();// 关闭文件输入流
                }
                scanner.close();// 关闭输入流扫描器
                socket.close();// 关闭socket
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(1598);// 创建socket服务器对象
        System.out.println("服务器已经启动。。。。");// 输出提示信息
        while (!ss.isClosed()) {
            final Socket socket = ss.accept();
            new SocketThread(socket).start();
        }
    }
}
