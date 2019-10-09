package com.lzw;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;

public class MouseZoomButton extends JFrame {
    
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
                    MouseZoomButton frame = new MouseZoomButton();
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
    public MouseZoomButton() {
        setTitle("鼠标经过时按钮放大效果");// 设置窗体标题
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 449, 241);// 设置窗体大小和位置
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        // 创建问题标签控件
        JLabel label = new JLabel("<html><body align=center>你是否喜欢使用Java"
                + "语言来<br>编写应用程序？</body></html>");
        label.setHorizontalAlignment(SwingConstants.CENTER);// 标签文本居中
        label.setFont(new Font("SansSerif", Font.PLAIN, 32));
        label.setBounds(6, 6, 421, 106);
        contentPane.add(label);
        JButton button = new JButton("喜欢");// 创建按钮控件
        MouseAdapter mouseAdapter = new MouseAdapter() {// 创建鼠标事件监听器
            private Rectangle sourceRec;// 创建矩形对象
            
            @Override
            public void mouseEntered(MouseEvent e) {
                JButton button = (JButton) e.getSource();// 获取事件源按钮
                sourceRec = button.getBounds();// 保存按钮大小
                button.setBounds(sourceRec.x - 10, sourceRec.y - 10,
                        sourceRec.width + 20, sourceRec.height + 20);// 把按钮放大
                super.mouseEntered(e);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                JButton button = (JButton) e.getSource();// 获取事件源按钮
                if (sourceRec != null) {// 如果有备份矩形则用它恢复按钮大小
                    button.setBounds(sourceRec);// 设置按钮大小
                }
                super.mouseExited(e);
            }
            
        };
        button.addMouseListener(mouseAdapter);// 为按钮添加事件监听器
        button.setBounds(59, 145, 90, 30);// 设置按钮大小
        contentPane.add(button);
        JButton button_1 = new JButton("不喜欢");// 创建按钮控件
        button_1.setBounds(259, 145, 90, 30);// 色绘制按钮初始大小
        button_1.addMouseListener(mouseAdapter);// 为按钮添加事件监听器
        contentPane.add(button_1);
    }
}
