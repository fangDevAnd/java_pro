package com.lzw;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.lzw.login.LoginPanel;
import javax.swing.border.MatteBorder;
import java.awt.Color;

/**
 * @author 李钟尉
 */
public class LoginFrame extends JFrame {
    
    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private LoginPanel loginPanel = null;
    private JTextField textField;
    private JPasswordField passwordField;
    
    /**
     * 主类的入口方法
     * 
     * @param args
     * @throws UnsupportedLookAndFeelException
     *             不支持的外观
     */
    public static void main(String[] args)
            throws UnsupportedLookAndFeelException {
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        // 设置窗体外观
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // 创建登录窗体
                LoginFrame loginFrame = new LoginFrame();
                // 显示登录窗体
                loginFrame.setVisible(true);
            }
        });
    }
    
    /**
     * 登录窗体的构造方法
     */
    public LoginFrame() {
        super();
        setTitle("使用图片制作绚丽按钮");
        // 设置窗体内容面板
        jContentPane = new JPanel();
        // 设置布局管理器
        jContentPane.setLayout(new BorderLayout());
        loginPanel = new LoginPanel();
        loginPanel.setLayout(null);
        JButton loginButton = new JButton();
        loginButton.setBounds(266, 81, 68, 68);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        // 设置按钮图标
        loginButton.setIcon(new ImageIcon(getClass().getResource(
                "/com/lzw/logBut1.png")));
        loginButton.setContentAreaFilled(false);
        // 设置按钮按下动作的图标
        loginButton.setPressedIcon(new ImageIcon(getClass().getResource(
                "/com/lzw/logBut2.png")));
        // 设置鼠标经过按钮的图标
        loginButton.setRolloverIcon(new ImageIcon(getClass().getResource(
                "/com/lzw/logBut3.png")));
        // 添加按钮事件监听器
        loginPanel.add(loginButton);// 添加登录按钮
        
        textField = new JTextField();// 创建文本框
        textField.setBounds(94, 81, 155, 30);
        loginPanel.add(textField);// 添加文本框到窗体
        
        passwordField = new JPasswordField();// 创建密码框
        passwordField.setBounds(94, 113, 155, 30);
        loginPanel.add(passwordField);// 添加密码框到窗体
        // 添加登录面板到内容面板
        jContentPane.add(loginPanel, BorderLayout.CENTER);
        this.setContentPane(jContentPane);
        // 设置窗体大小
        setSize(new Dimension(513, 248));// 调用初始化界面的方法
        setLocationRelativeTo(null);// 窗体居中
    }
}
