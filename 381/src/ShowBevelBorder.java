import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.UIManager;

public class ShowBevelBorder extends JFrame {
    
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
                    ShowBevelBorder frame = new ShowBevelBorder();
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
    public ShowBevelBorder() {
        setTitle("实现标签控件的立体边框");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 132);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel label = new JLabel("凹陷的立体边框");
        label.setBounds(150, 18, 100, 22);// 设置标签位置与大小
        contentPane.add(label);// 添加标签到窗体面板
        label.setHorizontalAlignment(SwingConstants.CENTER);// 文本居中显示
        label.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null,
                null));// 设置标签的边框
        
        JLabel label_1 = new JLabel("突起的立体边框");
        label_1.setBounds(150, 52, 100, 22);// 设置标签位置与大小
        contentPane.add(label_1);// 添加标签到窗体面板
        label_1.setHorizontalAlignment(SwingConstants.CENTER);// 文本居中显示
        label_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
                null));// 设置标签的边框
        
        JLabel label_2 = new JLabel("它们可不是按钮，都是标签");
        label_2.setBounds(262, 20, 166, 54);// 设置标签位置与大小
        contentPane.add(label_2);// 添加标签到窗体面板
        
        JLabel label_3 = new JLabel("指定边框颜色");
        label_3.setHorizontalAlignment(SwingConstants.CENTER);// 文本居中显示
        label_3.setBounds(6, 17, 124, 55);// 设置标签位置与大小
        contentPane.add(label_3);// 添加标签到窗体面板
        Color highlightOuter = new Color(255, 255, 0);// 边框的高亮颜色参数
        Color highlightInner = new Color(255, 175, 175);
        label_3.setBorder(new BevelBorder(BevelBorder.LOWERED, highlightOuter,
                highlightInner, Color.BLUE, Color.RED));// 设置标签的边框
    }
}
