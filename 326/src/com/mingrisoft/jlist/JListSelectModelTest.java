package com.mingrisoft.jlist;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class JListSelectModelTest extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -1203078038045289207L;
    private JPanel contentPane;
    private JList list1;
    private JList list2;
    private JList list3;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    
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
                    JListSelectModelTest frame = new JListSelectModelTest();
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
    public JListSelectModelTest() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                do_this_windowActivated(e);
            }
        });
        setTitle("\u5217\u8868\u6846\u9009\u62E9\u6A21\u5F0F");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(1, 3, 5, 5));
        
        JScrollPane scrollPane1 = new JScrollPane();
        panel.add(scrollPane1);
        
        list1 = new JList();
        list1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane1.setViewportView(list1);
        
        label1 = new JLabel("\u5355\u9879\u9009\u62E9\u5217\u8868");
        label1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        scrollPane1.setColumnHeaderView(label1);
        
        JScrollPane scrollPane2 = new JScrollPane();
        panel.add(scrollPane2);
        
        list2 = new JList();
        list2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
        list2.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        scrollPane2.setViewportView(list2);
        
        label2 = new JLabel("\u8FDE\u7EED\u9009\u62E9\u5217\u8868");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
        scrollPane2.setColumnHeaderView(label2);
        
        JScrollPane scrollPane3 = new JScrollPane();
        panel.add(scrollPane3);
        
        list3 = new JList();
        list3.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
        scrollPane3.setViewportView(list3);
        
        label3 = new JLabel("\u9ED8\u8BA4\u9009\u62E9\u5217\u8868");
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
        scrollPane3.setColumnHeaderView(label3);
    }
    
    protected void do_this_windowActivated(WindowEvent e) {
        String[] listData = new String[12];
        for(int i=0;i<listData.length;i++) {
            listData[i] = "Ã÷ÈÕ¿Æ¼¼"+(i+1);
        }
        list1.setListData(listData);
        list2.setListData(listData);
        list3.setListData(listData);
    }
}
