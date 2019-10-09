import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MouseCursorFrame extends JFrame {
    
    private JPanel contentPane;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MouseCursorFrame frame = new MouseCursorFrame();
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
    public MouseCursorFrame() {
        setTitle("设置窗体的鼠标光标");// 设置窗体标题
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 318, 205);// 设置窗体位置
        contentPane = new JPanel();// 创建内容面板
        Toolkit toolkit = getToolkit();// 获取窗体工具包
        // 创建鼠标光标图片对象
        Image image = toolkit.getImage(getClass().getResource("1.png"));
        // 通过图片创建光标对象
        Cursor cursor = toolkit.createCustomCursor(image, new Point(0, 0),
                "lzw");
        contentPane.setCursor(cursor);// 设置内容面板的鼠标光标
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// 边框
        contentPane.setLayout(new BorderLayout(0, 0));// 布局
        setContentPane(contentPane);
    }
    
}
