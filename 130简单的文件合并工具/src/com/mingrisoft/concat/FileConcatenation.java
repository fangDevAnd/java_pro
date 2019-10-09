package com.mingrisoft.concat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class FileConcatenation extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = 5592010207848128980L;
    private JPanel contentPane;
    private JTextField pathTextField;
    private JTable table;
    private int id;
    private List<FileInputStream> files = new ArrayList<FileInputStream>();
    
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
                    FileConcatenation frame = new FileConcatenation();
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
    public FileConcatenation() {
        addWindowListener(new WindowAdapter() {
            public void windowActivated(WindowEvent e) {
                do_this_windowActivated(e);
            }
        });
        setTitle("\u6587\u4EF6\u5408\u5E76\u5DE5\u5177");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JPanel choosePanel = new JPanel();
        contentPane.add(choosePanel, BorderLayout.NORTH);
        
        JLabel label = new JLabel("\u65B0\u6587\u4EF6\u7684\u7EDD\u5BF9\u8DEF\u5F84\uFF1A");
        label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        choosePanel.add(label);
        
        pathTextField = new JTextField();
        pathTextField.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        choosePanel.add(pathTextField);
        pathTextField.setColumns(10);
        
        JButton chooseButton = new JButton("\u589E\u52A0\u6587\u4EF6");
        chooseButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        chooseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_chooseButton_actionPerformed(e);
            }
        });
        choosePanel.add(chooseButton);
        
        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        JButton concatButton = new JButton("\u5408\u5E76\u6587\u4EF6");
        concatButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        concatButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_concatButton_actionPerformed(e);
            }
        });
        buttonPanel.add(concatButton);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        table = new JTable();
        table.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        table.setRowHeight(30);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        header.setPreferredSize(new Dimension(header.getWidth(), 35));
        scrollPane.setViewportView(table);
    }
    
    protected void do_chooseButton_actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                files.add(new FileInputStream(file));
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.addRow(new Object[] { ++id, file.getName() });
        }
    }
    
    protected void do_concatButton_actionPerformed(ActionEvent e) {
        String fileName = pathTextField.getText();
        if (fileName.length() == 0) {
            JOptionPane.showMessageDialog(this, "请输入新文件的绝对路径", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (files.size() == 0) {
            JOptionPane.showMessageDialog(this, "请选择要合并的文件", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        SequenceInputStream sis = new SequenceInputStream(Collections.enumeration(files));
        BufferedInputStream bis = new BufferedInputStream(sis);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(fileName);
            int length;
            while ((length = bis.read()) != -1) {
                out.write(length);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        JOptionPane.showMessageDialog(this, "文件合并成功！", "", JOptionPane.WARNING_MESSAGE);
    }
    
    protected void do_this_windowActivated(WindowEvent e) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(new Object[] { "序号", "文件名" });
        table.setModel(model);
    }
}
