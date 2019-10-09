package com.mingrisoft.jlist;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class SearchList extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = 2294503152841317673L;
    private JPanel contentPane;
    private JTextField textField;
    private JList list;
    private DefaultListModel model = new DefaultListModel();
    
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
                    SearchList frame = new SearchList();
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
    public SearchList() {
        setTitle("\u67E5\u627E\u529F\u80FD\u7684\u5B9E\u73B0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.SOUTH);
        
        JLabel label = new JLabel("\u8F93\u5165\u5173\u952E\u5B57\uFF1A");
        label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        panel.add(label);
        
        textField = new JTextField();
        textField.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        panel.add(textField);
        textField.setColumns(10);
        
        JButton button = new JButton("\u67E5\u627E");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        button.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        panel.add(button);
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        list = new JList();
        list.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        scrollPane.setViewportView(list);
        
        model.addElement("《Java从入门到精通（第2版）》");
        model.addElement("《PHP从入门到精通（第2版）》");
        model.addElement("《Visual Basic从入门到精通（第2版）》");
        model.addElement("《Visual C++从入门到精通（第2版）》");
        model.addElement("《Java编程词典》");
        model.addElement("《细说Java》");
        model.addElement("《视频学Java》");
        list.setModel(model);
    }
    
    protected void do_button_actionPerformed(ActionEvent e) {
        String key = textField.getText();
        if ((key == null) || (key.trim().isEmpty())) {
            JOptionPane.showMessageDialog(this, "请输入关键字", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (model.contains(key)) {
            int index = model.indexOf(key);
            list.setSelectedIndex(index);
        } else {
            list.clearSelection();
            JOptionPane.showMessageDialog(this, "未找到关键字", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
    }
}
