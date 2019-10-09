import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

public class IconTitledBorder extends JFrame {
    
    private JPanel contentPane;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    IconTitledBorder frame = new IconTitledBorder();
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
    public IconTitledBorder() {
        setTitle("带图标边框的标题边框");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 398, 271);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 239, 213));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        ImageIcon icon = new ImageIcon(getClass().getResource("icon.png"));// 创建图标对象
        JPanel panel_12 = new JPanel();// 创建面板对象
        panel_12.setOpaque(false);// 面板透明
        MatteBorder matteBorder = new MatteBorder(16, 16, 16, 16, icon);// 创建MatteBorder边框
        Font font = new Font("隶书", Font.ITALIC | Font.BOLD, 24);// 创建字体
        // 创建TitledBorder边框并把MatteBorder边框作为构造方法的参数进行嵌套
        TitledBorder titledBorder = new TitledBorder(matteBorder, "图标边框的标题",
                TitledBorder.LEADING, TitledBorder.ABOVE_TOP, font, Color.BLACK);
        panel_12.setBorder(titledBorder);// 设置面板容器使用TitledBorder边框
        contentPane.add(panel_12);
    }
}
