import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class SetFormBackColor extends JFrame {
    
    private JPanel contentPane;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SetFormBackColor frame = new SetFormBackColor();
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
    public SetFormBackColor() {
        setTitle("设置窗体背景颜色为淡蓝色");// 设置窗体标题栏
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 308, 238);// 设置窗体位置
        contentPane = new JPanel();// 创建内容面板
        // 设置内容面板的背景色
        contentPane.setBackground(new Color(102, 204, 255));
        setContentPane(contentPane);// 设置窗体内容面板
    }
    
}
