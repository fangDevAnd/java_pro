package com.lzw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.JProgressBar;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

public class VolumeCompress extends JFrame {
    
    /**
     * 执行压缩任务的西南侧
     * 
     * @author 李钟尉
     */
private final class CompressThread extends Thread {
    public void run() {
        try {
            // 获取表格控件的数据模型
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int rowCount = model.getRowCount();// 获取数据模型中表格行数
            StringBuilder fileList = new StringBuilder();
            for (int i = 0; i < rowCount; i++) {// 遍历数据表格模型中的文件对象
                File file = (File) model.getValueAt(i, 2);
                fileList.append(file.getPath() + "\n");// 把文件路径存到字符串构建器中
            }
            // 创建临时文件，用于保存压缩文件列表
            File listFile = File.createTempFile("fileList", ".tmp");
            FileOutputStream fout = new FileOutputStream(listFile);
            fout.write(fileList.toString().getBytes());// 保存字符串构建器数据到临时文件
            fout.close();
            int volumeSize = ((Number) volumetField.getValue()).intValue();
            // 创建压缩命令字符串
            final String command = "rar a -v"+volumeSize+"k " + rarFile.getPath() + " @"
                    + listFile.getPath();
            Runtime runtime = Runtime.getRuntime();// 获取Runtime对象
            progress = runtime.exec(command.toString() + "\n");// 执行压缩命令
            progress.getOutputStream().close();// 关闭进程输出流
            progressBar.setString(null);// 初始化进度条控件
            progressBar.setValue(0);
            // 获取进程输入流
            Scanner scan = new Scanner(progress.getInputStream());
            while (scan.hasNext()) {
                String line = scan.nextLine();// 获取进程提示单行信息
                // 获取提示信息的进度百分比的索引位置
                int index = line.lastIndexOf("%") - 3;
                if (index <= 0)
                    continue;
                // 获取进度百分比字符串
                String substring = line.substring(index, index + 3);
                // 获取整数的百分比数值
                int percent = Integer.parseInt(substring.trim());
                progressBar.setValue(percent);// 在进度条控件显示百分比
            }
            progressBar.setString("完成");
            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    
    private JPanel contentPane;
    private JTable table;
    private JPanel panel;
    private JButton addButton;
    private JButton removeButton;
    private JPanel panel_1;
    private JLabel label;
    private JTextField compressFileField;
    private JButton browseButton;
    private File rarFile;
    private JButton compressButton;
    private JProgressBar progressBar;
    private JButton stopButton;
    private Process progress;
    private JLabel lblkb;
    private JFormattedTextField volumetField;
    
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
                    VolumeCompress frame = new VolumeCompress();
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
    public VolumeCompress() {
        setTitle("\u6587\u4EF6\u5206\u5377\u538B\u7F29");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
                "\u6587\u4EF6\u540D\u79F0", "\u6587\u4EF6\u5927\u5C0F",
                "\u6587\u4EF6\u8DEF\u5F84" }));
        table.getColumnModel().getColumn(0).setPreferredWidth(125);
        table.getColumnModel().getColumn(2).setPreferredWidth(250);
        table.getTableHeader().setReorderingAllowed(false);
        scrollPane.setViewportView(table);
        scrollPane.getViewport().setBackground(Color.WHITE);
        
        panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.SOUTH);
        GridBagLayout gbl_panel_1 = new GridBagLayout();
        gbl_panel_1.columnWidths = new int[] { 0, 60, 0, 0 };
        gbl_panel_1.rowHeights = new int[] { 0, 17, 0 };
        gbl_panel_1.columnWeights = new double[] { 0.0, 1.0, 0.0,
                Double.MIN_VALUE };
        gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
        panel_1.setLayout(gbl_panel_1);
        
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        GridBagConstraints gbc_progressBar = new GridBagConstraints();
        gbc_progressBar.gridwidth = 3;
        gbc_progressBar.insets = new Insets(0, 0, 5, 5);
        gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
        gbc_progressBar.gridx = 0;
        gbc_progressBar.gridy = 0;
        panel_1.add(progressBar, gbc_progressBar);
        
        label = new JLabel("\u538B\u7F29\u6587\u6863\uFF1A");
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.fill = GridBagConstraints.HORIZONTAL;
        gbc_label.insets = new Insets(0, 0, 0, 5);
        gbc_label.gridx = 0;
        gbc_label.gridy = 1;
        panel_1.add(label, gbc_label);
        
        compressFileField = new JTextField();
        compressFileField.setEditable(false);
        GridBagConstraints gbc_compressFileField = new GridBagConstraints();
        gbc_compressFileField.insets = new Insets(0, 0, 0, 5);
        gbc_compressFileField.fill = GridBagConstraints.HORIZONTAL;
        gbc_compressFileField.gridx = 1;
        gbc_compressFileField.gridy = 1;
        panel_1.add(compressFileField, gbc_compressFileField);
        compressFileField.setColumns(10);
        
        browseButton = new JButton("\u6D4F\u89C8");
        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_browseButton_actionPerformed(arg0);
            }
        });
        GridBagConstraints gbc_browseButton = new GridBagConstraints();
        gbc_browseButton.gridx = 2;
        gbc_browseButton.gridy = 1;
        panel_1.add(browseButton, gbc_browseButton);
        
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(120, 10));
        contentPane.add(panel, BorderLayout.WEST);
        panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{52, 0, 0};
        gbl_panel.rowHeights = new int[]{30, 30, 30, 30, 0, 0};
        gbl_panel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);
        
        addButton = new JButton("\u589E\u52A0");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_addButton_actionPerformed(arg0);
            }
        });
        GridBagConstraints gbc_addButton = new GridBagConstraints();
        gbc_addButton.anchor = GridBagConstraints.WEST;
        gbc_addButton.insets = new Insets(0, 0, 5, 5);
        gbc_addButton.gridx = 0;
        gbc_addButton.gridy = 0;
        panel.add(addButton, gbc_addButton);
        
        removeButton = new JButton("\u79FB\u9664");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_removeButton_actionPerformed(arg0);
            }
        });
        GridBagConstraints gbc_removeButton = new GridBagConstraints();
        gbc_removeButton.anchor = GridBagConstraints.WEST;
        gbc_removeButton.insets = new Insets(0, 0, 5, 0);
        gbc_removeButton.gridx = 1;
        gbc_removeButton.gridy = 0;
        panel.add(removeButton, gbc_removeButton);
        
        compressButton = new JButton("\u538B\u7F29");
        compressButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_compressButton_actionPerformed(arg0);
            }
        });
        GridBagConstraints gbc_compressButton = new GridBagConstraints();
        gbc_compressButton.anchor = GridBagConstraints.WEST;
        gbc_compressButton.insets = new Insets(0, 0, 5, 5);
        gbc_compressButton.gridx = 0;
        gbc_compressButton.gridy = 1;
        panel.add(compressButton, gbc_compressButton);
        
        stopButton = new JButton("\u505C\u6B62");
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_stopButton_actionPerformed(arg0);
            }
        });
        GridBagConstraints gbc_stopButton = new GridBagConstraints();
        gbc_stopButton.insets = new Insets(0, 0, 5, 0);
        gbc_stopButton.anchor = GridBagConstraints.WEST;
        gbc_stopButton.gridx = 1;
        gbc_stopButton.gridy = 1;
        panel.add(stopButton, gbc_stopButton);
        
        lblkb = new JLabel("\u5206\u5377\uFF1A\uFF08KB\uFF09");
        GridBagConstraints gbc_lblkb = new GridBagConstraints();
        gbc_lblkb.anchor = GridBagConstraints.WEST;
        gbc_lblkb.gridwidth = 2;
        gbc_lblkb.insets = new Insets(0, 0, 5, 0);
        gbc_lblkb.gridx = 0;
        gbc_lblkb.gridy = 2;
        panel.add(lblkb, gbc_lblkb);
        
        volumetField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        volumetField.setValue(1024);
        GridBagConstraints gbc_volumetField = new GridBagConstraints();
        gbc_volumetField.insets = new Insets(0, 0, 5, 0);
        gbc_volumetField.gridwidth = 2;
        gbc_volumetField.fill = GridBagConstraints.HORIZONTAL;
        gbc_volumetField.gridx = 0;
        gbc_volumetField.gridy = 3;
        panel.add(volumetField, gbc_volumetField);
    }
    
    /**
     * 增加按钮的事件处理方法
     * 
     * @param arg0
     */
    protected void do_addButton_actionPerformed(ActionEvent arg0) {
        JFileChooser chooser = new JFileChooser();// 创建文件选择器
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setMultiSelectionEnabled(true);// 设置允许文件多选
        int option = chooser.showOpenDialog(this);// 显示文件打开对话框
        if (option != JFileChooser.APPROVE_OPTION)
            return;
        File[] files = chooser.getSelectedFiles();// 获取用户选择文件数组
        // 获取表格控件的数据模型
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (File file : files) {// 遍历用户选择的文件数组
            // 把文件信息添加到表格控件的模型中
            model.addRow(new Object[] { file.getName(), file.length(), file });
        }
    }
    
    /**
     * 移除按钮的事件处理方法
     * 
     * @param arg0
     */
    protected void do_removeButton_actionPerformed(ActionEvent arg0) {
        int[] rows = table.getSelectedRows();// 获取表格中选中的行索引的数组
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = rows.length - 1; i >= 0; i--) {
            model.removeRow(rows[i]);// 遍历并移除所有选中行
        }
    }
    
    /**
     * 选择压缩RAR文档的浏览按钮的事件处理方法
     * 
     * @param arg0
     */
    protected void do_browseButton_actionPerformed(ActionEvent arg0) {
        JFileChooser chooser = new JFileChooser();// 创建文件选择器
        // 设置选择文件类型为Rar
        chooser.setFileFilter(new FileNameExtensionFilter("RAR压缩文档", "rar"));
        chooser.setAcceptAllFileFilterUsed(false);
        int option = chooser.showSaveDialog(this);// 显示保存对话框
        if (option != JFileChooser.APPROVE_OPTION)
            return;
        rarFile = chooser.getSelectedFile();// 获取用户定制的RAR文件
        compressFileField.setText(rarFile.getPath());// 显示RAR文件路径信息
    }
    
    /**
     * 压缩按钮的事件处理方法
     * 
     * @param arg0
     */
    protected void do_compressButton_actionPerformed(ActionEvent arg0) {
        if (rarFile == null) {
            browseButton.doClick();
            if (rarFile == null)
                return;
        }
        progressBar.setVisible(true);
        CompressThread compressThread = new CompressThread(); // 创建压缩线程
        compressThread.start();// 启动线程
    }
    
    /**
     * 停止按钮的事件处理方法
     * 
     * @param arg0
     */
    protected void do_stopButton_actionPerformed(ActionEvent arg0) {
        if (progress != null) {
            progress.destroy();
            progressBar.setValue(0);
            progressBar.setVisible(false);
        }
    }
}
