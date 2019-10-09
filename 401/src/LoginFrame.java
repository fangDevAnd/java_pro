import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.UIManager;

public class LoginFrame extends JFrame {
    
    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton button;
    
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
                    LoginFrame frame = new LoginFrame();
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
    public LoginFrame() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                do_this_windowActivated(e);
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 329, 185);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel label = new JLabel("天雨系统登录界面"); // 创建标签控件
        label.setHorizontalAlignment(SwingConstants.CENTER);// 标签文本居中对其
        label.setFont(new Font("SansSerif", Font.PLAIN, 24));// 设置标签控件字体
        label.setBounds(6, 6, 309, 51);
        contentPane.add(label);
        JLabel label_1 = new JLabel("用户名："); // 创建标签控件
        label_1.setBounds(16, 69, 55, 18);
        contentPane.add(label_1);
        JLabel label_2 = new JLabel("密　码："); // 创建标签控件
        label_2.setBounds(16, 103, 55, 18);
        contentPane.add(label_2);
        textField = new JTextField();// 创建文本框
        textField.setBounds(65, 63, 242, 30);
        contentPane.add(textField);
        textField.setColumns(10);// 设置文本框列数
        passwordField = new JPasswordField();// 创建密码框
        passwordField.setBounds(65, 99, 143, 30);
        contentPane.add(passwordField);
        button = new JButton("登　录");// 创建登录按钮但没有定位
        contentPane.add(button);
    }
    
    protected void do_this_windowActivated(WindowEvent e) {// 创建激活事件处理方法
        new Thread() {// 创建匿名线程
            @Override
            public void run() {
                for (int i = 0; i < 217; i++) {// 循环控制按钮的移动
                    button.setBounds(i, i > 99 ? 99 : i, 90, 30);// 移动按钮
                    getRootPane().setComponentZOrder(button, 0);// 把按钮置顶显示
                    try {
                        sleep(1);// 线程休眠
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();// 启动线程
    }
}
