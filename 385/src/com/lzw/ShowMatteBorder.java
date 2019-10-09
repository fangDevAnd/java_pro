package com.lzw;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ShowMatteBorder extends JFrame {
    
    private JPanel contentPane;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ShowMatteBorder frame = new ShowMatteBorder();
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
    public ShowMatteBorder() {
        setTitle("\u63A7\u4EF6\u7684\u7EAF\u8272\u8FB9\u6846\u4E0E\u56FE\u6807\u8FB9\u6846");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 385, 332);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        // 创建图标对象
        ImageIcon icon = new ImageIcon(getClass().getResource("icon.png"));
        
        JButton button = new JButton("演示按钮");
        // 顶部纯色填充：40pix
        button.setBorder(new MatteBorder(40, 0, 0, 0, Color.MAGENTA));
        button.setBounds(19, 98, 109, 64);
        contentPane.add(button);
        
        JButton button_1 = new JButton("演示按钮");
        // 左侧图标填充：40pix
        button_1.setBorder(new MatteBorder(0, 40, 0, 0, icon));
        button_1.setBounds(217, 6, 109, 64);
        contentPane.add(button_1);
        
        JButton button_2 = new JButton("演示按钮");
        // 右侧纯色填充：40pix
        button_2.setBorder(new MatteBorder(0, 0, 0, 40, Color.ORANGE));
        button_2.setBounds(19, 6, 109, 64);
        contentPane.add(button_2);
        
        JButton button_3 = new JButton("演示按钮");
        // 底部图标填充：40pix
        button_3.setBorder(new MatteBorder(0, 0, 40, 0, icon));
        button_3.setBounds(217, 98, 109, 64);
        contentPane.add(button_3);
        
        JButton button_4 = new JButton("演示按钮");
        // 右侧和底部纯色填充：40pix
        button_4.setBorder(new MatteBorder(0, 0, 40, 40, Color.YELLOW));
        button_4.setBounds(19, 193, 109, 64);
        contentPane.add(button_4);
        
        JButton button_5 = new JButton("演示按钮");
        // 左侧和顶部图标填充：40pix
        button_5.setBorder(new MatteBorder(40, 40, 0, 0, icon));
        button_5.setBounds(217, 190, 109, 64);
        contentPane.add(button_5);
        
        JLabel lblpix = new JLabel(
                "\u53F3\u4FA7\u7EAF\u8272\u586B\u5145\uFF1A40pix");
        lblpix.setHorizontalAlignment(SwingConstants.CENTER);
        lblpix.setBounds(19, 68, 122, 18);
        contentPane.add(lblpix);
        
        JLabel lblpix_2 = new JLabel(
                "\u53F3\u4FA7\u548C\u5E95\u90E8\u7EAF\u8272\u586B\u5145\uFF1A40pix");
        lblpix_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblpix_2.setBounds(6, 255, 154, 18);
        contentPane.add(lblpix_2);
        
        JLabel lblpix_3 = new JLabel(
                "\u5DE6\u4FA7\u548C\u9876\u90E8\u56FE\u6807\u586B\u5145\uFF1A40pix");
        lblpix_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblpix_3.setBounds(202, 255, 160, 18);
        contentPane.add(lblpix_3);
        
        JLabel lblpix_4 = new JLabel(
                "\u5DE6\u4FA7\u56FE\u6807\u586B\u5145\uFF1A40pix");
        lblpix_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblpix_4.setBounds(217, 68, 122, 18);
        contentPane.add(lblpix_4);
        
        JLabel lblpix_1 = new JLabel(
                "\u9876\u90E8\u7EAF\u8272\u586B\u5145\uFF1A40pix");
        lblpix_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblpix_1.setBounds(19, 163, 122, 18);
        contentPane.add(lblpix_1);
        
        JLabel lblpix_5 = new JLabel(
                "\u5E95\u90E8\u56FE\u6807\u586B\u5145\uFF1A40pix");
        lblpix_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblpix_5.setBounds(217, 163, 122, 18);
        contentPane.add(lblpix_5);
    }
}
