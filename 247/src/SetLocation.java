import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.text.NumberFormat;

public class SetLocation extends JFrame {
    
    private JPanel contentPane;
    private JFormattedTextField leftField;
    private JFormattedTextField topField;
    
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
                    SetLocation frame = new SetLocation();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        File file = new File("ac.txt");
    }
    
    /**
     * Create the frame.
     */
    public SetLocation() {
        setTitle("\u8BBE\u7F6E\u7A97\u4F53\u5728\u5C4F\u5E55\u4E2D\u7684\u4F4D\u7F6E");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 295, 157);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel label = new JLabel("\u5DE6\u8FB9\u8DDD\uFF1A");
        label.setBounds(6, 12, 55, 18);
        contentPane.add(label);
        
        leftField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        leftField.setBounds(69, 6, 204, 30);
        contentPane.add(leftField);
        leftField.setColumns(10);
        
        JLabel label_1 = new JLabel("\u4E0A\u8FB9\u8DDD\uFF1A");
        label_1.setBounds(6, 42, 55, 18);
        contentPane.add(label_1);
        
        topField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        topField.setBounds(69, 36, 204, 30);
        contentPane.add(topField);
        topField.setColumns(10);
        
        JButton button = new JButton("\u8BBE\u7F6E");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        button.setBounds(97, 83, 90, 30);
        contentPane.add(button);
    }
    
    protected void do_button_actionPerformed(ActionEvent e) {
        Object value = leftField.getValue();// 获取左边距坐标文本
        Object value2 = topField.getValue();// 获取上边距坐标文本
        if (value == null || value2 == null)
            return;
        int left = ((Number) value).intValue();// 提取左边距坐标值
        int top = ((Number) value2).intValue();// 提取上边距坐标值
        setLocation(left, top);// 用左边距和上边距坐标值设置窗体位置
    }
}
