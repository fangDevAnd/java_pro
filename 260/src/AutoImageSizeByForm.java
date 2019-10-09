import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AutoImageSizeByForm extends JFrame {
    
    private JPanel contentPane;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AutoImageSizeByForm frame = new AutoImageSizeByForm();
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
    public AutoImageSizeByForm() {
        setTitle("使背景图片自动适应窗体的大小");// 设置窗体标题
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);// 设置窗体位置
        contentPane = new JPanel();// 创建内容面板
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        // 创建自定义背景面板
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setImage(getToolkit().getImage(
                getClass().getResource("Penguins.jpg")));// 设置背景面板的图片
        contentPane.add(backgroundPanel, BorderLayout.CENTER);// 添加背景面板到内容面板
    }
    
}
