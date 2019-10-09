package com.mingrisoft.jframe;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class BackgroundImage extends JFrame {
    private static final long serialVersionUID = -7734031908388740823L;
    
    public BackgroundImage() {
        ImageIcon background = new ImageIcon("src/image/mingri.jpg");
        JLabel label = new JLabel(background);
        label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
        JPanel panel = (JPanel) getContentPane();
        panel.setOpaque(false);
        panel.setLayout(new FlowLayout());
        panel.add(new JButton("±à³Ì´Êµä"));
        getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("¿ò¼ÜÈÝÆ÷µÄ±³¾°Í¼Æ¬");
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {
            
            public void run() {
                BackgroundImage image = new BackgroundImage();
                image.setVisible(true);
            }
        });
    }
}
