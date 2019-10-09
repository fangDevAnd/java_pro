package com.mingrisoft.jtabbedpane;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

public class ImageTabbedCard extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -3042756299881773911L;
    private JPanel contentPane;
    private JTabbedPane tabbedPane;
    
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
                    ImageTabbedCard frame = new ImageTabbedCard();
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
    public ImageTabbedCard() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                do_this_windowActivated(e);
            }
        });
        setTitle("\u5305\u542B\u56FE\u6807\u7684\u9009\u9879\u5361");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 200);
        contentPane = new JPanel();
        contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
        contentPane.add(tabbedPane, BorderLayout.CENTER);
        
        JLabel imageLabel1 = new JLabel("\u56FE\u68071");
        imageLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
        tabbedPane.addTab("", null, imageLabel1, null);
        
        JLabel imageLabel2 = new JLabel("\u56FE\u68072");
        imageLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
        tabbedPane.addTab("", null, imageLabel2, null);
        
        JLabel imageLabel3 = new JLabel("\u56FE\u68073");
        imageLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel3.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
        tabbedPane.addTab("", null, imageLabel3, null);
        
        JLabel imageLabel4 = new JLabel("\u56FE\u68074");
        imageLabel4.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel4.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
        tabbedPane.addTab("", null, imageLabel4, null);
    }
    
    protected void do_this_windowActivated(WindowEvent e) {
        tabbedPane.setIconAt(0, new ImageIcon("src/images/1.png"));
        tabbedPane.setIconAt(1, new ImageIcon("src/images/2.png"));
        tabbedPane.setIconAt(2, new ImageIcon("src/images/3.png"));
        tabbedPane.setIconAt(3, new ImageIcon("src/images/4.png"));
    }
}
