import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;


public class CancelFrameTitleBorder extends JFrame {
    
    private JPanel contentPane;
    private JTextArea textArea;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CancelFrameTitleBorder frame = new CancelFrameTitleBorder();
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
public CancelFrameTitleBorder() {
    // 设置背景色
    getContentPane().setBackground(new Color(240, 255, 255));
    setUndecorated(true);// 取消窗体修饰效果************
    setTitle("关于进销存管理系统");// 设置标题栏
    getContentPane().setLayout(null);
    setBounds(100, 100, 354, 206);
    setLocationRelativeTo(null);// 窗体居中
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    final JLabel label = new JLabel();// 用标签显示logo
    label.setIcon(new ImageIcon(getClass().getResource("logo.png")));
    label.setBounds(10, 27, 112, 98);
    getContentPane().add(label);
    textArea = new JTextArea();// 用文本域显示系统信息
    textArea.setOpaque(false);// 控件透明
    textArea.setText("系统：\n  Microsoft Windows Server 2003\n" +
            "  Standard Editon\n  Service Pack 2\n\n\n" +
            "软件：进销存管理系统\n版权：明日科技");
    textArea.setBounds(154, 6, 187, 154);
    getContentPane().add(textArea);// 添加控件到窗体
    JButton button = new JButton("\u5173\u95ED");// 创建“关闭”按钮
    button.addActionListener(new ActionListener() {// 添加按钮的事件监听器
        public void actionPerformed(ActionEvent e) {
            do_button_actionPerformed(e);// 调用按钮事件处理方法
        }
    });
    button.setBounds(230, 172, 90, 30);
    getContentPane().add(button);// 添加按钮到窗体
}
    
protected void do_button_actionPerformed(ActionEvent e) {
    dispose();// 销毁窗体
}
}
