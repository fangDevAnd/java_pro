package com.lzw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.ListSelectionModel;


public class RadioList extends JFrame {
    
    private JPanel contentPane;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RadioList frame = new RadioList();
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
    public RadioList() {
        setTitle("列表控件折行显示列表项");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 323, 294);
        contentPane = new JPanel();
        contentPane.setOpaque(false);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        final String[] values = new String[15];// 创建列表项数组
        for (int i = 0; i < values.length; i++) {
            values[i] = "选项" + (i + 1);
        }
        JList list = new JList(values);// 创建列表控件
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(-1);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// 列表项单选
        list.setSelectedIndex(0);// 设置默认选择状态的选项
        list.setFixedCellHeight(30);// 设置列表项的固定高度
        ListCellRenderer renderer = new ListCellRenderer() {// 创建渲染器实现
            JRadioButton radio = new JRadioButton();// 创建单选按钮控件
            Color background = new Color(0, 0, 0, 0);// 创建透明的背景色
            
            @Override
            public Component getListCellRendererComponent(final JList list,
                    Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                radio.setBackground(background);// 设置单选按钮控件的背景色
                radio.setOpaque(true);// 使单选按钮不透明
                if (value.equals(values[index])) {
                    radio.setText(value + "");// 设置单选按钮文本
                }
                radio.setSelected(isSelected);
                return radio;// 返回单选按钮控件作为渲染控件
            }
        };
        list.setCellRenderer(renderer);// 设置列表控件的渲染器
        scrollPane.setViewportView(list);// 把列表控件添加到滚动面板
    }
}
