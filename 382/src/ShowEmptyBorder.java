import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ShowEmptyBorder extends JFrame {
    
    private JPanel contentPane;
    
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
                    ShowEmptyBorder frame = new ShowEmptyBorder();
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
    public ShowEmptyBorder() {
        setTitle("\u5B9E\u73B0\u6309\u94AE\u63A7\u4EF6\u8FB9\u6846\u7559\u767D");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 328, 336);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton button = new JButton("ÑÝÊ¾°´Å¥");
        button.setBorder(new EmptyBorder(40, 0, 0, 0));// ¶¥²¿Áô°×£º40pix
        button.setBounds(19, 106, 109, 64);
        contentPane.add(button);
        
        JButton button_1 = new JButton("ÑÝÊ¾°´Å¥");
        button_1.setBorder(new EmptyBorder(0, 40, 0, 0));// ×ó²àÁô°×£º40pix
        button_1.setBounds(177, 14, 109, 64);
        contentPane.add(button_1);
        
        JButton button_2 = new JButton("ÑÝÊ¾°´Å¥");
        button_2.setBorder(new EmptyBorder(0, 0, 0, 40));// ÓÒ²àÁô°×£º40pix
        button_2.setBounds(19, 14, 109, 64);
        contentPane.add(button_2);
        
        JButton button_3 = new JButton("ÑÝÊ¾°´Å¥");
        button_3.setBorder(new EmptyBorder(0, 0, 40, 0));// µ×²¿Áô°×£º40pix
        button_3.setBounds(177, 106, 109, 64);
        contentPane.add(button_3);
        
        JButton button_4 = new JButton("ÑÝÊ¾°´Å¥");
        button_4.setBorder(new EmptyBorder(0, 0, 40, 40));// ÓÒ²àºÍµ×²¿Áô°×£º40pix
        button_4.setBounds(19, 201, 109, 64);
        contentPane.add(button_4);
        
        JButton button_5 = new JButton("ÑÝÊ¾°´Å¥");
        button_5.setBorder(new EmptyBorder(40, 40, 0, 0));// ×ó²àºÍ¶¥²¿Áô°×£º40pix
        button_5.setBounds(177, 198, 109, 64);
        contentPane.add(button_5);
        
        JLabel lblpix = new JLabel("\u53F3\u4FA7\u7559\u767D\uFF1A40pix");
        lblpix.setHorizontalAlignment(SwingConstants.CENTER);
        lblpix.setBounds(19, 76, 109, 18);
        contentPane.add(lblpix);
        
        JLabel lblpix_4 = new JLabel(
                "\u53F3\u4FA7\u548C\u5E95\u90E8\u7559\u767D\uFF1A40pix");
        lblpix_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblpix_4.setBounds(6, 263, 134, 18);
        contentPane.add(lblpix_4);
        
        JLabel lblpix_5 = new JLabel(
                "\u5DE6\u4FA7\u548C\u9876\u90E8\u7559\u767D\uFF1A40pix");
        lblpix_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblpix_5.setBounds(161, 263, 145, 18);
        contentPane.add(lblpix_5);
        
        JLabel lblpix_1 = new JLabel("\u5DE6\u4FA7\u7559\u767D\uFF1A40pix");
        lblpix_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblpix_1.setBounds(177, 76, 109, 18);
        contentPane.add(lblpix_1);
        
        JLabel lblpix_2 = new JLabel("\u9876\u90E8\u7559\u767D\uFF1A40pix");
        lblpix_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblpix_2.setBounds(19, 171, 109, 18);
        contentPane.add(lblpix_2);
        
        JLabel lblpix_3 = new JLabel("\u5E95\u90E8\u7559\u767D\uFF1A40pix");
        lblpix_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblpix_3.setBounds(177, 171, 109, 18);
        contentPane.add(lblpix_3);
        
    }
}
