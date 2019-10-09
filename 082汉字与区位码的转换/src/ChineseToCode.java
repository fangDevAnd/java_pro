import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;

public class ChineseToCode extends JFrame {
    
    private JPanel contentPane;
    private JTextField textField;
    private JTextField resultField;
    
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
                    ChineseToCode frame = new ChineseToCode();
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
    public ChineseToCode() {
        setTitle("\u6C49\u5B57\u4E0E\u533A\u4F4D\u7801\u7684\u8F6C\u6362");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 321, 128);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel label = new JLabel("\u8F93\u5165\u4E00\u4E2A\u6C49\u5B57\uFF1A");
        label.setBounds(21, 10, 103, 15);
        contentPane.add(label);
        
        JButton button = new JButton("\u8F6C\u6362\u4E3A\u533A\u4F4D\u7801");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        button.setBounds(21, 48, 103, 23);
        contentPane.add(button);
        
        textField = new JTextField();
        textField.setBounds(134, 2, 161, 30);
        contentPane.add(textField);
        textField.setColumns(10);
        
        resultField = new JTextField();
        resultField.setBounds(134, 45, 161, 30);
        contentPane.add(resultField);
        resultField.setColumns(10);
    }
    
    protected void do_button_actionPerformed(ActionEvent e) {
        String text = textField.getText();// 获取用户输入
        if (text.length() > 2) {// 禁止输入多个汉字
            JOptionPane.showMessageDialog(null, "不要输入过多汉字");
            return;
        }
        byte[] codeBit = text.getBytes();// 获取汉字的字节数组
        if (codeBit.length < 2) {// 禁止非汉字的区码获取
            JOptionPane.showMessageDialog(null, "您输入的好像不是汉字");
            return;
        }
        codeBit[0] -= 160;// 提取字节对应的区码
        codeBit[1] -= 160;
        // 组合最终区码编号
        String code = formatNumber(codeBit[0]) + formatNumber(codeBit[1]);
        resultField.setText(code);// 在文本框显示汉字的区码
    }
    
    /**
     * 保留数字位数的字符串格式
     * 
     * @param num
     * @return
     */
    private String formatNumber(int num) {
        String format = String.format("%02d", num);// 数字的补零格式
        return format;// 返回格式化后的字符串
    }
}
