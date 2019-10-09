import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.Option;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.UIManager;

public class FileSelectDialog extends JFrame {
    
    private JPanel contentPane;
    private JTextField textField;
    
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
                    FileSelectDialog frame = new FileSelectDialog();
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
    public FileSelectDialog() {
        setTitle("\u6587\u4EF6\u9009\u62E9\u5BF9\u8BDD\u6846\u6307\u5B9A\u6570\u636E\u5907\u4EFD");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 133);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel label = new JLabel(
                "\u8BF7\u6307\u5B9A\u6570\u636E\u5907\u4EFD\u6587\u4EF6");
        label.setFont(new Font("SansSerif", Font.PLAIN, 24));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(6, 6, 422, 30);
        contentPane.add(label);
        
        JLabel label_1 = new JLabel("\u6587\u4EF6\u8DEF\u5F84\uFF1A");
        label_1.setBounds(16, 57, 69, 18);
        contentPane.add(label_1);
        
        textField = new JTextField();
        textField.setBounds(80, 51, 259, 30);
        contentPane.add(textField);
        textField.setColumns(10);
        
        JButton button = new JButton("\u6D4F\u89C8");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        button.setBounds(338, 51, 90, 30);
        contentPane.add(button);
    }
    
    protected void do_button_actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();// 创建文件选择器
        int option = chooser.showOpenDialog(this);// 显示文件打开对话框
        if (option == JFileChooser.APPROVE_OPTION) {// 判断用户是否选定文件
            File file = chooser.getSelectedFile();// 获取用户选择文件
            textField.setText(file.getAbsolutePath());// 把选择的文件路径显示在文本框中
        }
    }
}
