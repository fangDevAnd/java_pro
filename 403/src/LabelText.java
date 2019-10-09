import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.SwingConstants;

public class LabelText extends JFrame {
    
    private JPanel contentPane;
    private JLabel label;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LabelText frame = new LabelText();
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
    public LabelText() {
        addWindowListener(new WindowAdapter() {// 为窗体添加打开事件处理器
            @Override
            public void windowOpened(WindowEvent e) {
                do_this_windowOpened(e);// 调用窗体打开事件处理方法
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置窗体默认关闭方式
        setBounds(100, 100, 450, 179);// 设置窗体大小
        contentPane = new JPanel();// 创建内容面板
        setContentPane(contentPane);// 设置内容面板
        contentPane.setLayout(new BorderLayout(0, 0));// 设置窗体布局
        label = new JLabel("");// 创建标签控件
        label.setHorizontalAlignment(SwingConstants.RIGHT);// 文本右对齐
        contentPane.add(label);// 添加标签到窗体
    }
    
    protected void do_this_windowOpened(WindowEvent e) {
        new Thread() {// 创建新的匿名线程对象
            @Override
            public void run() {// 重写run()方法
                int len = getWidth() / 12;// 获取跑马灯LED数量
                String info = "Java编程词典";// 定义跑马灯文字
                while (true) {// 创建无限循环
                    String space = "";// 创建空白字符串
                    for (int i = 0; i < len - info.length() - 2; i++) {// 遍历LED数量
                        len = getWidth() / 12;// 获取跑马灯LED数量
                        space += "　";// 为空白字符串添加空格字符
                        label.setText(info + space);// 设置标签文本
                        try {
                            sleep(300);// 线程休眠
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();// 启动线程
    }
}
