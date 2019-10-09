package com.mingrisoft.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.mingrisoft.util.FileUtils;
import java.awt.Font;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class FileDeleteFrame extends JFrame {
    
    private JPanel contentPane;
    private JTextField chooseTextField;
    private File fileName;
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
                    FileDeleteFrame frame = new FileDeleteFrame();
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
    public FileDeleteFrame() {
        setTitle("\u5220\u9664\u6587\u4EF6\u5939\u4E2D\u6240\u6709\u6587\u4EF6");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel choosePanel = new JPanel();
        contentPane.add(choosePanel, BorderLayout.NORTH);
        
        chooseTextField = new JTextField();
        chooseTextField.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
        choosePanel.add(chooseTextField);
        chooseTextField.setColumns(18);
        
        JButton chooseButton = new JButton("\u9009\u62E9\u6587\u4EF6\u5939");
        chooseButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
        chooseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_chooseButton_actionPerformed(arg0);
            }
        });
        choosePanel.add(chooseButton);
        
        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        JButton deleteButton = new JButton("\u5F00\u59CB\u5220\u9664");
        deleteButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_deleteButton_actionPerformed(arg0);
            }
        });
        buttonPanel.add(deleteButton);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        resultTextArea = new JTextArea();
        resultTextArea.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
        resultTextArea.setText("\u5220\u9664\u4E86\u4EE5\u4E0B\u6587\u4EF6\uFF1A");
        scrollPane.setViewportView(resultTextArea);
    }
    
    protected void do_chooseButton_actionPerformed(ActionEvent arg0) {
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(false);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = chooser.showOpenDialog(this.getContentPane());
        if (result == JFileChooser.APPROVE_OPTION) {
            fileName = chooser.getSelectedFile();
            chooseTextField.setText(fileName.getAbsolutePath());
            
        }
    }
    
    protected void do_deleteButton_actionPerformed(ActionEvent arg0) {
        List<String> list = new ArrayList<String>();
        FileUtils.getFilePath(list, fileName);
        Iterator<String> iterator = list.iterator();
        StringBuilder sb = new StringBuilder();
        sb.append(resultTextArea.getText() + "\n");
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            sb.append("\n");
        }
        resultTextArea.setText(sb.toString());
        FileUtils.deleteFiles(fileName);
        
    }
}
