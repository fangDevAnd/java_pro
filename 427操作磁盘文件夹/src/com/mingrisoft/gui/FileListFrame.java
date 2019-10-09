package com.mingrisoft.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;

public class FileListFrame extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -615665572894071265L;
    private JPanel contentPane;
    private JTextField chooseTextField;
    private JButton chooseButton;
    private JScrollPane scrollPane;
    private JTable table;
    private JProgressBar progressBar;
    private File chooseFile;
    
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
                    FileListFrame frame = new FileListFrame();
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
    public FileListFrame() {
        setTitle("\u7A97\u4F53\u52A8\u6001\u52A0\u8F7D\u78C1\u76D8\u6587\u4EF6");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent arg0) {
                do_this_windowActivated(arg0);
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        
        chooseTextField = new JTextField();
        panel.add(chooseTextField);
        chooseTextField.setColumns(13);
        
        chooseButton = new JButton("\u9009\u62E9\u6587\u4EF6\u5939");
        chooseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_chooseButton_actionPerformed(arg0);
            }
        });
        panel.add(chooseButton);
        
        progressBar = new JProgressBar();
        panel.add(progressBar);
        
        scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        table = new JTable();
        scrollPane.setViewportView(table);
    }
    
    protected void do_chooseButton_actionPerformed(ActionEvent arg0) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setMultiSelectionEnabled(false);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            chooseFile = fileChooser.getSelectedFile();// 获得用户选择的文件夹
            chooseTextField.setText(chooseFile.getAbsolutePath());// 显示用户选择的文件夹
            progressBar.setIndeterminate(true);// 设置滚动条开始滚动
            final File[] subFiles = chooseFile.listFiles();// 获得用户选择的文件夹中的所有文件（夹）
            final DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);// 清空表格
            new Thread() {// 开始新的线程
                public void run() {
                    for (int i = 0; i < subFiles.length; i++) {// 遍历用户选择的文件夹
                        if (subFiles[i].isFile()) {// 判断是否是一个文件
                            Object[] property = new Object[3];
                            property[0] = i + 1;// 保存序号
                            property[1] = subFiles[i].getName();// 保存文件名
                            property[2] = "";
                            if (subFiles[i].isHidden()) {// 判断是否是一个隐藏文件
                                property[2] = "隐藏文件";
                            }
                            model.addRow(property);// 向表格中添加记录
                            table.setModel(model);// 更新表格
                        }
                        try {
                            Thread.sleep(100);// 线程休眠0.1秒实现动态加载
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    progressBar.setIndeterminate(false);// 停止进度条滚动
                };
            }.start();
            
        }
    }
    
    protected void do_this_windowActivated(WindowEvent arg0) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(new Object[] { "序号", "文件名", "属性" });
    }
    
}
