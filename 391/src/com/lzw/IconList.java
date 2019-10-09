package com.lzw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

public class IconList extends JFrame {
    
    private JPanel contentPane;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    IconList frame = new IconList();
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
    public IconList() {
        setTitle("支持图标的列表控件");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 323, 294);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        final String[] values = new String[] { "西瓜", "吃剩的苹果", "香蕉", "玉米", "葡萄",
                "菠萝", "西红柿" };// 创建列表项数组
        final ImageIcon[] icons = new ImageIcon[values.length];// 创建图标数组
        for (int i = 0; i < icons.length; i++) {// 遍历图标数组
            icons[i] = new ImageIcon(getClass().getResource(
                    "/res/" + i + ".png"));// 初始化每一个数组元素
        }
        JList list = new JList(values);// 创建列表控件
        list.setSelectedIndex(0);
        ListCellRenderer renderer = new ListCellRenderer() {// 创建渲染器实现
            JLabel label = new JLabel();// 创建标签控件
            Color background = new Color(0, 0, 0, 0);// 创建透明的背景色
            
            @Override
            public Component getListCellRendererComponent(final JList list,
                    Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                label.setBackground(background);// 设置标签控件的背景色
                label.setOpaque(true);// 使标签不透明
                if (value.equals(values[index])) {
                    label.setText(value + "");// 设置标签文本
                    label.setIcon(icons[index]);// 设置标签图标
                }
                if (isSelected) {
                    label.setBackground(Color.PINK);// 设置选择时的背景色
                } else {
                    label.setBackground(background);// 设置未选择时的背景色
                }
                return label;// 返回标签控件作为渲染控件
            }
        };
        list.setCellRenderer(renderer);// 设置列表控件的渲染器
        scrollPane.setViewportView(list);// 把列表控件添加到滚动面板
    }
}
