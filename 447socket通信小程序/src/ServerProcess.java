import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServerProcess {
    private SocketManager socketMan = new SocketManager(); // 创建SocketManager对象    
    void getServer() { // 创建套接字方法
        try {
            ServerSocket serverSocket = new ServerSocket(7777); // 实例化ServerSocket对象
            System.out.println("服务器套接字已创建");
            while (true) {
                Socket socket = serverSocket.accept(); // 等待连接
                new write_Thread(socket).start(); // 启动线程
                socketMan.add(socket); // 调用添加套接字方法
                socketMan.sendClientConut(); // 将当前连接数输出              
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    class write_Thread extends Thread { // 内部类，在新线程中将用户输入内容输出
        Socket socket = null; // 创建Socket对象
        private BufferedReader reader; // 创建BufferedReader对象
        private PrintWriter writer; // 创建PrintWriter对象        
        public write_Thread(Socket socket) { // 构造方法
            this.socket = socket;
        }        
        public void run() {
            try {
                reader = new BufferedReader(new InputStreamReader(socket
                        .getInputStream()));
                writer = new PrintWriter(socket.getOutputStream(), true);
                String msg;
                while ((msg = reader.readLine()) != null) {
                    socketMan.writeAll(msg); // 将客户端输出信息写入流中
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }    
    public static void main(String[] args) { // 主方法
        ServerProcess server = new ServerProcess(); // 创建本类对象
        server.getServer();
    }
}
