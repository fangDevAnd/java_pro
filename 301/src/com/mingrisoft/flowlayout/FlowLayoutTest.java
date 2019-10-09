package com.mingrisoft.flowlayout;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class FlowLayoutTest extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = 197766755999871168L;
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
                    FlowLayoutTest frame = new FlowLayoutTest();
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
    public FlowLayoutTest() {
        setTitle("\u6D41\u5F0F\u5E03\u5C40\u6F14\u793A");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 200);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JButton button1 = new JButton("\u6309\u94AE1");
        contentPane.add(button1);
        
        JButton button2 = new JButton("\u6309\u94AE2");
        contentPane.add(button2);
        
        JButton button3 = new JButton("\u6309\u94AE3");
        contentPane.add(button3);
        
        JButton button4 = new JButton("\u6309\u94AE4");
        contentPane.add(button4);
        
        JButton button5 = new JButton("\u6309\u94AE5");
        contentPane.add(button5);
        
        JButton button6 = new JButton("\u6309\u94AE6");
        contentPane.add(button6);
        
        JButton button7 = new JButton("\u6309\u94AE7");
        contentPane.add(button7);
        
        JButton button8 = new JButton("\u6309\u94AE8");
        contentPane.add(button8);
    }
    
}
