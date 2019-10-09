package com.lzw;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.UIManager;

public class BusyButton extends JFrame {
    
    private JPanel contentPane;
    
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
                    BusyButton frame = new BusyButton();
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
    public BusyButton() {
        setTitle("忙碌的按钮控件");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 370, 219);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        JLabel label = new JLabel("你相信缘分吗？");// 创建标签
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.PLAIN, 24));// 设置标签字体
        label.setBounds(6, 32, 347, 66);
        contentPane.add(label);
        JButton button = new JButton("非常相信");// 创建按钮
        button.setBounds(50, 120, 90, 42);
        contentPane.add(button);
        JButton button_1 = new JButton("鬼才信呢");// 创建忙碌按钮
        // 设置按钮的鼠标光标为忙碌状态
        button_1.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        button_1.setBounds(207, 120, 90, 42);
        contentPane.add(button_1);
    }
    
}
