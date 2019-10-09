package com.lzw;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.Font;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class LimpidityTable extends JFrame {
    
    private JPanel contentPane;
    private JTable table;
    
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
                    LimpidityTable frame = new LimpidityTable();
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
    public LimpidityTable() {
        setTitle("实现透明效果的表格控件");// 设置窗体标题
        setResizable(false);// 禁止调整大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 520, 549);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        ImgPanel imgPanel = new ImgPanel();// 创建图片面板
        contentPane.add(imgPanel, BorderLayout.CENTER);
        imgPanel.setLayout(null);// 取消布局管理器
        table = new JTable() {// 创建自定义表格
            {
                setOpaque(false);// 初始化表格为透明
                setGridColor(Color.MAGENTA);// 设置表格网格颜色
                setShowVerticalLines(true);// 显示网格竖线
                setShowHorizontalLines(true);// 显示网格横线
                setRowHeight(20);// 设置表格行高
                setBorder(new LineBorder(Color.PINK));// 设置边框
                setForeground(Color.BLACK);// 设置表格文字颜色
                setFont(new Font("SansSerif", Font.PLAIN, 18));// 设置表格单元格字体
            }
            
            @Override
            public Component prepareRenderer(TableCellRenderer renderer,
                    int row, int column) {// 重写渲染方法
                // 获取渲染后的控件
                Component component = super.prepareRenderer(renderer, row,
                        column);
                ((JComponent) component).setOpaque(false);// 设置控件透明
                return component;// 返回控件
            }
        };
        table.setModel(new DefaultTableModel(
                new Object[][] {// 初始化表格内容与列名
                { "Java", "Java", "Java", "Java", "Java" },
                        { "Java", "Java", "Java", "Java", "Java" },
                        { "Java", "Java", "Java", "Java", "Java" },
                        { "Java", "Java", "Java", "Java", "Java" },
                        { "Java", "Java", "Java", "Java", "Java" },
                        { "Java", "Java", "Java", "Java", "Java" },
                        { "Java", "Java", "Java", "Java", "Java" },
                        { "Java", "Java", "Java", "Java", "Java" },
                        { "Java", "Java", "Java", "Java", "Java" },
                        { "Java", "Java", "Java", "Java", "Java" },
                        { "Java", "Java", "Java", "Java", "Java" } },
                new String[] { "列名1", "列名2", "列名3", "列名4", "列名5" }));
        table.setBounds(40, 161, 421, 254);// 设置表格大小
        imgPanel.add(table);
        JPanel panel = new JPanel();// 创建表头面板
        panel.setLayout(new BorderLayout(0, 0));
        panel.add(table.getTableHeader(), BorderLayout.CENTER);// 添加表头
        panel.setBounds(40, 126, 421, 34);
        imgPanel.add(panel);
    }
}
