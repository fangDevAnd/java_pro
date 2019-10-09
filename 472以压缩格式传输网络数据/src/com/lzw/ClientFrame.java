package com.lzw;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.zip.ZipInputStream;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.UIManager;

public class ClientFrame extends JFrame {
    
    private JPanel contentPane;
    private Socket socket;
    private JTextField hostField;
    private JList list;
    private JTextArea infoArea;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClientFrame frame = new ClientFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame.
     */
    public ClientFrame() {
        setTitle("\u4EE5\u538B\u7F29\u683C\u5F0F\u4F20\u8F93\u7F51\u7EDC\u6570\u636E");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 673, 399);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(10, 10));
        setContentPane(contentPane);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        panel.setLayout(new BorderLayout(0, 0));
        
        JLabel label = new JLabel("\u670D\u52A1\u5668\uFF1A");
        panel.add(label, BorderLayout.WEST);
        
        hostField = new JTextField();
        hostField.setText("127.0.0.1");
        panel.add(hostField);
        hostField.setColumns(10);
        
        JButton linkButton = new JButton("\u8FDE\u63A5");
        linkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_linkButton_actionPerformed(e);
            }
        });
        panel.add(linkButton, BorderLayout.EAST);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        infoArea = new JTextArea();
        infoArea.setLineWrap(true);
        scrollPane.setViewportView(infoArea);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setPreferredSize(new Dimension(160, 100));
        contentPane.add(scrollPane_1, BorderLayout.WEST);
        
        list = new JList();
        scrollPane_1.setViewportView(list);
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                do_list_valueChanged(e);
            }
        });
    }
    
    /**
     * 列表控件的选择事件处理方法
     * 
     * @param e
     */
    protected void do_list_valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting())
            return;
        Object value = list.getSelectedValue();
        if (value == null)
            return;
        String bookName = value.toString();
        infoArea.setText("");
        try {
            // 获取socket的输出流
            OutputStream outputStream = socket.getOutputStream();
            // 向socket发送信息
            outputStream.write((bookName + "\n").getBytes());
            // 创建ZIP输入流
            ZipInputStream zis = new ZipInputStream(socket.getInputStream());
            char[] data = new char[1024];// 缓冲数组
            int readNum;
            zis.getNextEntry();// 读取下一个ZIP条目
            // 把ZIP二进制输入流转换为字符输入流
            InputStreamReader ir = new InputStreamReader(zis);
            while ((readNum = ir.read(data)) > 0) {// 读取数据
                // 把数据添加到文本域控件
                infoArea.append(new String(data, 0, readNum));
            }
            infoArea.select(0, 0);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    
    /**
     * 连接按钮的时间处理方法
     * 
     * @param e
     */
    protected void do_linkButton_actionPerformed(ActionEvent e) {
        try {
            String host = hostField.getText();// 获取输入的主机名或地址
            socket = new Socket(host, 1598);// 创建socket连接
            final ObjectInputStream ois = new ObjectInputStream(socket
                    .getInputStream());// 获取socket的对象输入流
            list.setModel(new AbstractListModel() {// 设置JList的数据模型
                        // 获取socket传递的数组对象，作为列表控件的数据
                        String[] values = (String[]) ois.readObject();
                        
                        public int getSize() {
                            return values.length;
                        }
                        
                        public Object getElementAt(int index) {
                            return values[index];
                        }
                    });
        } catch (UnknownHostException e1) {// 捕获未知主机异常
            JOptionPane.showMessageDialog(null, "输入的主机无法连接");
            return;
        } catch (SocketException e1) {// 捕获socket异常
            JOptionPane.showMessageDialog(null, "输入的主机无法连接");
            return;
        } catch (IOException e11) {// 捕获输入输出异常
            e11.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}
