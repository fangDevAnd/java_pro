package com.cdd.triiger;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InsertTriggerFrame extends JFrame {
    
    private JPanel contentPane;
    private JTextField nameTextField;
    private JTextField mathTextField;
    private JTextField englishTextField;
    private JTextField chineseTextField;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InsertTriggerFrame frame = new InsertTriggerFrame();
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
    public InsertTriggerFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setTitle("添加学生成绩信息");
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 434, 262);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel messageLabel = new JLabel("向学生成绩表中添加数据");
        messageLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        messageLabel.setBounds(132, 22, 168, 15);
        panel.add(messageLabel);
        
        JLabel nameLabel = new JLabel("姓名：");
        nameLabel.setBounds(88, 57, 54, 15);
        panel.add(nameLabel);
        
        nameTextField = new JTextField();
        nameTextField.setBounds(132, 54, 182, 21);
        panel.add(nameTextField);
        nameTextField.setColumns(10);
        
        JLabel mathLabel = new JLabel("数学：");
        mathLabel.setBounds(88, 91, 54, 15);
        panel.add(mathLabel);
        
        mathTextField = new JTextField();
        mathTextField.setBounds(132, 88, 182, 21);
        panel.add(mathTextField);
        mathTextField.setColumns(10);
        
        JLabel lenglishLabel = new JLabel("英语：");
        lenglishLabel.setBounds(88, 124, 54, 15);
        panel.add(lenglishLabel);
        
        englishTextField = new JTextField();
        englishTextField.setBounds(132, 121, 182, 21);
        panel.add(englishTextField);
        englishTextField.setColumns(10);
        
        JLabel chineseLabel = new JLabel("语文：");
        chineseLabel.setBounds(88, 162, 54, 15);
        panel.add(chineseLabel);
        
        chineseTextField = new JTextField();
        chineseTextField.setBounds(132, 159, 182, 21);
        panel.add(chineseTextField);
        chineseTextField.setColumns(10);
        
        JButton insetButton = new JButton("添加");
        insetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_insetButton_actionPerformed(arg0);
            }
        });
        insetButton.setBounds(124, 205, 64, 23);
        panel.add(insetButton);
        
        JButton closeButton = new JButton("关闭");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_closeButton_actionPerformed(arg0);
            }
        });
        closeButton.setBounds(225, 205, 64, 23);
        panel.add(closeButton);
    }
    //添加按钮的单击处理事件
    protected void do_insetButton_actionPerformed(ActionEvent arg0) {
        String name = nameTextField.getText();
        String math = mathTextField.getText();
        String chinese = chineseTextField.getText();
        String english = englishTextField.getText();
        UserTrigger userTrigger = new UserTrigger();
        Grade grade = new Grade();
        grade.setName(name);
        grade.setEnglist(Float.parseFloat(english));
        grade.setChinese(Float.parseFloat(chinese));
        grade.setMath(Float.parseFloat(math));
        int count = userTrigger.insertGrade(grade);
        if(count > 0){
            JOptionPane.showMessageDialog(getContentPane(), 
                    "数据添加成功！", "信息提示框", JOptionPane.WARNING_MESSAGE);
        }
        if(count == 0){
            JOptionPane.showMessageDialog(getContentPane(), 
                    "无法添加数据，可能是添加的学生在学生信息表中不存在！", "警告提示框", JOptionPane.WARNING_MESSAGE);
        }
        
    }
    //关闭按钮的单击处理事件
    protected void do_closeButton_actionPerformed(ActionEvent arg0) {
        System.exit(0);
    }
}
