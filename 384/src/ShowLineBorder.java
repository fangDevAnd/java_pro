import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class ShowLineBorder extends JFrame {
    
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
                    ShowLineBorder frame = new ShowLineBorder();
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
    public ShowLineBorder() {
        setTitle("\u4E3A\u6587\u672C\u6846\u63A7\u4EF6\u6DFB\u52A0LineBorder\u7EBF\u5F62\u8FB9\u6846");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 368, 249);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(0, 1, 0, 0));
        JTextArea textArea = new JTextArea();// 创建默认边框的文本域控件
        textArea.setText("默认文本域控件外观");
        contentPane.add(textArea);
        JTextArea textArea_1 = new JTextArea();// 创建文本域控件
        textArea_1.setText("圆角直线边框的文本域控件");
        // 设置默认颜色的圆角直线边框
        textArea_1.setBorder(new LineBorder(new Color(0, 0, 0), 5, true));
        contentPane.add(textArea_1);
        JTextArea textArea_2 = new JTextArea();// 创建文本域控件
        textArea_2.setText("直角绿色直线边框的文本域控件外观");
        // 设置绿色的直角直线边框
        textArea_2.setBorder(new LineBorder(Color.GREEN, 5));
        contentPane.add(textArea_2);
    }
}
