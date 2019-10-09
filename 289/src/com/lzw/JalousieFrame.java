package com.lzw;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JalousieFrame extends JFrame {
    private JTextField textField;
    private JTextField textField_1;
    private JPanel contentPane;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JalousieFrame frame = new JalousieFrame();
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
    public JalousieFrame() {
        setTitle("窗体百叶窗登场特效");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 452, 221);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(135, 206, 250));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel label = new JLabel("\u767B\u5F55\u6A21\u5757");
        label.setOpaque(true);
        label.setBackground(new Color(245, 222, 179));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.PLAIN, 30));
        label.setBounds(6, 6, 422, 72);
        contentPane.add(label);
        
        JLabel label_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
        label_1.setBounds(31, 90, 55, 18);
        contentPane.add(label_1);
        
        JLabel label_2 = new JLabel("\u5BC6\u3000\u7801\uFF1A");
        label_2.setBounds(31, 134, 55, 18);
        contentPane.add(label_2);
        
        textField = new JTextField();
        textField.setBounds(83, 84, 184, 30);
        contentPane.add(textField);
        textField.setColumns(10);
        
        textField_1 = new JTextField();
        textField_1.setBounds(83, 128, 184, 30);
        contentPane.add(textField_1);
        textField_1.setColumns(10);
        
        JButton button = new JButton("\u767B\u5F55");
        button.setBounds(304, 84, 90, 30);
        contentPane.add(button);
        
        JButton button_1 = new JButton("\u5173\u95ED");
        button_1.setBounds(304, 128, 90, 30);
        contentPane.add(button_1);
        setLocationRelativeTo(null);
        JalousiePanel panel = new JalousiePanel();
    }
    
    class JalousiePanel extends JPanel {
        final int step = 30;// 百业条大体高度
        int hei = step;// 百叶条渐变高度
        int recNum = 0;// 百叶条的数量
        private Timer timer;
        
        public JalousiePanel() {
            setOpaque(false);// 面板透明
            final Component oldPanel = getGlassPane();// 保存原有玻璃面板
            final boolean visible = oldPanel.isVisible();
            setGlassPane(JalousiePanel.this);// 把当前面板设置为窗体玻璃面板
            getGlassPane().setVisible(true);// 显示玻璃面板
            // 初始化Timer控件
            timer = new Timer(300, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setGlassPane(JalousiePanel.this);// 设置当前面板为窗体玻璃面板
                    getGlassPane().setVisible(true);// 显示玻璃面板
                    if (hei-- > 0) {// 递减百叶条渐变高度
                        repaint();// 重绘界面
                    } else {// 如果百叶条高度渐变小于0
                        timer.stop();// 停止Timer控件
                        setGlassPane(oldPanel);// 恢复原有玻璃面板
                        hei = step;// 初始化百叶条高度
                        getGlassPane().setVisible(visible);// 恢复玻璃面板显示状态
                    }
                }
            });
            // 添加控件的事件监听器
            addComponentListener(new ComponentAdapter() {
                @Override
                public void componentShown(ComponentEvent e) {
                    fillJalousie();// 控件显示时调用的方法
                }
                
                private void fillJalousie() {
                    Dimension size = getSize();// 获取窗体控件大小
                    recNum = (size.height - 1) / step + 1;// 计算百叶条数量
                    timer.start();// 启动Timer控件
                }
                
                @Override
                public void componentResized(ComponentEvent e) {
                    fillJalousie();// 控件调整大小时调用的方法
                }
            });
        }
        
        @Override
        protected void paintComponent(Graphics g1) {
            Graphics2D g = (Graphics2D) g1;// 获取2D绘图对象
            g.setColor(Color.BLUE);// 设置绘图前景色
            // 设置绘图透明度
            g.setComposite(AlphaComposite.SrcOver.derive(0.5f));
            for (int i = 0; i < recNum; i++) {
                // 绘制所有百业条
                g.fillRect(0, i * step, getWidth(), hei);
            }
            super.paintComponent(g);
        }
    }
}
