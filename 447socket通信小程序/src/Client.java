

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends JFrame implements Runnable {
	private JPanel jpanel = new JPanel(); // 定义面板对象
	private JLabel nameLabel = new JLabel("姓名："); // 标签对象
	private JTextField nameField = new JTextField(); // 文本框对象
	private JTextArea msgArea = new JTextArea(); // 文本域对象
	private JTextField sendField = new JTextField();
	private JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
	private BufferedReader reader; // 创建BufferedReader类对象
	private PrintWriter writer;
	private Socket socket; // 创建套接字对象
	public Client(String title) {
		super(title);
		this.setSize(360, 340); // 定义窗体大小
		this.add(jpanel);
		jpanel.setLayout(null); // 窗体布局
		msgArea.setEditable(false); // msgView对象为不可编译状态
		jpanel.add(nameLabel); // 将标签添加到面板上
		nameLabel.setBounds(10, 10, 60, 20); // 设置布局
		jpanel.add(nameField);
		nameField.setBounds(60, 10, 270, 21);
		jpanel.add(sendField);
		sendField.setBounds(10, 270, 320, 21);
		msgArea.setColumns(20);
		msgArea.setRows(5);
		jScrollPane1.setViewportView(msgArea);
		jpanel.add(jScrollPane1);
		jScrollPane1.setBounds(10, 40, 320, 220);
		sendField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				writer.println(nameField.getText() + ":" + sendField.getText()); // 将用户名、用户输入信息写如流中
				sendField.setText(""); // 将发送文本框中内容清空
			}
		});
	}
	@Override
	public void run() {
		while (true) {
			try {
				msgArea.append(reader.readLine() + "\n"); // 在文本域中将读取内容分行显示
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	void getSocket() { // 创建套接字方法
		msgArea.append("尝试与服务器连接");
		try {
			socket = new Socket("127.0.0.1", 7777); // 创建客户端套接字对象
			msgArea.append("聊天准备完毕"); // 文本域中信息
			reader = new BufferedReader(new InputStreamReader(socket
					.getInputStream())); // 实例化BufferedReader对象
			writer = new PrintWriter(socket.getOutputStream(), true); // 实例化PrintWriter对象
			new Thread(this).start(); // 启动线程
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Client client = new Client("迷你聊天屋");
		client.setVisible(true); // 设置窗体为可见
		client.getSocket(); // 调用创建套接字方法
	}
}
