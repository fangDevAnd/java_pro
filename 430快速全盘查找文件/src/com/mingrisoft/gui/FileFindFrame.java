package com.mingrisoft.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Font;
import javax.swing.UIManager;

public class FileFindFrame extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -8720909398161703242L;
    private JPanel contentPane;
    private JTextField chooseTextField;
    private JTextField searchTextField;
    private File chooseFile;
    private JTextArea resultTextArea;
    
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
                    FileFindFrame frame = new FileFindFrame();
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
    public FileFindFrame() {
        setTitle("\u5FEB\u901F\u5168\u76D8\u67E5\u627E\u6587\u4EF6");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel choosePanel = new JPanel();
        contentPane.add(choosePanel, BorderLayout.NORTH);
        
        JLabel chooseLabel = new JLabel("\u9009\u62E9\u7D22\u5F15\u6587\u4EF6\uFF1A");
        chooseLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        choosePanel.add(chooseLabel);
        
        chooseTextField = new JTextField();
        chooseTextField.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        choosePanel.add(chooseTextField);
        chooseTextField.setColumns(10);
        
        JButton chooseButton = new JButton("\u9009\u62E9\u6587\u4EF6");
        chooseButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        chooseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_chooseButton_actionPerformed(arg0);
            }
        });
        choosePanel.add(chooseButton);
        
        JPanel searchPanel = new JPanel();
        contentPane.add(searchPanel, BorderLayout.SOUTH);
        
        JLabel searchLabel = new JLabel("\u8F93\u5165\u5173\u952E\u5B57\uFF1A");
        searchLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        searchPanel.add(searchLabel);
        
        searchTextField = new JTextField();
        searchTextField.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        searchPanel.add(searchTextField);
        searchTextField.setColumns(10);
        
        JButton searchButton = new JButton("\u5F00\u59CB\u67E5\u627E");
        searchButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_searchButton_actionPerformed(arg0);
            }
        });
        searchPanel.add(searchButton);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        resultTextArea = new JTextArea();
        resultTextArea.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        scrollPane.setViewportView(resultTextArea);
    }
    
    protected void do_chooseButton_actionPerformed(ActionEvent arg0) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("文本文件", "txt"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(false);
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            chooseFile = fileChooser.getSelectedFile();
            chooseTextField.setText(chooseFile.getAbsolutePath());
        }
    }
    
    protected void do_searchButton_actionPerformed(ActionEvent arg0) {
        if (chooseFile == null) {
            JOptionPane.showMessageDialog(this, "请选择索引文件", null, JOptionPane.WARNING_MESSAGE);
            return;
        }
        String keyword = searchTextField.getText();// 获得用户输入的关键字
        if (keyword.length() == 0) {
            JOptionPane.showMessageDialog(this, "请输入关键字", null, JOptionPane.WARNING_MESSAGE);
            return;
        }
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(chooseFile);// 利用用户选择的文件创建FileReader对象
            bufferedReader = new BufferedReader(fileReader);
            StringBuilder builder = new StringBuilder();// 利用StringBuilder对象保存索引
            String temp = null;
            while ((temp = bufferedReader.readLine()) != null) {// 读入文本文件
                builder.append(temp);
                builder.append("\n");// 在每一行的末尾添加一个分隔符
            }
            String[] rows = builder.toString().split("\n");// 将索引按换行符分割
            resultTextArea.setText("");// 清空文本域
            for (String row : rows) {// 遍历读入的文本文件
                if (row.contains(keyword)) {// 判断读入的文本文件是否包含指定的关键字
                    resultTextArea.append(row + "\n");// 返回结果
                }
            }
            if (resultTextArea.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "没有找到您需要的文件", null, JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
