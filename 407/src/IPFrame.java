import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import com.lzw.ip.IpField;
import javax.swing.UIManager;

public class IPFrame extends JFrame {
    
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
                    IPFrame frame = new IPFrame();
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
    public IPFrame() {
        setTitle("IP输入文本框控件");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 355, 222);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        JLabel label = new JLabel("设置服务器名称与IP地址");// 创建标题标签
        label.setHorizontalAlignment(SwingConstants.CENTER);// 居中对齐
        label.setFont(new Font("SansSerif", Font.PLAIN, 18));// 设置字体
        label.setBounds(6, 6, 298, 39);
        contentPane.add(label);
        JLabel label_1 = new JLabel("服务器名称：");// 创建标签
        label_1.setBounds(6, 57, 83, 18);// 设置标签大小
        contentPane.add(label_1);
        JLabel label_2 = new JLabel("服务器ｉｐ：");// 创建标签
        label_2.setBounds(6, 95, 83, 18);// 设置标签大小
        contentPane.add(label_2);
        textField = new JTextField();// 创建输入服务器名称的文本框
        textField.setBounds(82, 51, 251, 30);
        contentPane.add(textField);
        textField.setColumns(10);
        JButton button = new JButton("确定");// 创建确定按钮
        button.setBounds(54, 132, 90, 30);
        contentPane.add(button);
        JButton button_1 = new JButton("关闭");// 创建关闭按钮
        button_1.setBounds(177, 132, 90, 30);
        contentPane.add(button_1);
        IpField ipField = new IpField();// 创建IP文本框控件
        ipField.setBounds(82, 88, 251, 25);// 设置控件大小
        contentPane.add(ipField);
    }
}
