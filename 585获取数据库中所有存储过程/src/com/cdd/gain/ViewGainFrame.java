package com.cdd.gain;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ViewGainFrame extends JFrame {
    
    private JPanel contentPane;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewGainFrame frame = new ViewGainFrame();
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
    public ViewGainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setTitle("获取数据库中所有存储过程");
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 434, 262);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel massageLabel = new JLabel("获取db_database24数据库下的所有存储过程名：");
        massageLabel.setFont(new Font("华文琥珀", Font.PLAIN, 13));
        massageLabel.setBounds(65, 20, 297, 27);
        panel.add(massageLabel);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(54, 71, 316, 159);
        panel.add(scrollPane);
        
        JTextArea textArea = new JTextArea();        
        scrollPane.setViewportView(textArea);
        GainProcedure procedure = new GainProcedure(); // 创建工具类对象
        List list = procedure.executeGain(); // 调用获取所有存储过程方法
        for (int i = 0; i < list.size(); i++) { // 循环遍历查询结果
            String name = list.get(i).toString(); // 获取结果集中数据
            textArea.append(name + "\n"); // 向文本域中添加信息
        }
    }
}
