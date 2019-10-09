import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class CalculagraphFrame extends JFrame {
    
    private JPanel contentPane;
    private long sourTime;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CalculagraphFrame frame = new CalculagraphFrame();
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
    public CalculagraphFrame() {
        setTitle("窗体标题显示计时器");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                do_this_windowOpened(e);
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 138);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JLabel label = new JLabel(
                "<html><p>　　本窗体可以在标题栏显示计时器，标识当前窗体已经运行了多少秒钟</p></html>");
        label.setFont(new Font("SansSerif", Font.PLAIN, 22));
        contentPane.add(label, BorderLayout.CENTER);
    }
    
    protected void do_this_windowOpened(WindowEvent e) {// 窗体打开事件处理方法
        sourTime = System.currentTimeMillis();// 记录窗体打开的初始时间
        // 创建Timer控件
        Timer timer = new Timer(1000, new ActionListener() {
            String title = getTitle();// 提取原始标题文本
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // 技术消耗时间
                long smTime = System.currentTimeMillis() - sourTime;
                // 显示计时信息到标题栏
                setTitle(title + "【窗体已经运行了" + (smTime / 1000) + "秒】");
            }
        });
        timer.start();// 启动Timer控件
    }
}
