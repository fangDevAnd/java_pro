import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.lzw.widget.SmallScrollPanel;
import com.lzw.widget.ButtonPanel;
import javax.swing.UIManager;
import java.awt.Color;

public class PanelFrame extends JFrame {
    
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
                    PanelFrame frame = new PanelFrame();
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
    public PanelFrame() {
        setTitle("平移面板控件");// 设置窗体标题
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 133);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(102, 204, 204));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));// 设置布局管理器
        setContentPane(contentPane);
        // 创建平移滚动面板
        SmallScrollPanel smallScrollPanel = new SmallScrollPanel();
        // 添加面板到窗体
        contentPane.add(smallScrollPanel, BorderLayout.CENTER);
        ButtonPanel buttonPanel = new ButtonPanel();// 创建按钮组面板
        buttonPanel.setOpaque(false);
        // 把按钮组面板设置我平移面板的管理视图
        smallScrollPanel.setViewportView(buttonPanel);
    }
    
}
