import java.io.*;
import java.net.*;
import java.util.*;

public class SocketManager extends ArrayList {
    synchronized void add(Socket socket) { // 添加连接套接字方法
        super.add(socket);
    }
    
    synchronized void delete(Socket socket) { // 删除套接字方法
        super.remove(socket);
    }
    
    synchronized void sendClientConut() { // 输出当前聊天人数
        String info = "在线人数为：" + size();
        try {
            File file = new File("c://count.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true))); // 创建BufferedWriter对象
            String dates = String.format("%tF %<tT", new Date()); // 对日期进行格式化
            out.write(dates + ":在线人数为" + size()); // 向文件中写内容
            out.newLine(); // 新建一行
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(info);
        writeAll(info);
    }
    
    synchronized void writeAll(String str) { // 使用套接字输出流，输出信息
        PrintWriter writer = null;
        Socket socket;
        for (int i = 0; i < size(); i++) { // 循环遍历套接字集合
            socket = (Socket) get(i); // 获取指定套接字
            try {
                writer = new PrintWriter(socket.getOutputStream(), true); // 创建输出流
                if (writer != null)
                    writer.println(str); // 通过输出流写入信息
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
