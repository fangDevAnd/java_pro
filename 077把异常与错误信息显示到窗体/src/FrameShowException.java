import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class FrameShowException extends JFrame {
    
    private JPanel contentPane;
    private JTextField textField;
    private JTextArea textArea;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrameShowException frame = new FrameShowException();
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
    public FrameShowException() {
        setTitle("\u663E\u793A\u5F02\u5E38\u4FE1\u606F");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 253);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel label = new JLabel(
                "\u8BF7\u8F93\u5165\u975E\u6570\u5B57\u5B57\u7B26\u4E32\uFF0C\u67E5\u770B\u8F6C\u6362\u4E3A\u6570\u5B57\u65F6\u53D1\u751F\u7684\u5F02\u5E38\u3002");
        label.setBounds(10, 10, 414, 15);
        contentPane.add(label);
        
        textField = new JTextField();
        textField.setBounds(10, 32, 248, 21);
        contentPane.add(textField);
        textField.setColumns(10);
        
        JButton btninteger = new JButton(
                "\u8F6C\u6362\u4E3AInteger\u7C7B\u578B");
        btninteger.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_btninteger_actionPerformed(e);
            }
        });
        btninteger.setBounds(268, 31, 156, 23);
        contentPane.add(btninteger);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setOpaque(false);
        scrollPane.setBounds(10, 63, 414, 142);
        contentPane.add(scrollPane);
        
        textArea = new JTextArea();
        textArea.setText("\u4FE1\u606F\u63D0\u793A\u6846");
        textArea.setEditable(false);
        scrollPane.setViewportView(textArea);
    }
    
    protected void do_btninteger_actionPerformed(ActionEvent e) {
        // 创建字节数组输出流
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(stream));// 重定向err输出流
        String numStr = textField.getText();// 获取用户输入
        try {
            Integer value = Integer.valueOf(numStr);// 字符串转整数
        } catch (NumberFormatException e1) {
            e1.printStackTrace();// 输出错误异常信息
        }
        String info = stream.toString();// 获取字节输出流的字符串
        if (info.isEmpty()) {// 显示正常转换的提示信息
            textArea.setText("字符串到Integer的转换没有发生异常。");
        } else {// 显示出现异常的提示信息与异常
            textArea.setText("错错啦！转换过程中出现了如下异常错误：\n" + info);
        }
    }
}