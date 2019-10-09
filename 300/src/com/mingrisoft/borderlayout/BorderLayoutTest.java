package com.mingrisoft.borderlayout;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.JButton;

public class BorderLayoutTest extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -7407367246160801736L;
    private JPanel contentPane;
    
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
                    BorderLayoutTest frame = new BorderLayoutTest();
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
    public BorderLayoutTest() {
        setTitle("\u8FB9\u6846\u5E03\u5C40\u6F14\u793A");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JButton northButton = new JButton("\u5317\u65B9");
        contentPane.add(northButton, BorderLayout.NORTH);
        
        JButton westButton = new JButton("\u897F\u65B9");
        contentPane.add(westButton, BorderLayout.WEST);
        
        JButton southButton = new JButton("\u5357\u65B9");
        contentPane.add(southButton, BorderLayout.SOUTH);
        
        JButton eastButton = new JButton("\u4E1C\u65B9");
        contentPane.add(eastButton, BorderLayout.EAST);
        
        JButton centerButton = new JButton("\u4E2D\u95F4");
        contentPane.add(centerButton, BorderLayout.CENTER);
    }
    
}
