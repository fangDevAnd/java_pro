package com.lzw.ip;

import java.awt.Component;
import java.awt.Dimension;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.swtdesigner.FocusTraversalOnArray;

public class IpField extends JPanel {
    private CText textField;
    private CText textField_1;
    private CText textField_2;
    private CText textField_3;
    
    /**
     * Create the panel.
     */
    public IpField() {
        setPreferredSize(new Dimension(141, 25));// 设置控件初始首选大小
        setBorder(UIManager.getBorder("TextField.border"));// 采用文本框默认的边框
        setBackground(UIManager.getColor("TextField.background"));// 采用文本框默认的背景色
        setSize(200, 25);// 初始大小
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));// 设置布局管理器
        textField = new CText();// 创建自定义文本框
        add(textField);// 添加文本框到面板
        JLabel label = new JLabel(".");// 创建IP分隔符的标签控件
        add(label);
        textField_1 = new CText();// 创建自定义文本框
        add(textField_1);// 添加文本框到面板
        JLabel label_3 = new JLabel(".");// 创建IP分隔符的标签控件
        add(label_3);
        textField_2 = new CText();// 创建自定义文本框
        add(textField_2);
        JLabel label_2 = new JLabel(".");// 创建IP分隔符的标签控件
        add(label_2);
        textField_3 = new CText();// 创建自定义文本框
        add(textField_3);// 添加文本框到面板
        setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]
            { textField, textField_1, textField_2, textField_3 }));
    }
    
    public String getIpString() {// 编写获取IP字符串值的方法
        String ipstr = textField + "." + textField_1 + "." + textField_2 + "."
                + textField_3;// 把4个文本框的值连接为IP地址字符串
        return ipstr;
    }
    
    public InetAddress getIpAddress() {// 编写获取IP对象的方法
        InetAddress ia = null;// 创建一个空的IP地址对象
        try {
            ia = InetAddress.getByName(getIpString());// 把字符串转换为IP地址对象
        } catch (UnknownHostException e) {
            e.printStackTrace();// 处理异常
        }
        return ia;// 返回地址对象
    }
}
