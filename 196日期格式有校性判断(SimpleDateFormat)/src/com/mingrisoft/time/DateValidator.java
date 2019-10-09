package com.mingrisoft.time;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;

public class DateValidator extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1397183659288755891L;
    private JPanel contentPane;
    private JTextField dateTextField;
    private JTextField formatTextField;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DateValidator frame = new DateValidator();
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
    public DateValidator() {
        setTitle("\u65E5\u671F\u683C\u5F0F\u6821\u9A8C\u5668");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 200);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(3, 1, 5, 5));
        
        JPanel datePanel = new JPanel();
        contentPane.add(datePanel);
        
        JLabel dateLabel = new JLabel("\u8F93\u5165\u65E5\u671F\uFF1A");
        dateLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        datePanel.add(dateLabel);
        
        dateTextField = new JTextField();
        dateTextField.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        datePanel.add(dateTextField);
        dateTextField.setColumns(15);
        
        JPanel formatPanel = new JPanel();
        contentPane.add(formatPanel);
        
        JLabel formatLabel = new JLabel("\u8F93\u5165\u683C\u5F0F\uFF1A");
        formatLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        formatPanel.add(formatLabel);
        
        formatTextField = new JTextField();
        formatTextField.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        formatPanel.add(formatTextField);
        formatTextField.setColumns(15);
        
        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel);
        
        JButton button = new JButton("\u6821\u9A8C");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        button.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        buttonPanel.add(button);
    }
    
    protected void do_button_actionPerformed(ActionEvent e) {
        String date = dateTextField.getText();// 获得日期
        String format = formatTextField.getText();// 获得格式
        if (date.length() == 0 || format.length() == 0) {
            JOptionPane.showMessageDialog(this, "日期或格式不能为空", "", JOptionPane.WARNING_MESSAGE);// 如果日期或格式为空则提示用户输入
            return;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);// 创建指定格式的formatter
        try {
            formatter.parse(date);// 利用指定的格式解析date对象
        } catch (ParseException pe) {
            JOptionPane.showMessageDialog(this, "日期格式不能匹配", "", JOptionPane.WARNING_MESSAGE);// 如果不匹配则提示用户不能匹配
            return;
        }
        JOptionPane.showMessageDialog(this, "日期格式相互匹配", "", JOptionPane.WARNING_MESSAGE);// 如果匹配则提示用户能匹配
        return;
    }
    
}
