import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class LazyButton extends JFrame {
    
    private JPanel contentPane;
    private JButton button;
    private Timer timer;
    
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
                    LazyButton frame = new LazyButton();
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
    public LazyButton() {
        setTitle("\u5EF6\u8FDF\u751F\u6548\u7684\u6309\u94AE");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                do_this_windowOpened(e);
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 372, 395);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(18, 50, 318, 224);
        contentPane.add(scrollPane);
        
        JTextArea textArea = new JTextArea();// 创建文本域控件
        textArea.setLineWrap(true);// 自动折行
        StringBuilder sb = new StringBuilder();// 创建字符串构建器
        // 创建文本扫描器
        Scanner scan = new Scanner(getClass().getResourceAsStream("lzw.txt"));
        while (scan.hasNext()) {// 遍历文本扫描器
            String string = (String) scan.nextLine();// 逐行获取数据
            sb.append(string + "\n");// 把所有行数据添加到字符串构建器
        }
        textArea.setText(sb.toString());// 释放字符串构建器中的字符串到文本域
        textArea.setSelectionStart(0);// 把文本域在滚动面板中滚至首行
        textArea.setSelectionEnd(0);
        scrollPane.setViewportView(textArea);
        // 创建标签控件
        JLabel lblJava = new JLabel("Java编程词典许可协议");
        lblJava.setFont(new Font("SansSerif", Font.PLAIN, 24));// 指定标签字体
        lblJava.setHorizontalAlignment(SwingConstants.CENTER);// 标签文本居中
        lblJava.setBounds(18, 6, 318, 32);
        contentPane.add(lblJava);
        // 创建接收按钮
        button = new JButton("接受（10秒）");
        button.setEnabled(false);// 取消按钮的可用状态
        button.setBounds(59, 286, 124, 30);
        contentPane.add(button);
        // 创建拒绝按钮
        JButton button_1 = new JButton("拒绝");
        button_1.setBounds(195, 286, 90, 30);
        contentPane.add(button_1);
    }
    
    protected void do_this_windowOpened(WindowEvent e) {// 窗体打开事件处理方法
        timer = new Timer(1000, new ActionListener() {// 创建timer对象并实现事件处理监听器
                    int tNum = 10;// 定义倒计时描述
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        button.setText("接受（" + --tNum + "秒）");// 更新按钮的计时文本
                        if (tNum <= 0) {// 计时结束后，激活按钮可用状态并停止timer控件
                            button.setEnabled(true);
                            timer.stop();
                        }
                    }
                });
        timer.start();// 启动timer控件
    }
}
