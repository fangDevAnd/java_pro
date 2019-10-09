package com.mingrisoft.gui;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.UIManager;

public class DownloadFrame extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -4265931991434336626L;
    private JPanel contentPane;
    private JTextField urlTextField;
    private JTextField pathTextField;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DownloadFrame frame = new DownloadFrame();
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
    public DownloadFrame() {
        setTitle("\u538B\u7F29\u5B58\u50A8\u7F51\u9875");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 200);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(3, 1, 0, 0));
        
        JPanel urlPanel = new JPanel();
        contentPane.add(urlPanel);
        
        JLabel urlLabel = new JLabel("\u4E0B\u8F7D\u5730\u5740\uFF1A");
        urlLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        urlPanel.add(urlLabel);
        
        urlTextField = new JTextField();
        urlTextField.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        urlPanel.add(urlTextField);
        urlTextField.setColumns(15);
        
        JPanel pathPanel = new JPanel();
        contentPane.add(pathPanel);
        
        JLabel pathLabel = new JLabel("\u4FDD\u5B58\u8DEF\u5F84\uFF1A");
        pathLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        pathPanel.add(pathLabel);
        
        pathTextField = new JTextField();
        pathTextField.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        pathPanel.add(pathTextField);
        pathTextField.setColumns(15);
        
        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel);
        
        JButton button = new JButton("\u5F00\u59CB\u4E0B\u8F7D");
        button.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_button_actionPerformed(arg0);
            }
        });
        
        JButton chooseButton = new JButton("\u9009\u62E9\u8DEF\u5F84");
        chooseButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        chooseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_chooseButton_actionPerformed(arg0);
            }
        });
        buttonPanel.add(chooseButton);
        buttonPanel.add(button);
    }
    
    protected void do_button_actionPerformed(ActionEvent arg0) {
        String url = urlTextField.getText();
        String savePath = pathTextField.getText();
        if (url.length() == 0) {
            JOptionPane.showMessageDialog(this, "请输入下载地址", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (savePath.length() == 0) {
            JOptionPane.showMessageDialog(this, "请选择保存路径", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            zipWebPage(url, savePath);
            JOptionPane.showMessageDialog(this, "下载完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    protected void do_chooseButton_actionPerformed(ActionEvent arg0) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setMultiSelectionEnabled(false);
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            pathTextField.setText(path);
        }
    }
    
    private static void zipWebPage(String url, String savePath) throws IOException {
        URLConnection conn = new URL(url).openConnection();// 利用用户输入的网址创建URL连接对象
        InputStream in = conn.getInputStream();// 获得输入流
        FileOutputStream fos = new FileOutputStream(savePath + "download.zip");
        ZipOutputStream zos = new ZipOutputStream(fos);
        byte[] buffer = new byte[1024];
        ZipEntry entry = new ZipEntry("download.html");// 创建名为“download.html”的压缩条目
        zos.putNextEntry(entry);
        int read = 0;
        while ((read = in.read(buffer)) != -1) {// 写入数据
            zos.write(buffer, 0, read);
        }
        zos.closeEntry();
        in.close();// 释放资源
        zos.close();
        fos.close();
    }
    
}
