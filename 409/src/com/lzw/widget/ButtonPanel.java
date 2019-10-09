package com.lzw.widget;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;

public class ButtonPanel extends JPanel {
    
    /**
     * Create the panel.
     */
    public ButtonPanel() {
        setLayout(new GridLayout(1, 0, 0, 0));
        
        JButton button = new JButton("登录模块");
        add(button);
        
        JButton button_1 = new JButton("邮件管理");
        add(button_1);
        
        JButton button_2 = new JButton("网站维护");
        add(button_2);
        
        JButton button_3 = new JButton("新闻模块");
        add(button_3);
        
        JButton button_4 = new JButton("数据备份");
        add(button_4);
        
        JButton button_5 = new JButton("系统设置");
        add(button_5);
        
        JButton button_6 = new JButton("数据管理");
        add(button_6);
        
        JButton button_7 = new JButton("网络连接");
        add(button_7);
        
        JButton button_8 = new JButton("服务管理");
        add(button_8);
        
    }
    
}
